package com.microgram.microgram.dao;

import com.microgram.microgram.dto.LikeDto;
import com.microgram.microgram.entities.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LikesDao {
    private final JdbcTemplate jdbcTemplate;

    public void createLikesTable(){
        String query = "CREATE TABLE likes(" +
                "Id SERIAL PRIMARY KEY, " +
                "LikedUserId INTEGER, " +
                "LikedPublicationId INTEGER, " +
                "Datetime TIMESTAMP, " +
                "FOREIGN KEY (LikedUserId) REFERENCES Users (Id) " +
                "ON UPDATE CASCADE " +
                "ON DELETE RESTRICT, " +
                "FOREIGN KEY (LikedPublicationId) REFERENCES Publications (Id) " +
                "ON UPDATE CASCADE " +
                "ON DELETE RESTRICT);";
        jdbcTemplate.update(query);
    }
    public void dropLikesTable(){
        String query = "DROP TABLE likes;";
        jdbcTemplate.update(query);
    }
    public long createNewLike(Like like){
        String query = "INSERT INTO likes (LikedUserId, LikedPublicationId, Datetime) " +
                "VALUES(?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setInt(1, like.getLikedUserId());
            ps.setInt(2, like.getLikedPublicationId());
            ps.setTimestamp(3, Timestamp.valueOf(like.getDatetime()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    public boolean userLikedPublication(int userId, int publicationId){
        String query = "SELECT likedUserId, likedPublicationId, dateTime" +
                " FROM Likes where LikedUserId = ? and LikedPublicationId = ?;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Like.class), userId, publicationId).stream()
                .findFirst()
                .isPresent();
    }
    public List<Like> getPublicationsLikes(int publicationId){
        String query = "SELECT LikedUserId, LikedPublicationId, Datetime FROM likes WHERE likedPublicationId = ?;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Like.class), publicationId);
    }
}
