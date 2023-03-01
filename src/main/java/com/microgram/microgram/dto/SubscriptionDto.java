package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class SubscriptionDto {
    private int subscribeUserId;
    private int subscribedUserId;
    private LocalDateTime dateTime;
}
