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


}