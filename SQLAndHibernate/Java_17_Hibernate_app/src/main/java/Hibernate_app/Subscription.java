package Hibernate_app;

import javax.persistence.*;


@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;


    public Subscription(SubscriptionKey id, int studentId, int courseId) {

    }
}


