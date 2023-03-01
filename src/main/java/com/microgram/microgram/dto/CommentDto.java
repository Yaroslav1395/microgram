package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
public class CommentDto {
    private String comment;
    private int userId;
    private int publicationId;
}
