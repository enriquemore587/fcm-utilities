package mx.bbva.intranet.utilities.fcm;

import mx.bbva.intranet.utilities.fcm.clients.FCMClient;
import mx.bbva.intranet.utilities.fcm.vos.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Omar on 12/19/16.
 */
public abstract class UserFCMUtility {

    private static final Logger logger = LoggerFactory.getLogger(UserFCMUtility.class);

//    public static Response addUserToGCMTopic(String instanceID /*registrationID*/, List<String> topics, FCMClient fcmClient) {
//        Response response;
//
//        if (topics != null && !topics.isEmpty()) {
//            for (String newGroup : topics) {
//                // Check the TopicName
//                String topicName;
//                boolean existTopic = newGroup.existTopic();
//                if (existTopic) {
//                    topicName = newGroup.getTopicName();
//                } else {
//                    topicName = GCMTopicUtility.buildTopicName(newGroup.getName());
//                }
//
//                try {
//                    boolean existUserIntoTopic;
//                    // Verify if the User registrationId already exists in the Topic
//                    existUserIntoTopic = FCMUtility.verifyIsAssignedToTopic(fcmClient, user.getRegistrationId(), topicName);
//                    // If doesn't exists... Process to add User into Topic
//                    if (!existUserIntoTopic) {
//                        GCMTopic gcmTopic = new GCMTopic(topicName, user.getRegistrationId());
//                        FCMUtility.invokeTopicRelationship(fcmClient, gcmTopic);
//                        String msgTopic = String.format("User [%s] added to Topic [%s]", user.retrieveCurrentKey(), topicName);
//                        logger.info(msgTopic);
//                        messages.put("OK-FCM-Added", msgTopic);
//                    } else {
//                        String msgTopic = String.format("User [%s] already exists into the Topic [%s]", user.retrieveCurrentKey(), topicName);
//                        logger.info(msgTopic);
//                        messages.put("OK-FCM-Existed", msgTopic);
//                    }
//                } catch (GCMInstanceIdEmptyException e) {
//                    String msg = String.format("User [%s] could not be added to any Topic, registrationId is null or empty.", user.retrieveCurrentKey());
//                    logger.warn(msg);
//                    result.put("InstanceID", "Servicio de Notificaciones no está disponible. Intente más tarde.");
//                } catch (GCMInstanceIdException | GCMTopicException e) {
//                    logger.warn(e.getMessage());
//                    messages.put("KO-FCM", e.getMessage());
//                }
//
//                // Check if the User is already associated with the Group with the current Topic
//                if (!user.getGroups().contains(newGroup)) {
//                    // Adds the new Group to the User
//                    user.getGroups().add(newGroup);
//                    String msgGroup = String.format("User [%s] added to the Group [%s]", user.retrieveCurrentKey(), newGroup.getName());
//                    logger.info(msgGroup);
//                    messages.put("OK-Group-Added", msgGroup);
//                } else {
//                    // add message
//                    String msgGroup = String.format("User [%s] already exists into the Group [%s]", user.retrieveCurrentKey(), newGroup.getName());
//                    logger.info(msgGroup);
//                    messages.put("OK-Group-Exists", msgGroup);
//                }
//
//                // If the TopicName is new is added to the Group and Upgraded
//                if (!existTopic) {
//                    newGroup.setTopicName(topicName);
//                    groupServiceDAO.update(newGroup);
//                }
//                result.put(String.format("For the Topic [%s]", topicName), messages);
//            }
////            response = new AppCNResponse("OK", result);
//        } else {
////            response = new AppCNResponse("OK", String.format("User [%s] updated, but there is not any Group to be add.", user.retrieveCurrentKey()));
//        }
//
//        return response;
//    }
//
//    public static AppCNResponse removeUserFromGCMTopic(User user, List<Group> removeGroups, FCMClient fcmClient, GroupServiceDAO groupServiceDAO) {
////    public static AppCNResponse removeUserFromGCMTopic(User user, List<Group> removeGroups, GCMClient gcmClient, List<Group> groupsDB) {
//        AppCNResponse appCNResponse;
//        Map<String, Object> result = new HashMap<>();
////        boolean isRegistrationIdIsNull = false;
//
//        if (removeGroups != null && !removeGroups.isEmpty()) {
//            for (Group removeGroup : removeGroups) {
//                Map<String, String> messages = new HashMap<>();
//                // Check the TopicName
//                String topicName;
////                boolean existTopic = removeGroup.getTopicName() != null;
//                boolean existTopic = removeGroup.existTopic();
//                if (existTopic) {
//                    topicName = removeGroup.getTopicName();
//                } else {
//                    topicName = GCMTopicUtility.buildTopicName(removeGroup.getName());
//                }
//
//                // Verify if the User registrationId already exists in the Topic
//                boolean existUserIntoTopic;
//
////                if (!isRegistrationIdIsNull) {
//                try {
//                    existUserIntoTopic = FCMUtility.verifyIsAssignedToTopic(fcmClient, user.getRegistrationId(), topicName);
//                    // If User exists into the Topic is removed
//                    if (existUserIntoTopic) {
//                        // Creates the List of registrationIds to remove...
//                        List<String> registrationTokens = new ArrayList<>();
//                        registrationTokens.add(user.getRegistrationId());
//
//                        // Invokes the GCM Services to remove from a Topic
//                        GCMTopic gcmTopic = new GCMTopic(GCMOperation.REMOVE_BATCH_OF_TOPIC, topicName, registrationTokens);
//                        FCMUtility.invokeTopicRelationship(fcmClient, gcmTopic);
//
//                        String msgTopic = String.format("User [%s] removed from topic [%s]", user.retrieveCurrentKey(), topicName);
////                        logger.info(String.format("User [%s] removed from topic [%s]", user.retrieveCurrentKey(), topicName));
//                        logger.info(msgTopic);
//                        messages.put("OK-FCM-Removed", msgTopic);
//                    } else {
//                        String msgTopic = String.format("User [%s] already removed from the Topic [%s]", user.retrieveCurrentKey(), topicName);
//                        logger.info(msgTopic);
//                        messages.put("OK-FCM-NoExists", msgTopic);
//                    }
//                } catch (GCMInstanceIdEmptyException e) {
//                    // If the registrationId is not null,
////                        isRegistrationIdIsNull = true;
//                    String msg = String.format("User [%s] could not be added to any Topic, registrationId is null or empty.", user.retrieveCurrentKey());
//                    logger.warn(msg);
//                    result.put("InstanceID", "Servicio de Notificaciones no está disponible. Intente más tarde.");
//                } catch (GCMInstanceIdException | GCMTopicException e) {
//                    logger.warn(e.getMessage());
//                    messages.put("KO-FCM", e.getMessage());
//                }
////                }
//                // Check if the User already is associated to the Group, so is removed
//                if (user.getGroups().contains(removeGroup)/* && !registrationIdIsNull*/) {
//                    // Removes the Group to the User
//                    user.getGroups().remove(removeGroup);
//                    String msgGroup = String.format("User [%s] removed to the Group with topic [%s]", user.retrieveCurrentKey(), topicName);
//                    logger.info(msgGroup);
//                    messages.put("OK-Group-Removed", msgGroup);
//                } else {
//                    // add message
//                    String msgGroup = String.format("User [%s] already removed from the Group [%s]", user.retrieveCurrentKey(), removeGroup.getName());
//                    logger.info(msgGroup);
//                    messages.put("OK-Group-NoExists", msgGroup);
//                }
//
//                // If the TopicName is new is added to the Group and Upgraded
//                if (!existTopic) {
//                    removeGroup.setTopicName(topicName);
//                    groupServiceDAO.update(removeGroup);
//                }
//                result.put(String.format("For the Topic [%s]", topicName), messages);
//            }
//            appCNResponse = new AppCNResponse("OK", result);
//        } else {
//            appCNResponse = new AppCNResponse("OK", String.format("User [%s] updated, but there is not any Group to be add.", user.retrieveCurrentKey()));
//        }
//
//        return appCNResponse;
//    }
//
//    public static List<String> addUserToGCMGroups(User user, List<Group> groups, FCMClient fcmClient) {
//        List<String> errors = new ArrayList<>();
//        List<String> registrationIds = new ArrayList<>();
//        GCMDeviceGroup gcmDeviceGroup;
//
//        for (Group group : groups) {
//            // Fill registrationIds object
//            registrationIds.add(user.getRegistrationId());
//
//            // Check if the Group has a NotificationID
//            if (group.getNotificationKey() != null && !group.getNotificationKey().isEmpty()) {
//                gcmDeviceGroup = FCMUtility.buildDeviceGroup(group.getName(), GCMOperation.ADD_TO_GROUP,
//                        registrationIds, group.getNotificationKey());
//            } else {
//                gcmDeviceGroup = FCMUtility.buildDeviceGroup(group.getName(), GCMOperation.CREATE_GROUP,
//                        registrationIds, group.getNotificationKey());
//            }
//
//            // Invokes the GCM Group operation and retrieves the NotificationKey generated
//            gcmDeviceGroup = fcmClient.invokeFCMGroupService(gcmDeviceGroup);
//            try {
//                // Validate the response of GCM
//                FCMUtility.validateDeviceGroup(gcmDeviceGroup);
//                // If didn't throws the FCMGroupException...
//                // the notification_key of Group is assign
//                group.setNotificationKey(gcmDeviceGroup.getNotificationKey());
//            } catch (GCMGroupException e) {
//                errors.add(e.getMessage());
//            }
//        }
//
//        return errors;
//    }

}
