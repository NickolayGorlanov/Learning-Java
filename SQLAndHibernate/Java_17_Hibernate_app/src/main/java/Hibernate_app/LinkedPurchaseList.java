package Hibernate_app;

import javax.persistence.*;


@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    @ManyToOne
    @MapsId("studentId") // Связываем с атрибутом studentId составного ключа
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId") // Связываем с атрибутом courseId составного ключа
    @JoinColumn(name = "course_id")
    private Course course;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public LinkedPurchaseList(LinkedPurchaseListKey id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public LinkedPurchaseList() {
        // Это пустой конструктор, необходимый для Hibernate.
    }




}

