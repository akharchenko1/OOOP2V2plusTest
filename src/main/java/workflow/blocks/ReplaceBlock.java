package workflow.blocks;

import workflow.BlockType;
import workflow.exceptions.WorkflowException;

import java.util.List;

// replace <word1> <word2> – замена слова <word1> словом <word2> во входном тексте.
// Вход – текст, выход – текст.

public class ReplaceBlock implements Block {

    @Override
    public List<String> execute(List<String> text, String[] args) throws WorkflowException {
        if (args == null || args.length < 2) {
            throw new WorkflowException("Not enough args for the command");
        }
        if (text == null) {
            return null;
        }
        String keyWord = args[0];
        String newWord = args[1];
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (line.contains(keyWord)) {
                String newLine = line.replace(keyWord, newWord);
                text.set(i, newLine);
            }
        }
        return text;
    }

    @Override
    public BlockType getType() {
        return BlockType.InputOutput;
    }
}

