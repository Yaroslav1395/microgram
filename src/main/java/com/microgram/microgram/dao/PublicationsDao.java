package com.microgram.microgram.dao;

import com.microgram.microgram.entities.Publication;
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
public class PublicationsDao {
    private final JdbcTemplate jdbcTemplate;

    public void createPublicationsTable(){
        String query = "CREATE TABLE Publications( " +
                "Id SERIAL PRIMARY KEY, " +
                "Image CHARACTER VARYING(30), " +
                "Description TEXT, " +
                "Datetime TIMESTAMP, " +
                "UserId INTEGER, " +
                "FOREIGN KEY (UserId) REFERENCES Users (Id) " +
                "ON UPDATE CASCADE " +
                "ON DELETE RESTRICT);";
        jdbcTemplate.update(query);
    }
    public void dropPublicationsTable(){
        String query = "DROP TABLE Publications;";
        jdbcTemplate.update(query);
    }
    public Long createNewPublication(Publication publication){
        String query = "INSERT INTO Publications (Image, Description, Datetime, UserId) " +
                "VALUES(?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, publication.getImage());
            ps.setString(2, publication.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(publication.getDateTime()));
            ps.setInt(4, publication.getUserId());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<Publication> getPublicationsForUser(int userId){
        String query = "select * from publications " +
                "where userId != ?;";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Publication.class), userId);
    }

    public List<Publication> getPublicationsForUserBySubscriptions(int userId){
        String query = "select " +
                "p.image, " +
                "p.description, " +
                "p.datetime, " +
                "s.subscribeuserid" +
                "from publications as p " +
                "inner join users as u " +
                "on u.id = p.userid " +
                "inner join subscriptions as s " +
                "on s.subscribeduserid = u.id " +
                "where u.id = ?;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Publication.class), userId);
    }


}
