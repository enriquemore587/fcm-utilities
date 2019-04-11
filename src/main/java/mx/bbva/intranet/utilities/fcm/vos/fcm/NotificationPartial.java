package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BBVA on 09/06/2016.
 */
public class NotificationPartial {

    // Required
    private String title;
    private String body;
    // Android
    // Required
    private String icon;
    private String sound;
    // For iOS
    private String badge;
    // For Android
    private String tag;
    // #rrggbb format
    private String color;

    @SerializedName("click_action")
    private String clickAction;
    @SerializedName("body_loc_key")
    private String bodyLocKey;
    @SerializedName("body_loc_args")
    private String bodyLocArgs;
    @SerializedName("title_loc_key")
    private String titleLocKey;
    @SerializedName("title_loc_args")
    private String titleLocArgs;

    public NotificationPartial() {
    }

    public NotificationPartial(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public NotificationPartial(String title, String body, String icon, String sound, String color) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.sound = sound;
        this.color = color;
    }

    public NotificationPartial(String title, String body, String icon, String sound, String badge, String color) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.sound = sound;
        this.badge = badge;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

    public String getBodyLocKey() {
        return bodyLocKey;
    }

    public void setBodyLocKey(String bodyLocKey) {
        this.bodyLocKey = bodyLocKey;
    }

    public String getBodyLocArgs() {
        return bodyLocArgs;
    }

    public void setBodyLocArgs(String bodyLocArgs) {
        this.bodyLocArgs = bodyLocArgs;
    }

    public String getTitleLocKey() {
        return titleLocKey;
    }

    public void setTitleLocKey(String titleLocKey) {
        this.titleLocKey = titleLocKey;
    }

    public String getTitleLocArgs() {
        return titleLocArgs;
    }

    public void setTitleLocArgs(String titleLocArgs) {
        this.titleLocArgs = titleLocArgs;
    }

}
