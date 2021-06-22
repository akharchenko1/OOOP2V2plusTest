package workflow.exceptions;

public class FactoryException extends WorkflowException {
    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }

    public FactoryException() {
    }
}
