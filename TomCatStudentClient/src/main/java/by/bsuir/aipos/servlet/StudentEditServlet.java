package by.bsuir.aipos.servlet;

import by.bsuir.aipos.RestClient;
import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/edit")
public class StudentEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("userId"));
        request.setAttribute("student", new RestClient().getStudent(id));
        List<StudentGroup> groups = new RestClient().getStudentGroups();
        request.setAttribute("groups", groups);
        request.setAttribute("action", "/student/edit");
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StudentUtils utils = new StudentUtils(request);
        Student student = utils.getStudentFromRequest();
        if (utils.validStudent(student)){
            new RestClient().saveStudent(student);
        }
        response.sendRedirect("/students");
    }
}