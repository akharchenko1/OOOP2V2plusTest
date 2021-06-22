package workflow.blocks;

import workflow.BlockType;
import workflow.exceptions.WorkflowException;

import java.util.List;
import java.util.stream.Collectors;

// sort – лексикографическая сортировка входного набора строк.
// Вход – текст, выход – текст.

public class SortBlock implements Block {


    @Override
    public List<String> execute(List<String> text, String[] args) throws WorkflowException {
        if (text == null) {
            return null;
        }
        return text.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Override
    public BlockType getType() {
        return BlockType.InputOutput;
    }
}

