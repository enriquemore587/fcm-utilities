package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by BBVA on 08/06/2016.
 */
public class FCMNotification {

    private String to;
    // high or normal
    private String priority;

    // A NotificationPartial can be replaced by other with the same KEY. ex: "collapse_key" : "alerta1"
    @SerializedName("collapse_key")
    private String collapseKey;

    @SerializedName("delay_while_idle")
    private Boolean delayWhileIdle;

    // Only Android
    @SerializedName("time_to_live")
    private Integer timeToLive;

    @SerializedName("content_available")
    private Boolean contentAvailable;

    private NotificationPartial notification;

    private Map<String, String> data;
//    private Data data;

//    private class Data {
//        protected String body;
//
//        protected String score;
//        protected String time;
//    }

    public FCMNotification() {}

    public FCMNotification(String to, NotificationPartial notification) {
        this.to = to;
        this.notification = notification;
    }

    public FCMNotification(String to, Map<String, String> data) {
        this.to = to;
        this.data = data;
    }

    public FCMNotification(String to, NotificationPartial notification, Map<String, String> data) {
        this.to = to;
        this.notification = notification;
        this.data = data;
    }

    public FCMNotification(String to, String priority, NotificationPartial notification, Map<String, String> data) {
        this.to = to;
        this.priority = priority;
        this.notification = notification;
        this.data = data;
    }

    public FCMNotification(String to, String priority, Integer timeToLive, NotificationPartial notification, Map<String, String> data, String collapseKey, Boolean delayWhileIdle) {
        this.to = to;
        this.priority = priority;
        this.timeToLive = timeToLive;
        this.notification = notification;
        this.data = data;
        this.collapseKey = collapseKey;
        this.delayWhileIdle = delayWhileIdle;
    }

    public FCMNotification(String to, String priority, String collapseKey, Boolean delayWhileIdle, Integer timeToLive, NotificationPartial notification, Map<String, String> data) {
        this.to = to;
        this.priority = priority;
        this.collapseKey = collapseKey;
        this.delayWhileIdle = delayWhileIdle;
        this.timeToLive = timeToLive;
        this.notification = notification;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public Boolean getDelayWhileIdle() {
        return delayWhileIdle;
    }

    public void setDelayWhileIdle(Boolean delayWhileIdle) {
        this.delayWhileIdle = delayWhileIdle;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

    public NotificationPartial getNotification() {
        return notification;
    }

    public void setNotification(NotificationPartial notification) {
        this.notification = notification;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Boolean getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Boolean contentAvailable) {
        this.contentAvailable = contentAvailable;
    }
}
