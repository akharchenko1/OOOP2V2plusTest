package workflow;

import workflow.blocks.Block;
import workflow.exceptions.BlockNotFoundException;
import workflow.exceptions.FactoryException;
import workflow.exceptions.UnableCreateBlockException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockFactory {

    private static final Logger log = Logger.getLogger(BlockFactory.class.getName());

    private final Properties config = new Properties();

    private static volatile BlockFactory factory; //volatile=нельзя читать, пока не инициализированна

    private BlockFactory() throws IOException, FactoryException {
        var configStream = BlockFactory.class
                .getClassLoader()
                .getResourceAsStream("BlockFactory.config");
        if (configStream == null) {
            throw new FactoryException("Can not find config. " +
                    "It should be named BlockFactory.config and be located in resources.");
        }
        config.load(configStream);
    }

    // double-checked singleton
    public static BlockFactory getInstance() throws IOException, FactoryException {
        if (factory == null) {
            synchronized (BlockFactory.class) { // synchronized=сюда может зайти одновременно только 1 поток
                if (factory == null) {
                    factory = new BlockFactory(); // отложенная инициализация
                }
            }
        }
        return factory;
    }

    public Block getBlock(String blockName) throws UnableCreateBlockException, BlockNotFoundException {
        if (!config.containsKey(blockName)) {
            log.severe("Block with name " + blockName + " not found in config");
            throw new BlockNotFoundException("Block with name " + blockName + " not found in config");
        }
        Block newBlock;
        try {
            var blockClass = Class.forName(config.getProperty(blockName)); //
            var blockObject = blockClass.getDeclaredConstructor().newInstance(); // получает стандартный конструктор без параметров
            newBlock = (Block) blockObject;
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Factory can not find class by name", e);
            throw new UnableCreateBlockException("Factory can not find class by name", e);
        } catch (NoSuchMethodException e) {
            log.log(Level.SEVERE, "Exception: Factory can not find constructor in block " + blockName, e);
            throw new UnableCreateBlockException("Factory can not find constructor in block", e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.log(Level.SEVERE, "Exception: Factory can not create new instance of " + blockName, e);
            throw new UnableCreateBlockException("Factory can not create new instance of", e);
        }
        return newBlock;
    }

}

