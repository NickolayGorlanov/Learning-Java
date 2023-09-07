package Hibernate_app;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;



    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public Subscription(SubscriptionKey id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;

    }
}

