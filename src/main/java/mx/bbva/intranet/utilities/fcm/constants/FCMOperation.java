package mx.bbva.intranet.utilities.fcm.constants;

/**
 * Created by BBVA on 13/06/2016.
 */
public abstract class FCMOperation {
    // Group GCM operations
    public static final String CREATE_GROUP = "create";
    public static final String ADD_TO_GROUP = "add";
    public static final String REMOVE_OF_GROUP = "remove";

    // Topic GCM operations
    public static final String ADD_BATCH_TO_TOPIC = "batchAdd";
    public static final String REMOVE_BATCH_OF_TOPIC = "batchRemove";
    public static final String IMPORT_BATCH_OF_TOPIC = "batchImport";

}
