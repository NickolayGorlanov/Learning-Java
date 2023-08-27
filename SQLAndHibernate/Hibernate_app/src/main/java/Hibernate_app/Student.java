package Hibernate_app;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "student")
    private List<Subscription> subscriptions;


    public Student(Long id, String name, int age, List<Subscription> subscriptions) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subscriptions = subscriptions;
    }
}