package com.microgram.microgram.services;

import com.microgram.microgram.dao.LikesDao;
import com.microgram.microgram.dto.LikeDto;
import com.microgram.microgram.dto.PublicationDto;
import com.microgram.microgram.entities.Like;
import com.microgram.microgram.entities.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesDao likesDao;

    public void createLikesTable(){
        likesDao.createLikesTable();
    }
    public void dropLikesTable(){
        likesDao.dropLikesTable();
    }
    public Long createNewLike(LikeDto likeDto){
        return likesDao.createNewLike(Like.builder()
                .LikedUserId(likeDto.getLikedUserId())
                .LikedPublicationId(likeDto.getLikedPublicationId())
                .Datetime(LocalDateTime.now())
                .build());
    }
    public boolean isUserLikedPublications(int userId, int likedPublicationId){
        return likesDao.userLikedPublication(userId, likedPublicationId);
    }

    public List<LikeDto> getPublicationLikes(int publicationId){
        return likesDao.getPublicationsLikes(publicationId).stream()
                .map(like -> LikeDto.builder()
                        .LikedUserId(like.getLikedUserId())
                        .likedPublicationId(like.getLikedPublicationId())
                        .dateTime(like.getDatetime())
                        .build())
                .collect(Collectors.toList());
    }
}
