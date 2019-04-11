package mx.bbva.intranet.utilities.fcm.exceptions;

/**
 * Created by BBVA on 16/06/2016.
 * OGG
 */
public class FCMPushNotificationException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public FCMPushNotificationException() {
        super();
    }

    public FCMPushNotificationException(final String message) {
        this.message = message;
    }

    public FCMPushNotificationException(final String message, final Throwable cause) {
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
