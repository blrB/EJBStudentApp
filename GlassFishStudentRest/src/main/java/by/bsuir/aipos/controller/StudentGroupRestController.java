package by.bsuir.aipos.controller;

import by.bsuir.aipos.model.StudentGroup;
import by.bsuir.aipos.service.StudentGroupService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student-group")
public class StudentGroupRestController {

    @EJB
    private StudentGroupService studentGroupService;

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void doSave(StudentGroup studentGroup) {

        studentGroupService.save(studentGroup);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentGroup doGetByID(@PathParam("id") Long id) {

        return studentGroupService.get(id);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentGroup doGetByName(@PathParam("name") String name) {

        return studentGroupService.get(name);
    }

    @DELETE
    @Path("{id}")
    public void doDeleteByID(@PathParam("id") Long id){

        studentGroupService.delete(id);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGroup> doGetAll() {

        return studentGroupService.getAll();
    }

}

