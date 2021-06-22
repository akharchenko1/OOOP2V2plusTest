package workflow.blocks;

import workflow.BlockType;
import workflow.exceptions.WorkflowException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// dump <filename> - сохранить пришедший текст в указанном файле и передать дальше.
// Вход – текст, выход – текст.

public class DumpBlock implements Block {

    @Override
    public List<String> execute(List<String> text, String[] args) throws WorkflowException {
        if (args == null || args.length < 1) {
            throw new WorkflowException("Not enough args for the command");
        }
        if (text == null) {
            return null;
        }

        try (FileWriter fileWriter = new FileWriter(args[0])) {
            for (String line : text) {
                fileWriter.write(line);
            }
        } catch (IOException e) {
            throw new WorkflowException("Can not write in file", e);
        }

        return text;
    }

    @Override
    public BlockType getType() {
        return BlockType.InputOutput;
    }
}
