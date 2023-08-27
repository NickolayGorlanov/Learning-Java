package Hibernate_app;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int salary;
    private int age;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;


    public Teacher(Long id, String name, int salary, int age, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.courses = courses;
    }
}
