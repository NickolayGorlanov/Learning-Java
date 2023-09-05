package Hibernate_app;

import javax.persistence.*;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public LinkedPurchaseList(Student student_name, Course course_name) {
        this.student = student_name;
        this.course = course_name;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student_name) {
        this.student = student_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course_name) {
        this.course = course_name;
    }
}
