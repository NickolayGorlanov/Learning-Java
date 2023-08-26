package Hibernate_app;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int duration;

    @OneToMany(mappedBy = "course")
    private List<Subscription> subscriptions;


}
