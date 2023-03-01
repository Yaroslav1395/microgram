package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.LikeDto;
import com.microgram.microgram.services.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;
    @RequestMapping("/createLikesTable")
    public void createLikesTable(){
        likesService.createLikesTable();
    }
    @RequestMapping("/dropLikesTable")
    public void dropLikesTable(){
        likesService.dropLikesTable();
    }
    @PostMapping("/createNewLike")
    public ResponseEntity<Long> createNewLike(@RequestBody LikeDto likeDto){
        return new ResponseEntity<>(likesService.createNewLike(likeDto), HttpStatus.OK);
    }
    @GetMapping("/userLikedPublications/{userId}/{publicationId}")
    public ResponseEntity<Boolean> userLikedPublications(@PathVariable String userId, @PathVariable String publicationId){
        try {
            return new ResponseEntity<>(likesService.isUserLikedPublications(Integer.parseInt(userId), Integer.parseInt(publicationId)), HttpStatus.OK);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
    @GetMapping("/getPublicationLikes/{publicationId}")
    public ResponseEntity<List<LikeDto>> getPublicationLikes(@PathVariable String publicationId){
        try {
            return new ResponseEntity<>(likesService.getPublicationLikes(Integer.parseInt(publicationId)), HttpStatus.OK);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

}
