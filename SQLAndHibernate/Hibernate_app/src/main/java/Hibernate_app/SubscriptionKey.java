package Hibernate_app;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SubscriptionKey implements Serializable {
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    // Геттеры и сеттеры

    // Пример реализации equals и hashCode для составного ключа
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionKey that = (SubscriptionKey) o;

        if (!studentId.equals(that.studentId)) return false;
        return courseId.equals(that.courseId);
    }


}
