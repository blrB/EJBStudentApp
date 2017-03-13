package by.bsuir.aipos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.bsuir.aipos.client.MainWindow.logger;

public class PropertyUtils {

    private static String urlStudentSave;
    private static String urlStudentGet;
    private static String urlStudentDelete;
    private static String urlStudentsAll;
    private static String urlStudentGroupName;
    private static String urlStudentGroupsAll;

    public PropertyUtils() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            String serverURL = property.getProperty("server-url");
            StudentLogger.getLogger().info("Server URL : " + serverURL);
            urlStudentSave = serverURL + property.getProperty("rest-student-save");
            StudentLogger.getLogger().info("urlStudentSave : " + urlStudentSave);
            urlStudentGet = serverURL + property.getProperty("rest-student-get");
            StudentLogger.getLogger().info("urlStudentGet : " + urlStudentGet);
            urlStudentDelete = serverURL + property.getProperty("rest-student-delete");
            StudentLogger.getLogger().info("urlStudentDelete : " + urlStudentDelete);
            urlStudentsAll = serverURL + property.getProperty("rest-students-all");
            StudentLogger.getLogger().info("urlStudentsAll : " + urlStudentsAll);
            urlStudentGroupName = serverURL + property.getProperty("rest-student-group-name");
            StudentLogger.getLogger().info("urlStudentGroupName : " + urlStudentGroupName);
            urlStudentGroupsAll = serverURL + property.getProperty("rest-students-group-all");
            StudentLogger.getLogger().info("urlStudentGroupsAll : " + urlStudentGroupsAll);
        } catch (IOException e) {
            logger.error("Error, can not find file - config.properties");
            logger.trace(e);
        }
    }

    public static String getUrlStudentSave() {
        return urlStudentSave;
    }

    public static String getUrlStudentGet() {
        return urlStudentGet;
    }

    public static String getUrlStudentDelete() {
        return urlStudentDelete;
    }

    public static String getUrlStudentsAll() {
        return urlStudentsAll;
    }

    public static String getUrlStudentGroupName() {
        return urlStudentGroupName;
    }

    public static String getUrlStudentGroupsAll() {
        return urlStudentGroupsAll;
    }
}
