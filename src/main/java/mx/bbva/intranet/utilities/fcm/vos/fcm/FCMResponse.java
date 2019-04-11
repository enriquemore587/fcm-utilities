package mx.bbva.intranet.utilities.fcm.vos.fcm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BBVA on 09/06/2016.
 */
public class FCMResponse {

    @SerializedName("multicast_id")
    private String multicastId;
    private Integer success;
    private Integer failure;
    @SerializedName("canonical_ids")
    private Integer canonicalIds;
//    private Map<String, String> results;
//    @Expose(serialize = false)
    private List<ResultPartial> results = new ArrayList<ResultPartial>();
    @SerializedName("message_id")
    private String messageId;
    private String error;

    public String getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(String multicastId) {
        this.multicastId = multicastId;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getCanonicalIds() {
        return canonicalIds;
    }

    public void setCanonicalIds(Integer canonicalIds) {
        this.canonicalIds = canonicalIds;
    }

//    public Map<String, String> getResults() {
//        return results;
//    }

//    public void setResults(Map<String, String> results) {
//        this.results = results;
//    }

    public List<ResultPartial> getResults() {
        return results;
    }

    public void setResults(List<ResultPartial> results) {
        this.results = results;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
