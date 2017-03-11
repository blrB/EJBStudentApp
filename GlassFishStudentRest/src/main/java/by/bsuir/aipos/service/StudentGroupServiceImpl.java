package by.bsuir.aipos.service;

import by.bsuir.aipos.StudentEntityManagerFactory;
import by.bsuir.aipos.StudentLogger;
import by.bsuir.aipos.model.StudentGroup;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
@Local(StudentGroupService.class)
public class StudentGroupServiceImpl implements StudentGroupService {

    private EntityManager em = StudentEntityManagerFactory.createEntityManager();

    public StudentGroup save(StudentGroup studentGroup){
        StudentLogger.getLogger().info("Save student group: " + studentGroup.getName());
        em.getTransaction().begin();
        em.merge(studentGroup);
        em.getTransaction().commit();
        return studentGroup;
    }

    public StudentGroup get(String name){
        StudentLogger.getLogger().info("Get student group: " + name);
        TypedQuery<StudentGroup> namedQuery = em.createNamedQuery("StudentGroup.getByName", StudentGroup.class)
                .setParameter("name", name);
        return namedQuery.getResultList().get(0) != null ? namedQuery.getResultList().get(0) : null;
    }

    public StudentGroup get(long id){
        StudentLogger.getLogger().info("Get student group: " + id);
        return em.find(StudentGroup.class, id);
    }

    public void delete(long id){
        StudentLogger.getLogger().info("Delete student group: " + id);
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public List<StudentGroup> getAll(){
        StudentLogger.getLogger().info("Get all student groups");
        TypedQuery<StudentGroup> namedQuery = em.createNamedQuery("StudentGroup.getAll", StudentGroup.class);
        return namedQuery.getResultList();
    }
}
