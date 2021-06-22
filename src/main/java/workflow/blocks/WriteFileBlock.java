package workflow.blocks;

import workflow.BlockType;
import workflow.exceptions.WorkflowException;

import java.io.*;
import java.util.List;

// writefile <filename> – запись текста в файл.
// Вход – текст, выход – отсутствует.

public class WriteFileBlock implements Block {

    @Override
    public List<String> execute(List<String> text, String[] args) throws WorkflowException {
        if (args == null || args.length < 1) {
            throw new WorkflowException("Not enough args for the command");
        }
        try (FileWriter fileWriter = new FileWriter(args[0])) {
            for (String line : text) {
                fileWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new WorkflowException("Can not write in file", e);
        }
        return null;
    }

    @Override
    public BlockType getType() {
        return BlockType.InputOnly;
    }
}

