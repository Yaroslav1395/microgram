package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.CommentDto;
import com.microgram.microgram.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentsController {
    private final CommentService commentService;
    @GetMapping("/createCommentTable")
    public void createCommentTable(){
        commentService.createCommentTable();
    }
    @GetMapping("/dropCommentTable")
    public void dropCommentTable(){
        System.out.println("123");
        commentService.dropCommentTable();
    }
    @PostMapping("/createNewComment")
    public Long createNewComment(@RequestBody CommentDto commentDto){
        return commentService.createNewComment(commentDto);
    }
    @GetMapping("/getCommentsForPublication/{publicationId}")
    public ResponseEntity<List<CommentDto>> getCommentForPublication(@PathVariable String publicationId){
        try {
            return new ResponseEntity<>(commentService.getCommentsForPublication(Integer.parseInt(publicationId)), HttpStatus.OK);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
