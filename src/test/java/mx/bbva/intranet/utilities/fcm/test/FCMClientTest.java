package mx.bbva.intranet.utilities.fcm.test;

import mx.bbva.intranet.utilities.fcm.FCMUtility;
import mx.bbva.intranet.utilities.fcm.clients.FCMClient;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMGroupException;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMInstanceIdEmptyException;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMPushNotificationException;
import mx.bbva.intranet.utilities.fcm.exceptions.FCMTopicException;
import mx.bbva.intranet.utilities.fcm.vos.fcm.FCMAppInstanceInfo;
import mx.bbva.intranet.utilities.fcm.vos.fcm.FCMNotification;
import mx.bbva.intranet.utilities.fcm.vos.fcm.FCMTopic;
import mx.bbva.intranet.utilities.fcm.vos.fcm.NotificationPartial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FCMClientTest {

    public static void main(String... args) {
//        testSendNotification();

//        testRemoveFromTopic();
//        testAddFromTopic();
//        testInstanceIDInfo();

//        testCreateGroup();

        testAddNotificationKeyToTopic();
    }

    public static FCMClient initFCMClient() {
        FCMClient fcmClient = new FCMClient();
        fcmClient.setContentType("application/json");
        fcmClient.setEncoding("UTF-8");
        fcmClient.setTimeout(30000);
        Map<String, String> headers = new HashMap<>();
        // AU AppCN
//        headers.put("Authorization", "key=AAAA-4Mzz4g:APA91bGDmzE7wicFLuFy2kRUM9Ku9Sm0_HQiq1Xs9JsNSR9Ursp32-ML6ndT8wcxeF2KKvjDJIGTQzIH_i6xpN4L7NJP6RFGJhy0NzFAs7uAQMwGHWijQmkSx3AI7-9vpQxwavmhlgGm");
//        headers.put("project_id", "1080238002056");
        // PROD AppCN
//        headers.put("Authorization", "key=AAAA8SyalZA:APA91bF2Hx8yKaBpVgb2xwkgIBlDy2StKDa59oAlHQBVoPEreRzGVox1_BlNN9vGV_jet-Bl1EX8FX-gmu0lpvYX8jli7sU3QSBfVkUgp37Rhyr2h4TWlF2FYmSFBZ_1I0Kqco5Pb-08");
//        headers.put("project_id", "1035835446672");
        // AU UneT
        headers.put("Authorization", "key=AAAAFjDPqYA:APA91bHRfUnfRmbvzGTG-A2NMWKgGmQk7X_6Bs9hRvjuM4u5085t2PxhqjNk04jE7SnWFgiRAzXbBwU9-9gQYeZLAaSj-IvTs6TczTE8uKHIE7zgzs3A1fPK5DER6O_c42eieuvZWpPk");
        headers.put("project_id", "95308196224");
        // PROD UneT
//        headers.put("Authorization", "key=AAAAFNKBhds:APA91bGAuPNgcLcjeIuWkpjOZ7pJw04T57t0TssMzyrQwvddbiSwdeRhs0BOg4Hd9qM0REjRdopWvG4_YxEEug_Z_ik5-VPjRn5SRQsehmKNFZct-Rm_-RpPDMNN5sQi-efIcnjnWnYr");
////        headers.put("project_id", "89431049691");
        fcmClient.setHeaders(headers);

        fcmClient.setUrlNotificationSend("https://fcm.googleapis.com/fcm/send");
        fcmClient.setUrlAppInstanceInfo("https://iid.googleapis.com/iid/info/{IID_TOKEN}{params}");
        fcmClient.setUrlTopicBatch("https://iid.googleapis.com/iid/v1:");
        fcmClient.setUrlNotificationGroup("https://fcm.googleapis.com/fcm/notifier");

        return fcmClient;
    }

    public static void testSendNotification() {
        FCMClient fcmClient = initFCMClient();

        FCMNotification fcmNotification = new FCMNotification();
//        fcmNotification.setTo("dxomWsWaXKM:APA91bE8pz4OiUtz5QhgLoIL0Yc-BQ_5EXls1bAb4YccwQzuA98f7GVikr2pHNR9LbhA3HqQ55m9xOgy0cWBr6-B4DTIGfPQSkK-WiEVBJ_9LAS6_QjsZCaqyZwKnCegPYDNo8g94dBX");
//        fcmNotification.setTo("cqAJaXEDMAA:APA91bFFwOUPUq5n03LAF78BmzB_mZjqIYTEnLqsMg769Zp8R9U9A5mEPG5Yg1YyTa4q_l5qgZB52RGfEAyBRK0-ztbibamCdsIRpK7TCk5y_Ffk_4aH7p9sTBLPuy6zFCtoYmsd2OUv");
        // YO
//        fcmNotification.setTo("cr--ouTuPj8:APA91bHAKFsyRDPmWDHylTT29S7wyRZW7elBNPHiX66Fxtwg4PrE-9xXtHmixPBgHoHtr3A5_1A8xPQnTunEsRafb6rO41LLKVdnKwhxsraajG_pjeVfQ5wr6AroGdCKyW2jN7kqMrvt");
        // David
//        fcmNotification.setTo("f72pEV7adSU:APA91bF2BN3r5aOeDBIS7xC6nlgu3q99izwI4V1BA2oDG1_OFSnw83Z9jfSj0L1fZ0ct3yL8DB5EldCHyZiTXC0gyecYIIAFw2wLHmD4PzlSKDbSdj3adnzezsvNy1bceDMCjHRMkQxC");
        // Toño
//        fcmNotification.setTo("");
        // TOPIC GENERAL
        fcmNotification.setTo("/topics/EVERYONE");

        NotificationPartial notificationPartial = new NotificationPartial("Title", "Body");
        notificationPartial.setSound("default");
        fcmNotification.setNotification(notificationPartial);

        fcmNotification.setPriority("high");

        try {
            FCMUtility.sendPushNotification(fcmClient, fcmNotification);
        } catch (FCMPushNotificationException e) {
            e.printStackTrace();
        }
    }

    public static void testInstanceIDInfo() {
        FCMClient fcmClient = initFCMClient();

//        FCMAppInstanceInfo fcmAppInstanceInfo = fcmClient.invokeServiceAppInstanceInfo("cr--ouTuPj8:APA91bHAKFsyRDPmWDHylTT29S7wyRZW7elBNPHiX66Fxtwg4PrE-9xXtHmixPBgHoHtr3A5_1A8xPQnTunEsRafb6rO41LLKVdnKwhxsraajG_pjeVfQ5wr6AroGdCKyW2jN7kqMrvt", true);
        FCMAppInstanceInfo fcmAppInstanceInfo = fcmClient.invokeServiceAppInstanceInfo("c9kSwK58Xjs:APA91bEuR8x-_fxg4Ato1eWCtXzu8tczJtbHoauORGMZzIgXTsraSn4jN3QKXYIxtVNvAhMYTWTbDA65U4998WzbAeQkf60i1tIelUXR3WE_OiefAy1qrCnz6re0K2vij03rIduRtnHi", true);


    }

    public static void testAddFromTopic() {
        FCMClient fcmClient = initFCMClient();

        try {
            FCMUtility.addInstanceIdFromTopic(fcmClient, "c9kSwK58Xjs:APA91bEuR8x-_fxg4Ato1eWCtXzu8tczJtbHoauORGMZzIgXTsraSn4jN3QKXYIxtVNvAhMYTWTbDA65U4998WzbAeQkf60i1tIelUXR3WE_OiefAy1qrCnz6re0K2vij03rIduRtnHi", "EVERYONE");
        } catch (FCMInstanceIdEmptyException e) {
            e.printStackTrace();
        } catch (FCMTopicException e) {
            e.printStackTrace();
        }
    }

    public static void testRemoveFromTopic() {
        FCMClient fcmClient = initFCMClient();

        try {
            FCMUtility.removeInstanceIdFromTopic(fcmClient, "c9kSwK58Xjs:APA91bEuR8x-_fxg4Ato1eWCtXzu8tczJtbHoauORGMZzIgXTsraSn4jN3QKXYIxtVNvAhMYTWTbDA65U4998WzbAeQkf60i1tIelUXR3WE_OiefAy1qrCnz6re0K2vij03rIduRtnHi", "EVERYONE");
        } catch (FCMInstanceIdEmptyException e) {
            e.printStackTrace();
        } catch (FCMTopicException e) {
            e.printStackTrace();
        }
    }

    public static void testCreateGroup() {
        FCMClient fcmClient = initFCMClient();

        String notificationKeyName = "test_au";

        List<String> instanceIds = new ArrayList<>();
        instanceIds.add("d2dIVMWguUA:APA91bFDiC0qeAnsk1m5AdBPoEz6Kjz6X4047yyfLI5SyPmh76woBY-7pSo_8CuMG5Y8VPGCXnKCz6t2NOde-o1cZc-fV7wSHuW2AtXZTas3Rv2OWiXnrCE9jtmQdSPj6SCWIR5jAfBU");
        instanceIds.add("dspjBFKATkE:APA91bETQoBhi9Txgt66oSQA0nQC0dqpLvkgfpqWsNSGBBQxMEVmJv5UntxFQFdHM-MMGK4cWFMKZgwPf9VJ2tTkvxuwqYb7YDoAp_D6g-dMqSWXpA8EthJyNtaf0SpKJw-x6VYQ0_Kt");

//        String notificationKey = "APA91bFlNwKwwuKBih1JPW6GpWofuI6MsStlMc1wnv527TQElNy_KpbN3p8uHXYH2VTym9GpJEAX-NV5RDJ0pDCsO78_3EPRBKQBN8pWrZmPvUwV96WGJSs";

        try {
            String notificationKey = FCMUtility.createDeviceGroup(fcmClient, notificationKeyName, instanceIds);
            System.out.println(notificationKey);
        } catch (FCMGroupException e) {
            e.printStackTrace();
        }
    }

    public static void testAddNotificationKeyToTopic() {
        FCMClient fcmClient = initFCMClient();

        String notificationKey = "APA91bFlNwKwwuKBih1JPW6GpWofuI6MsStlMc1wnv527TQElNy_KpbN3p8uHXYH2VTym9GpJEAX-NV5RDJ0pDCsO78_3EPRBKQBN8pWrZmPvUwV96WGJSs";

        try {
            FCMUtility.addInstanceIdFromTopic(fcmClient, notificationKey, "group-topic");
        } catch (FCMInstanceIdEmptyException e) {
            e.printStackTrace();
        } catch (FCMTopicException e) {
            e.printStackTrace();
        }
    }
}
