package by.bsuir.aipos.servlet;

import by.bsuir.aipos.RestClient;
import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.anyString;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({StudentUtils.class})
public class StudentUtilsTest {

    private HttpServletRequest request;
    private StudentUtils studentUtils;
    private Student student;
    private StudentGroup studentGroup;
    private Date date;

    @Before
    public void init() throws Exception {
        String dateString = "1996-12-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(dateString);
        studentGroup = new StudentGroup("421702");
        studentGroup.setId(1);
        student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                date,
                "Bobrujsk",
                studentGroup);
        student.setId(1);
        RestClient client = PowerMockito.mock(RestClient.class);
        PowerMockito.whenNew(RestClient.class).withNoArguments()
                .thenReturn(client);
        when(client.getStudentGroup(anyString())).thenReturn(studentGroup);
        request = PowerMockito.mock(HttpServletRequest.class);
        when(request.getParameter("firstName")).thenReturn("Andrey");
        when(request.getParameter("lastName")).thenReturn("Bobkov");
        when(request.getParameter("middleName")).thenReturn("Valerievich");
        when(request.getParameter("homeAddress")).thenReturn("Bobrujsk");
        when(request.getParameter("dateOfBirth")).thenReturn("1996-12-05");
        when(request.getParameter("group")).thenReturn("421702");
        studentUtils = new StudentUtils(request);
    }

    @Test
    public void saveStudent() throws Exception {
        when(request.getParameter("id")).thenReturn("");
        Student student = studentUtils.getStudentFromRequest();
        String dateString = "1996-12-05";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);
        assert (student.getFirstName().equals("Andrey") &&
                student.getLastName().equals("Bobkov") &&
                student.getMiddleName().equals("Valerievich") &&
                student.getDateOfBirth().equals(date) &&
                student.getHomeAddress().equals("Bobrujsk") &&
                student.getStudentGroup().getName().equals("421702")
        );
    }

    @Test
    public void validStudent() {
        assert (studentUtils.validStudent(student));
    }

    @Test
    public void notValidStudentData() {
        Student student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                null,
                "Bobrujsk",
                studentGroup);
        assert (!studentUtils.validStudent(student));
    }

    @Test
    public void notValidStudentFirstName() {
        Student student = new Student(
                "",
                "Bobkov",
                "Valerievich",
                date,
                "Bobrujsk",
                studentGroup);
        assert (!studentUtils.validStudent(student));
    }

    @Test
    public void notValidStudentLastName() {
        Student student = new Student(
                "Andrey",
                "",
                "Valerievich",
                date,
                "Bobrujsk",
                studentGroup);
        assert (!studentUtils.validStudent(student));
    }

    @Test
    public void notValidStudentHomeAddress() {
        Student student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                date,
                "",
                studentGroup);
        assert (!studentUtils.validStudent(student));
    }

    @Test
    public void notValidStudentGroup() {
        Student student = new Student(
                "Andrey",
                "Bobkov",
                "Valerievich",
                date,
                "Bobrujsk",
                null);
        assert (!studentUtils.validStudent(student));
    }

}