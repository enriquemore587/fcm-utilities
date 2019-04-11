package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by XMZ0860 on 23/06/2016.
 */
public class FCMException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMException() {
        super();
    }

    public FCMException(final String message) {
        this.message = message;
    }

    public FCMException(final String message, final Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
