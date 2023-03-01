package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserWithoutPasswordDto {
    private int id;
    private String name;
    private String nickName;
    private String email;
}
