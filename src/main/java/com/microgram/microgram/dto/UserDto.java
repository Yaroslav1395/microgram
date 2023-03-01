package com.microgram.microgram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private String nickName;
    private String email;
    private String password;
}
