package by.bsuir.aipos.controller;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.service.StudentService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student")
public class StudentRestController {

    @EJB
    private StudentService studentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void doSave(Student student) {

        studentService.save(student);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student doGetByID(@PathParam("id") Long id) {

        return studentService.get(id);
    }

    @DELETE
    @Path("{id}")
    public void doDeleteByID(@PathParam("id") Long id){

        if (studentService.get(id) != null) {
            studentService.delete(id);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> doGetAll() {

        return studentService.getAll();
    }

}
