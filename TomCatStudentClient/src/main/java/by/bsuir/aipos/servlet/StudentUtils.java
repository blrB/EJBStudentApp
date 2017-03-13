package by.bsuir.aipos.servlet;

import by.bsuir.aipos.RestClient;
import by.bsuir.aipos.StudentLogger;
import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentUtils {

    private HttpServletRequest request;

    public StudentUtils(HttpServletRequest request){
        this.request = request;
    }

    /**
     * Save or update student
     *
     * @return saved student
     */
    public Student getStudentFromRequest(){
        final String ISO = "iso-8859-1";
        final String UTF8 = "UTF-8";
        Student student = new Student();
        if (!request.getParameter("id").isEmpty()) {
            student.setId(Integer.parseInt(request.getParameter("id")));
        }
        try {
            student.setFirstName
                    (new String(request.getParameter("firstName").getBytes(ISO), UTF8));
            student.setLastName
                    (new String(request.getParameter("lastName").getBytes(ISO), UTF8));
            student.setMiddleName
                    (new String(request.getParameter("middleName").getBytes(ISO), UTF8));
            student.setHomeAddress
                    (new String(request.getParameter("homeAddress").getBytes(ISO), UTF8));
        } catch (UnsupportedEncodingException e) {
            StudentLogger.getLogger().trace(e);
            StudentLogger.getLogger().error("UnsupportedEncodingException in getStudentFromRequest()");
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = format.parse(request.getParameter("dateOfBirth"));
            student.setDateOfBirth(birthDate);
        } catch (ParseException e) {
            StudentLogger.getLogger().trace(e);
            StudentLogger.getLogger().error("ParseException dateOfBirth in getStudentFromRequest()");
        }
        StudentGroup studentGroup = new RestClient().getStudentGroup(request.getParameter("group"));
        student.setStudentGroup(studentGroup);
        return student;
    }

    /**
     * Check property of student
     *
     * @param student
     * @return true if student have all needed property
     */
    public boolean validStudent(Student student) {
        return !(
                student.getFirstName().isEmpty() ||
                student.getLastName().isEmpty() ||
                student.getHomeAddress().isEmpty() ||
                student.getDateOfBirth() == null ||
                student.getStudentGroup() == null
        );
    }

}
