package Hibernate_app;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListKey id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;


}
