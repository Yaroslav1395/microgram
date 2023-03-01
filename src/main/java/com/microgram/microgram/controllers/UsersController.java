package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.UserDto;
import com.microgram.microgram.dto.UserWithoutPasswordDto;
import com.microgram.microgram.entities.User;
import com.microgram.microgram.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/createUsersTable")
    public void createUsersTable(){
        usersService.createUsersTable();
    }
    @GetMapping("/dropUsersTable")
    public void dropUsersTable(){
        usersService.dropUsersTable();
    }
    @PostMapping("/newUser")
    public ResponseEntity<Long> createNewUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(usersService.createNewUser(userDto), HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserWithoutPasswordDto>> getAllUsers(){
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserWithoutPasswordDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(usersService.getUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<List<UserWithoutPasswordDto>> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(usersService.getUserByName(name), HttpStatus.OK);
    }
    @GetMapping("/getUserByNickName/{nickName}")
    public ResponseEntity<UserWithoutPasswordDto> getUserByNickName(@PathVariable String nickName){
        return new ResponseEntity<>(usersService.getUserByNickName(nickName), HttpStatus.OK);
    }
    @GetMapping("/registrationCheck/{email}")
    public ResponseEntity<String> isRegisteredEmail(@PathVariable String email){
        if(!usersService.isRegisteredEmail(email)){
            return new ResponseEntity<>("False", HttpStatus.OK);
        }
        return new ResponseEntity<>("True", HttpStatus.OK);
    }

}
