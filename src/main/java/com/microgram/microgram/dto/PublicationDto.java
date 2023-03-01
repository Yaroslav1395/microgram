package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class PublicationDto {
    private String image;
    private String description;
    private LocalDateTime dataTime;
    private int userId;
}
