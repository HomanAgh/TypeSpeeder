package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Patch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String version;
    @Column(name = "release_date")
    public LocalDateTime releaseDate;

    public Patch() {
        this.releaseDate = LocalDateTime.parse("2024-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
