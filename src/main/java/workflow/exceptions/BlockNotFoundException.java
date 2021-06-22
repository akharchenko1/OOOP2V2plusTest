package workflow.exceptions;

public class BlockNotFoundException extends WorkflowException {
    public BlockNotFoundException(String message) {
        super(message);
    }
    public BlockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockNotFoundException(Throwable cause) {
        super(cause);
    }

    public BlockNotFoundException() {
    }
}
