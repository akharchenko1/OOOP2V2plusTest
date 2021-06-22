package workflow.exceptions;

public class WorkflowException extends Exception {

    public WorkflowException(String message) {
        super(message);
    }

    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowException(Throwable cause) {
        super(cause);
    }

    public WorkflowException() {
    }

}