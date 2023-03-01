package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class CustomerDto {
    private Long id;
    private String name;
    private Integer age;
}
