package workflow.blocks;

import workflow.BlockType;
import workflow.exceptions.WorkflowException;

import java.util.List;

public interface Block {
    List<String> execute(List<String> text, String[] args) throws WorkflowException;

    BlockType getType();
}
