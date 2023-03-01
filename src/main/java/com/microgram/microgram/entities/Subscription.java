package com.microgram.microgram.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
public class Subscription {
    private int subscribeUserId;
    private int subscribedUserId;
    private LocalDateTime dateTime;


}

