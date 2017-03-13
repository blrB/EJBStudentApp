package by.bsuir.aipos;

import by.bsuir.aipos.model.Student;
import by.bsuir.aipos.model.StudentGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestClient {

    private Client client;

    public RestClient(){
        client = ClientBuilder.newClient();
    }

    /**
     * Save or update student
     *
     * @return saved student
     */
    public void saveStudent(Student student){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(student);
            client.target(PropertyUtils.getUrlStudentSave())
                    .request()
                    .post(Entity.json(jsonInString));
        } catch (JsonProcessingException e) {
            StudentLogger.getLogger().trace(e);
        }
    }


    /**
     * Get student
     *
     * @return student
     */
    public Student getStudent(long id) {
        Student student = new Student();
        try {
            String jsonStudent = client.target(PropertyUtils.getUrlStudentGet() + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            StudentLogger.getLogger().info("Get json(Student " + id + ") from server : " + jsonStudent);
            ObjectMapper mapper = new ObjectMapper();
            student = mapper.readValue(jsonStudent, Student.class);
        } catch (IOException e) {
            StudentLogger.getLogger().trace(e);
        }
        return student;
    }

    /**
     * Delete student
     */
    public void deleteStudent(long id){
        client.target(PropertyUtils.getUrlStudentDelete() + id)
                .request().accept(MediaType.TEXT_PLAIN).method("DELETE");
        StudentLogger.getLogger().info("Delete student id : " + id);
    }

    /**
     * Get all student
     *
     * @return list of student
     */
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        try {
            String jsonStudents = client.target(PropertyUtils.getUrlStudentsAll())
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            StudentLogger.getLogger().info("Get json(All Student) from server : " + jsonStudents);
            ObjectMapper mapper = new ObjectMapper();
            students = mapper.readValue(jsonStudents, new TypeReference<List<Student>>(){});
        } catch (IOException e) {
            StudentLogger.getLogger().trace(e);
        }
        return students;
    }

    /**
     * Get student group by name
     *
     * @return student
     */
    public StudentGroup getStudentGroup(String name) {
        StudentGroup studentGroup = new StudentGroup();
        try {
            String jsonStudent = client.target(PropertyUtils.getUrlStudentGroupName() + name)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            StudentLogger.getLogger().info("Get json(Student group " + name + " from server : " + jsonStudent);
            ObjectMapper mapper = new ObjectMapper();
            studentGroup = mapper.readValue(jsonStudent, StudentGroup.class);
        } catch (IOException e) {
            StudentLogger.getLogger().trace(e);
        }
        return studentGroup;
    }


    /**
     * Get all student groups
     *
     * @return list of student groups
     */
    public List<StudentGroup> getStudentGroups(){
        List<StudentGroup> studentGroups = new ArrayList<>();
        try {
            String jsonStudents = client.target(PropertyUtils.getUrlStudentGroupsAll())
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            StudentLogger.getLogger().info("Get json(All Student Group) from server : " + jsonStudents);
            ObjectMapper mapper = new ObjectMapper();
            studentGroups = mapper.readValue(jsonStudents, new TypeReference<List<StudentGroup>>(){});
        } catch (IOException e) {
            StudentLogger.getLogger().trace(e);
        }
        return studentGroups;
    }

}
