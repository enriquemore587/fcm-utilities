package mx.bbva.intranet.utilities.fcm.clients;

import com.bbva.intranet.utilities.GsonUtility;
import com.bbva.intranet.utilities.HttpClientSimpleUtility;
import com.bbva.intranet.utilities.exceptions.HttpClientException;
import com.bbva.intranet.utilities.vos.HttpClientData;
import com.bbva.intranet.utilities.vos.HttpResponse;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMException;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMPushNotificationException;
import mx.bbva.intranet.utilities.fcm.vos.fcm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.util.Map;

/**
 * Created by BBVA on 08/06/2016.
 */
@Service
public class FCMClient {

    private static final Logger logger = LoggerFactory.getLogger(FCMClient.class);

    private String contentType;
    private String encoding;
    private Integer timeout;
    private Map<String, String> headers;

    private String urlNotificationSend;
    private String urlNotificationGroup;
    private String urlAppInstanceInfo;
    private String urlTopicRelationshipAppInstance;
    private String urlTopicBatch;

    public FCMResponse sendPushNotification(FCMNotification fcmNotification) throws FCMException, FCMPushNotificationException {
        FCMResponse fcmResponse = null;
        try {
            HttpResponse httpResponse;

            // Se prepara la informacion necesaria para enviar la notificacion segun el Payload de GCM
            String payload = GsonUtility.objectToJson(fcmNotification);
            httpResponse = buildFCMClientRequest(urlNotificationSend, payload);

            logger.info(httpResponse.getOutput());

            // Se evalua la respuesta recuperada del Servicio de GCM
            if (httpResponse.getCode() == 200 && httpResponse.getContentType().contains("application/json")) {
                fcmResponse = (FCMResponse) GsonUtility.jsonToObject(httpResponse.getOutput(), null, FCMResponse.class);
            } else {
                throw new FCMPushNotificationException(String.format("Push notification cannot be sent. code [%d] message [%s]",
                        httpResponse.getCode(), httpResponse.getMessage()));
            }
        } catch (HttpClientException e) {
            throw new FCMException(e.getMessage());
        }

        return fcmResponse;
    }

    public FCMAppInstanceInfo invokeServiceAppInstanceInfo(String instanceId, boolean details) throws FCMException {
        FCMAppInstanceInfo fcmAppInstanceInfo = null;
        try {
            HttpResponse httpResponse;

            ST urlRender = new ST(urlAppInstanceInfo, '{', '}');
            urlRender.add("IID_TOKEN", instanceId);
            if (details) {
                urlRender.add("params", "?details=true");
            }
            String url = urlRender.render();
            logger.info(String.format("URL to invoke [%s]", url));
            httpResponse = buildFCMClientRequest(url, null);

            logger.info(httpResponse.getOutput());

            fcmAppInstanceInfo = validateFCMAppInstanceInfoCodeResponse(httpResponse);

        } catch (HttpClientException e) {
            throw new FCMException(e.getMessage());
        }
        return fcmAppInstanceInfo;
    }

    public FCMTopic invokeFCMTopicRelationshipWithAppInstance(FCMTopic fcmTopic) throws FCMException {
        FCMTopic fcmTopicResult = null;
        try {
            HttpResponse httpResponse;

            ST urlRender = new ST(urlTopicRelationshipAppInstance, '{', '}');
            urlRender.add("IID_TOKEN", fcmTopic.getInstanceId());
            urlRender.add("TOPIC_NAME", fcmTopic.getTo());
            String url = urlRender.render();
            logger.info(String.format("URL to invoke [%s]", url));
            httpResponse = buildFCMClientRequest(url, "");

            logger.info(httpResponse.getOutput());

            fcmTopicResult = validateFCMTopicCodeResponse(httpResponse);
        } catch (HttpClientException e) {
            throw new FCMException(e.getMessage());
        }
        return fcmTopicResult;
    }

    public FCMTopic invokeFCMTopicRelationshipWithAppInstances(FCMTopic fcmTopic) throws FCMException {
        FCMTopic fcmTopicResult = null;
        try {
            HttpResponse httpResponse;

            String url = String.format("%s%s", urlTopicBatch, fcmTopic.getOperation());
            logger.info(String.format("URL to invoke [%s]", url));
            fcmTopic.setTo(String.format("/topics/%s", fcmTopic.getTo()));
            String payload = GsonUtility.objectToJson(fcmTopic);
            httpResponse = buildFCMClientRequest(url, payload);

            logger.info(httpResponse.getOutput());

            fcmTopicResult = validateFCMTopicCodeResponse(httpResponse);
        } catch (HttpClientException e) {
            throw new FCMException(e.getMessage());
        }
        return fcmTopicResult;
    }

    public FCMDeviceGroup invokeFCMGroupService(FCMDeviceGroup fcmDeviceGroup) throws FCMException {
        FCMDeviceGroup fcmDeviceGroupResponse = null;
        try {
            HttpResponse httpResponse;

            // Se prepara la informacion necesaria para enviar la notificacion segun el Payload de GCM
            String payload = GsonUtility.objectToJson(fcmDeviceGroup);
            httpResponse = buildFCMClientRequest(urlNotificationGroup, payload);

            logger.info(httpResponse.getOutput());
            // Se evalua la respuesta recuperada del Servicio de GCM
            fcmDeviceGroupResponse = (FCMDeviceGroup) GsonUtility.jsonToObject(httpResponse.getOutput(), null, FCMDeviceGroup.class);
            if (fcmDeviceGroupResponse != null) {
                fcmDeviceGroupResponse.setNotificationKeyName(fcmDeviceGroup.getNotificationKeyName());
                fcmDeviceGroupResponse.setOperation(fcmDeviceGroup.getOperation());
            }
        } catch (HttpClientException e) {
            throw new FCMException(e.getMessage());
        }

        return fcmDeviceGroupResponse;
    }

    private FCMTopic validateFCMTopicCodeResponse(HttpResponse httpResponse) {
        FCMTopic fcmTopicResult;
        if (httpResponse.getCode() == 200) {
            fcmTopicResult = (FCMTopic) GsonUtility.jsonToObject(httpResponse.getOutput(), null, FCMTopic.class);
        } else {
            fcmTopicResult = new FCMTopic();
            String errorMsg = String.format("Response Error: %s [%s] : %s", httpResponse.getMessage(),
                    httpResponse.getCode(), httpResponse.getOutput());
            logger.error(errorMsg);
            fcmTopicResult.setError(String.format("Cannot add or remove to a Topic: %s", errorMsg));
        }
        return fcmTopicResult;
    }

    private FCMAppInstanceInfo validateFCMAppInstanceInfoCodeResponse(HttpResponse httpResponse) {
        FCMAppInstanceInfo fcmAppInstanceInfo;
        if (httpResponse.getCode() == 200) {
            fcmAppInstanceInfo = (FCMAppInstanceInfo) GsonUtility.jsonToObject(httpResponse.getOutput(), null, FCMAppInstanceInfo.class);
//        } else if (httpResponse.getCode() != 200 || httpResponse.getContentType().contains("text/html")) {
        } else {
            fcmAppInstanceInfo = new FCMAppInstanceInfo();
            String errorMsg = String.format("Response Error: %s [%s] : %s", httpResponse.getMessage(),
                    httpResponse.getCode(), httpResponse.getOutput());
            logger.error(errorMsg);
            fcmAppInstanceInfo.setError(String.format("Cannot get info of InstanceID: %s", errorMsg));
        }
        return fcmAppInstanceInfo;
    }

    private HttpResponse buildFCMClientRequest(String url, String payload) throws HttpClientException {
        HttpClientData httpClientData;

        if (payload == null) {
            // Assign the URL of the service to call
            httpClientData = new HttpClientData(url);
        } else {
            // Se prepara la informacion necesaria para enviar la notificacion segun el Payload de GCM
            httpClientData = new HttpClientData(url, payload);
        }
        httpClientData.setContentType(contentType);
        httpClientData.setEncoding(encoding);
        httpClientData.setTimeout(timeout);
        logger.info(String.format("Headers: %s", headers.toString()));
        httpClientData.setHeaders(headers);

        // Se invoca al Servicio de GCM para envio de Notificaciones
        HttpResponse httpResponse;
        if (payload == null) {
            httpResponse = HttpClientSimpleUtility.buildRequestGet(httpClientData);
        } else {
            httpClientData.setPayload(payload);
            httpResponse = HttpClientSimpleUtility.buildRequestPost(httpClientData);
        }

        return httpResponse;
    }

    public void setUrlNotificationSend(String urlNotificationSend) {
        this.urlNotificationSend = urlNotificationSend;
    }

    public void setUrlNotificationGroup(String urlNotificationGroup) {
        this.urlNotificationGroup = urlNotificationGroup;
    }

    public void setUrlAppInstanceInfo(String urlAppInstanceInfo) {
        this.urlAppInstanceInfo = urlAppInstanceInfo;
    }

    public void setUrlTopicRelationshipAppInstance(String urlTopicRelationshipAppInstance) {
        this.urlTopicRelationshipAppInstance = urlTopicRelationshipAppInstance;
    }

    public void setUrlTopicBatch(String urlTopicBatch) {
        this.urlTopicBatch = urlTopicBatch;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
