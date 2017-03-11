package by.bsuir.aipos.service;

import by.bsuir.aipos.StudentEntityManagerFactory;
import by.bsuir.aipos.StudentLogger;
import by.bsuir.aipos.model.Student;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
@Local(StudentService.class)
public class StudentServiceImpl implements StudentService{

    private EntityManager em = StudentEntityManagerFactory.createEntityManager();

    public Student save(Student student){
        StudentLogger.getLogger().info("Save student : " + student.getLastName());
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        return student;
    }

    public Student get(long id){
        StudentLogger.getLogger().info("Get student : " + id);
        return em.find(Student.class, id);
    }

    public void delete(long id){
        StudentLogger.getLogger().info("Delete student : " + id);
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<Student> getAll(){
        StudentLogger.getLogger().info("Get all students");
        TypedQuery<Student> namedQuery = em.createNamedQuery("Student.getAll", Student.class);
        return namedQuery.getResultList();
    }
}
