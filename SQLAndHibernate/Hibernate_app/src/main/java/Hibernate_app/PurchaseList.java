package Hibernate_app;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;


}
