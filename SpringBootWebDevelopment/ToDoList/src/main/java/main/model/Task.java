package main.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Объявляем класс как сущность
public class Task {
    @Id // Указываем, что это поле будет использоваться как первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Указываем стратегию генерации ключа
    private Long id;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private boolean isDone;

    @Column(nullable = false)
    private String title;

    private String description;

    public Task() {
        this.creationTime = LocalDateTime.now();
        this.isDone = false;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
