package workflow;

import workflow.exceptions.ParsingException;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Description {
    HashMap<Integer, String> blocks;

    private static Logger log = Logger.getLogger(WorkflowExecutor.class.getName());

    public Description(BufferedReader inputReader) throws ParsingException {
        blocks = new HashMap<>();
        try {
            String currentLine = inputReader.readLine();
            while (!currentLine.startsWith("desc")) {
                currentLine = inputReader.readLine();
                if (currentLine == null) {
                    throw new ParsingException("Can not find begin of description (it should begin with \"desc\")");
                }
            }
            currentLine = inputReader.readLine();
            if (currentLine == null) {
                throw new ParsingException("Can not find end of description (it should end with \"csed\")");
            }
            while (!currentLine.equals("csed")) {
                String[] idAndCommand = currentLine.split(" = ", 2);
                if (idAndCommand.length < 2) {
                    throw new ParsingException("Can not read command from description.");
                }
                Integer id = Integer.valueOf(idAndCommand[0]);
                if (blocks.containsKey(id)) { // Blocks id should not repeat
                    log.log(Level.SEVERE, "Repeated id in description: " + currentLine
                            + "\nConflict with: " + blocks.get(id));
                    throw new ParsingException("Repeated id in description.");
                }
                blocks.put(id, idAndCommand[1]);
                currentLine = inputReader.readLine();
                if (currentLine == null) {
                    throw new ParsingException("Can not find end of description (it should end with \"csed\")");
                }
            }

        } catch (IOException e) {
            log.log(Level.SEVERE, "Error while reading description", e);
            throw new ParsingException("Error while reading description", e);
        }
    }

    HashMap<Integer, String> getDescription() {
        return blocks;
    }

}
