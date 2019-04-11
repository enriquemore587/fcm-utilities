package mx.bbva.intranet.utilities.fcm.tools;

/**
 * Created by XMZ0860 on 21/06/2016.
 */
public abstract class FCMTopicTool {


    public static String buildTopicName(String bcpName, String groupName) {
        String topicName = "";

        groupName = groupName.replace(' ', '-');
        if (bcpName != null && !bcpName.isEmpty()) {
            bcpName = bcpName.replace(' ', '-');
            topicName = String.format("%s-%s", bcpName, groupName);
        } else {
            topicName = String.format("%s", groupName);
        }

        return topicName;
    }

    public static String buildTopicName(String groupName) {
        String topicName = "";
        groupName = groupName.replace(' ', '-');
        groupName = groupName.replaceAll("ñ", "n");

        groupName = groupName.replaceAll("á", "a");
        groupName = groupName.replaceAll("à", "a");
        groupName = groupName.replaceAll("ä", "a");
        groupName = groupName.replaceAll("â", "a");
        groupName = groupName.replaceAll("Á", "A");
        groupName = groupName.replaceAll("À", "A");
        groupName = groupName.replaceAll("Ä", "A");
        groupName = groupName.replaceAll("Â", "A");

        groupName = groupName.replaceAll("é", "e");
        groupName = groupName.replaceAll("è", "e");
        groupName = groupName.replaceAll("ë", "e");
        groupName = groupName.replaceAll("ê", "e");
        groupName = groupName.replaceAll("É", "E");
        groupName = groupName.replaceAll("È", "E");
        groupName = groupName.replaceAll("Ë", "E");
        groupName = groupName.replaceAll("Ê", "E");

        groupName = groupName.replaceAll("í", "i");
        groupName = groupName.replaceAll("ì", "i");
        groupName = groupName.replaceAll("ï", "i");
        groupName = groupName.replaceAll("î", "i");
        groupName = groupName.replaceAll("Í", "I");
        groupName = groupName.replaceAll("Ì", "I");
        groupName = groupName.replaceAll("Ï", "I");
        groupName = groupName.replaceAll("Î", "I");

        groupName = groupName.replaceAll("ó", "o");
        groupName = groupName.replaceAll("ò", "o");
        groupName = groupName.replaceAll("ö", "o");
        groupName = groupName.replaceAll("ô", "o");
        groupName = groupName.replaceAll("Ó", "O");
        groupName = groupName.replaceAll("Ò", "O");
        groupName = groupName.replaceAll("Ö", "O");
        groupName = groupName.replaceAll("Ô", "O");

        groupName = groupName.replaceAll("ú", "u");
        groupName = groupName.replaceAll("ù", "u");
        groupName = groupName.replaceAll("ü", "u");
        groupName = groupName.replaceAll("û", "u");
        groupName = groupName.replaceAll("Ú", "U");
        groupName = groupName.replaceAll("Ù", "U");
        groupName = groupName.replaceAll("Ü", "U");
        groupName = groupName.replaceAll("Û", "U");

//        groupName = groupName.replaceAll("\\[", "");
//        groupName = groupName.replaceAll("\\]", "");
        groupName = groupName.replaceAll("(?!-)(\\W)", "");
        topicName = String.format("%s", groupName);

        return topicName;
    }
}
