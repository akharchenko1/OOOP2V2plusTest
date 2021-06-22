package workflow.exceptions;

public class UnableCreateBlockException extends WorkflowException {
    public UnableCreateBlockException(String message) {
        super(message);
    }

    public UnableCreateBlockException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableCreateBlockException(Throwable cause) {
        super(cause);
    }

    public UnableCreateBlockException() {
    }
}