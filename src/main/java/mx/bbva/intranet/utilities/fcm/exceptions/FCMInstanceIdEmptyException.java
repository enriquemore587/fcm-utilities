package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by XMZ0860 on 23/06/2016.
 */
public class FCMInstanceIdEmptyException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMInstanceIdEmptyException() {
        super();
    }

    public FCMInstanceIdEmptyException(final String message) {
        this.message = message;
    }

    public FCMInstanceIdEmptyException(final String message, final Throwable cause) {
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
