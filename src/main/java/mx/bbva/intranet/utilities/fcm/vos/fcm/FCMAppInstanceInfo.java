package mx.bbva.intranet.utilities.fcm.vos.fcm;

import java.util.Map;

/**
 * Created by BBVA on 17/06/2016.
 * OGG
 */
public class FCMAppInstanceInfo {

    private String application;
    private String authorizedEntity;
    private String applicationVersion;
    private String appSigner;
    private String attestStatus;
    private String platform;
    private String connectionType;
    private String connectDate;
    private Map<String, Map<String, TopicPartial>> rel;

    // Over here will be assigned the Error at the moment of verify InstanceId info
    private String error;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAuthorizedEntity() {
        return authorizedEntity;
    }

    public void setAuthorizedEntity(String authorizedEntity) {
        this.authorizedEntity = authorizedEntity;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getAppSigner() {
        return appSigner;
    }

    public void setAppSigner(String appSigner) {
        this.appSigner = appSigner;
    }

    public String getAttestStatus() {
        return attestStatus;
    }

    public void setAttestStatus(String attestStatus) {
        this.attestStatus = attestStatus;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectDate() {
        return connectDate;
    }

    public void setConnectDate(String connectDate) {
        this.connectDate = connectDate;
    }

    public Map<String, Map<String, TopicPartial>> getRel() {
        return rel;
    }

    public void setRel(Map<String, Map<String, TopicPartial>> rel) {
        this.rel = rel;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
