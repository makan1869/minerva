package ir.serenade.minerva.exception;

/**
 * Created by serenade on 8/6/18.
 */
public class ResourceNotAuthorizedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4088649120307193878L;

    public ResourceNotAuthorizedException() {
        super();
    }

    public ResourceNotAuthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotAuthorizedException(final String message) {
        super(message);
    }

    public ResourceNotAuthorizedException(final Throwable cause) {
        super(cause);
    }

}
