package com.microgram.microgram.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
public class Like {
    private int LikedUserId;
    private int LikedPublicationId;
    private LocalDateTime Datetime;

    public Like() {
    }

    public Like(int likedUserId, int likedPublicationId, LocalDateTime dateTime) {
        this.LikedUserId = likedUserId;
        this.LikedPublicationId = likedPublicationId;
        this.Datetime = dateTime;
    }
    public Like(int likedUserId, int likedPublicationId, Timestamp dateTime){
        this.LikedUserId = likedUserId;
        this.LikedPublicationId = likedPublicationId;
        this.Datetime = dateTime.toLocalDateTime();
    }
}
