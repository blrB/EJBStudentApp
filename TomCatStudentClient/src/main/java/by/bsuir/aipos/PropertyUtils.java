package by.bsuir.aipos;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PropertyUtils implements ServletContextListener {

    private static String urlStudentSave;
    private static String urlStudentGet;
    private static String urlStudentDelete;
    private static String urlStudentsAll;
    private static String urlStudentGroupName;
    private static String urlStudentGroupsAll;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String serverURL = servletContextEvent
                .getServletContext()
                .getInitParameter("server-url");
        StudentLogger.getLogger().info("Server URL : " + serverURL);
        urlStudentSave = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-student-save");
        StudentLogger.getLogger().info("urlStudentSave : " + urlStudentSave);
        urlStudentGet = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-student-get");
        StudentLogger.getLogger().info("urlStudentGet : " + urlStudentGet);
        urlStudentDelete = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-student-delete");
        StudentLogger.getLogger().info("urlStudentDelete : " + urlStudentDelete);
        urlStudentsAll = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-students-all");
        StudentLogger.getLogger().info("urlStudentsAll : " + urlStudentsAll);
        urlStudentGroupName = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-student-group-name");
        StudentLogger.getLogger().info("urlStudentGroupName : " + urlStudentGroupName);
        urlStudentGroupsAll = serverURL + servletContextEvent
                .getServletContext()
                .getInitParameter("rest-students-group-all");
        StudentLogger.getLogger().info("urlStudentGroupsAll : " + urlStudentGroupsAll);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

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
