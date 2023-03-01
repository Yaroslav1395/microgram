package com.microgram.microgram.services;

import com.microgram.microgram.dao.UsersDao;
import com.microgram.microgram.dto.UserDto;
import com.microgram.microgram.dto.UserWithoutPasswordDto;
import com.microgram.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersDao usersDao;


    public void createUsersTable(){
        usersDao.createUsersTable();
    }
    public void dropUsersTable(){
        usersDao.dropUsersTable();
    }
    public Long createNewUser(UserDto userDto){
        return usersDao.createNewUser(User.builder()
                .name(userDto.getName())
                .nickName(userDto.getNickName())
                .email(userDto.getEmail())
                .password(userDto.getPassword()).build());
    }
    public List<UserWithoutPasswordDto> getAllUsers(){
        return usersDao.getAllUsers().stream().map(user -> UserWithoutPasswordDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .nickName(user.getNickName())
                        .email(user.getEmail()).build())
                        .collect(Collectors.toList());
    }

    public UserWithoutPasswordDto getUserByEmail(String email){
        User user = usersDao.getUserByEmail(email);
        return UserWithoutPasswordDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
    }

    public List<UserWithoutPasswordDto> getUserByName(String name){
        return usersDao.getUsersByName(name).stream().map(user -> UserWithoutPasswordDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build()).collect(Collectors.toList());
    }

    public UserWithoutPasswordDto getUserByNickName(String nickName){
        User user = usersDao.getUserByNickName(nickName);
        return UserWithoutPasswordDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
    }

    public boolean isRegisteredEmail(String email){
        return usersDao.isRegisteredEmail(email);
    }
}
