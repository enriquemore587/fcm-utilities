package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by BBVA on 16/06/2016.
 * OGG
 */
public class FCMGroupException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMGroupException() {
        super();
    }

    public FCMGroupException(final String message) {
        this.message = message;
    }

    public FCMGroupException(final String message, final Throwable cause) {
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
