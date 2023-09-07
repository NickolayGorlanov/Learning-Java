package Hibernate_app;

import javax.persistence.*;

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





    public Subscription(SubscriptionKey id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;

    }
}

