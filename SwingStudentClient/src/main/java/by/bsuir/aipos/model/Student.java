package by.bsuir.aipos.model;

import java.util.Date;

public class Student {

    private long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date dateOfBirth;

    private String homeAddress;

    private StudentGroup studentGroup;

    public Student (){
    }

    public Student(String firstName, String lastName, Date dateOfBirth, String homeAddress, StudentGroup studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public Student(String firstName, String lastName, String middleName, Date dateOfBirth, String homeAddress, StudentGroup studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", homeAddress='" + homeAddress + '\'' +
                ", studentGroup=" + studentGroup +
                '}';
    }
}
