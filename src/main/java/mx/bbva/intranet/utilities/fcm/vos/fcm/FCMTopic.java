package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by BBVA on 17/06/2016.
 * OGG
 */
public class FCMTopic {

    // Batch MODE
    @Expose(serialize = false)
    private String operation;
    private String to;
    @SerializedName("registration_tokens")
    private List<String> registrationTokens;

    private List<Map<String, String>> results;

    // Single MODE
    private String instanceId;
    private String error;

    public FCMTopic() {
    }

    public FCMTopic(String operation, String to, List<String> registrationTokens) {
        this.operation = operation;
        this.to = to;
        this.registrationTokens = registrationTokens;
    }

    public FCMTopic(String to, List<String> registrationTokens) {
        this.to = to;
        this.registrationTokens = registrationTokens;
    }

    public FCMTopic(String to, String instanceId) {
        this.to = to;
        this.instanceId = instanceId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getRegistrationTokens() {
        return registrationTokens;
    }

    public void setRegistrationTokens(List<String> registrationTokens) {
        this.registrationTokens = registrationTokens;
    }

    public List<Map<String, String>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, String>> results) {
        this.results = results;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
