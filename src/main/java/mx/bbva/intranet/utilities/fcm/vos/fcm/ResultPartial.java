package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BBVA on 09/06/2016.
 */
public class ResultPartial {

    @SerializedName("message_id")
    private String messageId;
    @SerializedName("registration_id")
    private String registrationId;
    private String error;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
