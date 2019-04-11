package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by XMZ0860 on 23/06/2016.
 */
public class FCMInstanceIdException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMInstanceIdException() {
        super();
    }

    public FCMInstanceIdException(final String message) {
        this.message = message;
    }

    public FCMInstanceIdException(final String message, final Throwable cause) {
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
