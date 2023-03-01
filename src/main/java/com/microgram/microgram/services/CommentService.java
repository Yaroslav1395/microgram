package com.microgram.microgram.services;

import com.microgram.microgram.dao.CommentsDao;
import com.microgram.microgram.dto.CommentDto;
import com.microgram.microgram.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentsDao commentsDao;

    public void createCommentTable(){
        commentsDao.createCommentsTable();
    }
    public void dropCommentTable(){
        System.out.println("123");
        commentsDao.dropCommentTable();
    }

    public Long createNewComment(CommentDto commentDto){
        return  commentsDao.createNewComment(Comment.builder()
                .comment(commentDto.getComment())
                .userId(commentDto.getUserId())
                .publicationId(commentDto.getPublicationId())
                .build());
    }
    public List<CommentDto> getCommentsForPublication(int publicationId){
        return commentsDao.getCommentForPublication(publicationId).stream().map(comment -> CommentDto.builder()
                .comment(comment.getComment())
                .userId(comment.getUserId())
                .publicationId(comment.getPublicationId())
                .build()).collect(Collectors.toList());
    }
}
