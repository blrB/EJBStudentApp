package by.bsuir.aipos.model;

public class StudentGroup {

    private long id;

    private String name;
    
    public StudentGroup() {
    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
