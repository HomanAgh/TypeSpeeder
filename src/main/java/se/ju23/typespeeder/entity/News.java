package se.ju23.typespeeder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "publish_date")
    public LocalDateTime publishDate;

    public News() {
        this.content = "Today marks the launch of Streamline, an innovative app designed to enhance productivity for remote teams. Developed by TechForward";
        this.publishDate = LocalDateTime.parse("2024-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
