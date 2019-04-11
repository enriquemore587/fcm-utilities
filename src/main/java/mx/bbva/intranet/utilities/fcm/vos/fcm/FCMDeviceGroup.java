package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BBVA on 13/06/2016.
 */
public class FCMDeviceGroup {

    private String operation;
//    private FCMOperation operation;

    @SerializedName("notification_key_name")
    private String notificationKeyName;

    @SerializedName("registration_ids")
    private List<String> registrationIds;

    @SerializedName("notification_key")
    private String notificationKey;

    private String error;

    public FCMDeviceGroup() {}

    public FCMDeviceGroup(String operation, String notificationKeyName, List<String> registrationIds) {
        this.operation = operation;
        this.notificationKeyName = notificationKeyName;
        this.registrationIds = registrationIds;
    }

    public FCMDeviceGroup(String operation, String notificationKeyName, List<String> registrationIds, String notificationKey) {
        this.operation = operation;
        this.notificationKeyName = notificationKeyName;
        this.registrationIds = registrationIds;
        this.notificationKey = notificationKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getNotificationKeyName() {
        return notificationKeyName;
    }

    public void setNotificationKeyName(String notificationKeyName) {
        this.notificationKeyName = notificationKeyName;
    }

    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(List<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "FCMDeviceGroup{" +
                "operation='" + operation + '\'' +
                ", notificationKeyName='" + notificationKeyName + '\'' +
                ", registrationIds=" + registrationIds +
                ", notificationKey='" + notificationKey + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
