package mx.bbva.intranet.utilities.fcm;

import mx.bbva.intranet.utilities.fcm.clients.FCMClient;
import mx.bbva.intranet.utilities.fcm.constants.FCMOperation;
import mx.bbva.intranet.utilities.fcm.exceptions.*;
import mx.bbva.intranet.utilities.fcm.vos.fcm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BBVA on 15/06/2016.
 * OGG
 */
public abstract class FCMUtility {

    private static final Logger logger = LoggerFactory.getLogger(FCMUtility.class);

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";

    public static void sendPushNotification(FCMClient fcmClient, FCMNotification fcmNotification) throws FCMPushNotificationException, FCMException {
        FCMResponse fcmResponse = fcmClient.sendPushNotification(fcmNotification);
        if (fcmResponse != null) {
            if (fcmResponse.getFailure() != null && fcmResponse.getFailure() > 0) {
                String errorList = "";
                for (ResultPartial resultPartial : fcmResponse.getResults()) {
                    errorList = String.format("%s %s %s %s. ", errorList, resultPartial.getMessageId(), resultPartial.getRegistrationId(), resultPartial.getError());
                }
                String errorMsg = String.format("Error list: [%s]", errorList);
                throw new FCMPushNotificationException(errorMsg);
            }
        }
    }

    public static String createDeviceGroup(FCMClient fcmClient, String notificationKeyName, List<String> instanceIds) throws FCMGroupException, FCMException {
        String notificationKey;
        FCMDeviceGroup fcmDeviceGroup = buildDeviceGroup(notificationKeyName, FCMOperation.CREATE_GROUP, instanceIds, null);
        fcmDeviceGroup = fcmClient.invokeFCMGroupService(fcmDeviceGroup);
        if (fcmDeviceGroup.getNotificationKey() == null && fcmDeviceGroup.getNotificationKey().isEmpty()) {
            throw new FCMGroupException(String.format("FCM Group operation could not be done %s", fcmDeviceGroup.toString()));
        } else {
            notificationKey = fcmDeviceGroup.getNotificationKey();
            return notificationKey;
        }
    }

    public static String addDeviceGroup(FCMClient fcmClient, String notificationKeyName, String notificationKey, List<String> instanceIds) throws FCMGroupException, FCMException {
        FCMDeviceGroup fcmDeviceGroup = buildDeviceGroup(notificationKeyName, FCMOperation.ADD_TO_GROUP, instanceIds, notificationKey);
        fcmDeviceGroup = fcmClient.invokeFCMGroupService(fcmDeviceGroup);
        if (fcmDeviceGroup.getNotificationKey() == null && fcmDeviceGroup.getNotificationKey().isEmpty()) {
            throw new FCMGroupException(String.format("FCM Group operation could not be done %s", fcmDeviceGroup.toString()));
        } else {
            notificationKey = fcmDeviceGroup.getNotificationKey();
            return notificationKey;
        }
    }

    public static String removeDeviceGroup(FCMClient fcmClient, String notificationKeyName, String notificationKey, List<String> instanceIds) throws FCMGroupException, FCMException {
        FCMDeviceGroup fcmDeviceGroup = buildDeviceGroup(notificationKeyName, FCMOperation.REMOVE_OF_GROUP, instanceIds, notificationKey);
        fcmDeviceGroup = fcmClient.invokeFCMGroupService(fcmDeviceGroup);
        if (fcmDeviceGroup.getNotificationKey() == null && fcmDeviceGroup.getNotificationKey().isEmpty()) {
            throw new FCMGroupException(String.format("FCM Group operation could not be done %s", fcmDeviceGroup.toString()));
        } else {
            notificationKey = fcmDeviceGroup.getNotificationKey();
            return notificationKey;
        }
    }

    /**
     * TODO: The return value it's not the correct, must be something like boolean or other object...
     */
    private static FCMDeviceGroup buildDeviceGroup(String notificationKeyName, String operation,
                                                  List<String> registrationIds, String notificationKey) {
        FCMDeviceGroup fcmDeviceGroup;
        if (notificationKey != null) {
            fcmDeviceGroup = new FCMDeviceGroup(operation, notificationKeyName, registrationIds, notificationKey);
        } else {
            fcmDeviceGroup = new FCMDeviceGroup(operation, notificationKeyName, registrationIds);
        }
        return fcmDeviceGroup;
    }

//    private static void validateDeviceGroup(FCMDeviceGroup fcmDeviceGroup) throws FCMGroupException {
//        String error;
//        if (fcmDeviceGroup != null) {
////            if (fcmDeviceGroup.getNotificationKey() == null) {
//            if (fcmDeviceGroup.getError() != null) {
//                if (fcmDeviceGroup.getNotificationKey() != null) {
//                    error = String.format("%s: notification_key_name [%s] and notification_key [%s].", fcmDeviceGroup.getError(),
//                            fcmDeviceGroup.getNotificationKeyName(), fcmDeviceGroup.getNotificationKey());
//                } else {
//                    error = String.format("%s: notification_key_name [%s], notification_key LOST.", fcmDeviceGroup.getError(), fcmDeviceGroup.getNotificationKeyName());
//                }
//                throw new FCMGroupException(error);
//            }
////            }
//        } else {
//            error = "NO response received!.";
//            throw new FCMGroupException(error);
//        }
//    }

    public static void invokeTopicRelationship(FCMClient fcmClient, FCMTopic fcmTopic) throws FCMTopicException, FCMException {
        if (fcmTopic.getInstanceId() != null && !fcmTopic.getInstanceId().isEmpty()) {
            fcmTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstance(fcmTopic);
        } else if (fcmTopic.getRegistrationTokens() != null && !fcmTopic.getRegistrationTokens().isEmpty()) {
            fcmTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstances(fcmTopic);
        }
        if (fcmTopic != null && fcmTopic.getError() != null) {
            throw new FCMTopicException(fcmTopic.getError());
        }
    }

    public static boolean verifyIsAssignedToTopic(FCMClient fcmClient, String registrationId, String topicName) throws FCMInstanceIdException, FCMInstanceIdEmptyException, FCMException {
        // Verify if the User registrationId already exists in the Topic
        logger.info(String.format("Checking if exists in the Topic [%s]", topicName));
        if (registrationId != null && !registrationId.isEmpty()) {
            FCMAppInstanceInfo fcmAppInstanceInfo = fcmClient.invokeServiceAppInstanceInfo(registrationId, true);
            boolean existUserIntoTopic = false;
            if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getRel() != null) {
                for (String rel : fcmAppInstanceInfo.getRel().keySet()) {
                    if (rel != null && rel.equalsIgnoreCase("topics")) {
                        for (String topic : fcmAppInstanceInfo.getRel().get(rel).keySet()) {
                            if (topic != null && topic.equalsIgnoreCase(topicName)) {
                                existUserIntoTopic = true;
                                logger.info(String.format("Exists into the Topic [%s] the registrationId [%s]", topic, registrationId));
                            }
                        }
                    }
                }
            } else if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getError() != null) {
                throw new FCMInstanceIdException(fcmAppInstanceInfo.getError());
            }
            return existUserIntoTopic;
        } else {
            throw new FCMInstanceIdEmptyException("The registrationId is null or empty. Check the FCM registration service.");
        }
    }

    public static List<String> cleanInstanceIdOfTopics(FCMClient fcmClient, String registrationId) throws FCMInstanceIdException, FCMInstanceIdEmptyException, FCMException {
        List<String> unsubscribeTopics = new ArrayList<String>();
        logger.info(String.format("The registrationId [%s] will be removed of whole Topics subscribed...", registrationId));
        if (registrationId != null && !registrationId.isEmpty()) {
            FCMAppInstanceInfo fcmAppInstanceInfo = fcmClient.invokeServiceAppInstanceInfo(registrationId, true);
            if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getRel() != null) {
                for (String rel : fcmAppInstanceInfo.getRel().keySet()) {
                    if (rel != null && rel.equalsIgnoreCase("topics")) {
                        for (String topic : fcmAppInstanceInfo.getRel().get(rel).keySet()) {
                            logger.info(String.format("The registrationId [%s] will be removed from Topic [%s]", registrationId, topic));
//                            if (removeInstanceIdFromTopic(fcmClient, registrationId, topic)) {
//                                unsubscribeTopics.add(topic);
//                            }
                            try {
                                removeInstanceIdFromTopic(fcmClient, registrationId, topic);
                                unsubscribeTopics.add(topic);
                            } catch (FCMTopicException e) {
                                logger.warn(String.format("Registration Id %s was unable to be deleted from topic %s", registrationId, topic));
                            }
                        }
                    }
                }
            } else if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getError() != null) {
                throw new FCMInstanceIdException(fcmAppInstanceInfo.getError());
            }
            return unsubscribeTopics;
        } else {
            throw new FCMInstanceIdEmptyException("The registrationId is null or empty. Check the FCM registration service.");
        }
    }

    public static Map<String, List<String>> getTopicsOfInstanceId(FCMClient fcmClient, String registrationId) throws FCMInstanceIdException, FCMInstanceIdEmptyException, FCMException {
        Map<String, List<String>> resume = new HashMap<>();
        if (registrationId != null && !registrationId.isEmpty()) {
            logger.info(String.format("The registrationId [%s] will check their subscribed Topics...", registrationId));
            FCMAppInstanceInfo fcmAppInstanceInfo = fcmClient.invokeServiceAppInstanceInfo(registrationId, true);
            if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getRel() != null) {
                for (String rel : fcmAppInstanceInfo.getRel().keySet()) {
                    if (rel != null && rel.equalsIgnoreCase("topics")) {
//                        resume.addAll(fcmAppInstanceInfo.getRel().get(rel).keySet());
                        List<String> topics = new ArrayList<>(fcmAppInstanceInfo.getRel().get(rel).keySet());
                        resume.put(registrationId, topics);
                    }
                }
            } else if (fcmAppInstanceInfo != null && fcmAppInstanceInfo.getError() != null) {
                throw new FCMInstanceIdException(fcmAppInstanceInfo.getError());
            }
            return resume;
        } else {
            throw new FCMInstanceIdEmptyException("The registrationId is null or empty. Check the FCM registration service.");
        }
    }

    public static void removeInstanceIdFromTopic(FCMClient fcmClient, String registrationId, String topic) throws FCMInstanceIdEmptyException, FCMTopicException, FCMException {
//        boolean done = false;
        if (registrationId != null && !registrationId.isEmpty()) {
            List<String> registrationIds = new ArrayList<String>();
            registrationIds.add(registrationId);
            FCMTopic fcmTopic = new FCMTopic(FCMOperation.REMOVE_BATCH_OF_TOPIC, topic, registrationIds);
            FCMTopic responseFCMTopic;
            responseFCMTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstances(fcmTopic);
            if (responseFCMTopic.getResults() != null && !responseFCMTopic.getResults().isEmpty()) {
                logger.info(String.format("registrationId [%s] removed from Topic [%s]", registrationId, topic));
//                done = true;
            } else {
                throw new FCMTopicException(String.format("InstanceIds %s were not been added to the topic %s", registrationIds.toString(), topic));
            }
//            return done;
        } else {
            throw new FCMInstanceIdEmptyException("The registrationId is null or empty. Check the FCM registration service.");
        }
    }

    public static void removeInstanceIdsFromTopic(FCMClient fcmClient, List<String> instanceIds, String topic) throws FCMInstanceIdEmptyException, FCMTopicException, FCMException {
//        boolean done = false;
        if (instanceIds != null && !instanceIds.isEmpty()) {
            FCMTopic fcmTopic = new FCMTopic(FCMOperation.REMOVE_BATCH_OF_TOPIC, topic, instanceIds);
            FCMTopic responseFCMTopic;
            responseFCMTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstances(fcmTopic);
            if (responseFCMTopic.getResults() != null && !responseFCMTopic.getResults().isEmpty()) {
                logger.info(String.format("instanceIds [%s] removed from Topic [%s]", instanceIds, topic));
//                done = true;
            } else {
                throw new FCMTopicException(String.format("InstanceIds %s were not been removed to the topic %s", instanceIds.toString(), topic));
            }
//            return done;
        } else {
            throw new FCMInstanceIdEmptyException("The instanceIds is null or empty. Check the FCM registration service.");
        }
    }

    public static void addInstanceIdFromTopic(FCMClient fcmClient, String registrationId, String topic) throws FCMInstanceIdEmptyException, FCMTopicException, FCMException {
//        boolean done = false;
        if (registrationId != null && !registrationId.isEmpty()) {
            List<String> registrationIds = new ArrayList<String>();
            registrationIds.add(registrationId);
            FCMTopic fcmTopic = new FCMTopic(FCMOperation.ADD_BATCH_TO_TOPIC, topic, registrationIds);
            FCMTopic responseFCMTopic;
            responseFCMTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstances(fcmTopic);
            // TODO: It's required to manage the results Array for each of the response by registrationId
            if (responseFCMTopic.getResults() != null && !responseFCMTopic.getResults().isEmpty()) {
                logger.info(String.format("registrationId [%s] added from Topic [%s]", registrationId, topic));
//                done = true;
            } else {
                throw new FCMTopicException(String.format("InstanceIds %s were not been added to the topic %s", registrationIds.toString(), topic));
            }
//            return done;
        } else {
            throw new FCMInstanceIdEmptyException("The registrationId is null or empty. Check the FCM registration service.");
        }
    }

    public static void addInstanceIdsFromTopic(FCMClient fcmClient, List<String> instanceIds, String topic) throws FCMInstanceIdEmptyException, FCMTopicException, FCMException {
//        boolean done = false;
        if (instanceIds != null && !instanceIds.isEmpty()) {
            FCMTopic fcmTopic = new FCMTopic(FCMOperation.ADD_BATCH_TO_TOPIC, topic, instanceIds);
            FCMTopic responseFCMTopic;
            responseFCMTopic = fcmClient.invokeFCMTopicRelationshipWithAppInstances(fcmTopic);
            if (responseFCMTopic.getResults() != null && !responseFCMTopic.getResults().isEmpty()) {
                logger.info(String.format("instanceIds [%s] added from Topic [%s]", instanceIds.toString(), topic));
//                done = true;
            } else {
                throw new FCMTopicException(String.format("InstanceIds %s were not been added to the topic %s", instanceIds.toString(), topic));
            }
//            return done;
        } else {
            throw new FCMInstanceIdEmptyException("The instanceIds is null or empty. Check the FCM registration service.");
        }
    }

//    public static void assignToTopic(GCMClient gcmClient, String registrationId, String topicName) {
//        // If doesn't exists... Process to add User into Topic
//        FCMTopic gcmTopic = new FCMTopic(topicName, registrationId);
//        FCMUtility.invokeTopicRelationship(gcmClient, gcmTopic);
//        topicNames.add(topicName);
//        // Adds the topicName
//        logger.info(String.format("User [%s] add to topic [%s]", user.retrieveCurrentKey(), topicName));
//    }


//    public static void validateGCMTopicRelationship(FCMTopic gcmTopic) throws FCMTopicException {
//        if (gcmTopic.getError() != null) {
//            throw new FCMTopicException(response);
//        }
////        boolean done = false;
////        if (response.equalsIgnoreCase("{}")) {
////            done = true;
////        } else {
////            throw new FCMTopicException(response);
////        }
////        return done;
//    }

}
