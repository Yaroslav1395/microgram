package com.microgram.microgram.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Data
@Builder
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
public class Publication{
    private String image;
    private String description;
    private LocalDateTime dateTime;
    private int userId;

    public Publication() {
    }

    public Publication(String image, String description, LocalDateTime dateTime, int publishingUser) {
        this.image = image;
        this.description = description;
        this.dateTime = dateTime;
        this.userId = publishingUser;
    }
    public Publication(String image, String description, Timestamp dateTime, int publishingUser) {
        this.image = image;
        this.description = description;
        this.dateTime = dateTime.toLocalDateTime();
        this.userId = publishingUser;
    }
}
