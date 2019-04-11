package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by XMZ0860 on 23/06/2016.
 */
public class FCMTopicException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMTopicException() {
        super();
    }

    public FCMTopicException(final String message) {
        this.message = message;
    }

    public FCMTopicException(final String message, final Throwable cause) {
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
