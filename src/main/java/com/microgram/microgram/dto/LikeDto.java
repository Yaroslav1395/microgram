package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
public class LikeDto {
    private int LikedUserId;
    private int likedPublicationId;
    private LocalDateTime dateTime;

}
