package Hibernate_app;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
    @Table(name = "Subscriptions")
    public class Subscription {
        @EmbeddedId
        private SubscriptionKey id;

        @ManyToOne
        @JoinColumn(name = "student_id", insertable = false, updatable = false)
        private Student student;

        @ManyToOne
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course course;

        private LocalDateTime subscriptionDate;


    }


