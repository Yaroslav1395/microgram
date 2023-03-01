package com.microgram.microgram.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
public class Comment {
    private String comment;
    private int userId;
    private int publicationId;

    public Comment() {
    }

    public Comment(String comment, int userId, int publicationId) {
        this.comment = comment;
        this.userId = userId;
        this.publicationId = publicationId;
    }
}
