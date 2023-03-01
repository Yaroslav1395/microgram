package com.microgram.microgram.dao;

import com.microgram.microgram.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentsDao {
    private final JdbcTemplate jdbcTemplate;
    public void createCommentsTable(){
        String query = "CREATE TABLE comments(\n" +
                "\tid SERIAL PRIMARY KEY,\n" +
                "    comment TEXT,\n" +
                "    userId INTEGER,\n" +
                "    publicationId INTEGER, \n" +
                "FOREIGN KEY (userId) REFERENCES users(id)\n" +
                "\tON DELETE CASCADE\n" +
                "\tON UPDATE CASCADE,\n" +
                "FOREIGN KEY (userId) REFERENCES users(id)\n" +
                "\tON DELETE CASCADE\n" +
                "\tON UPDATE CASCADE);";
        jdbcTemplate.update(query);
    }
    public void dropCommentTable(){
        String query = "DROP TABLE comments;\t";
        jdbcTemplate.update(query);
    }
    public Long createNewComment(Comment comment){
        String query = "INSERT INTO comments(comment, userId, publicationId)\n" +
                "VALUES(?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getPublicationId());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    public List<Comment> getCommentForPublication(int publicationId){
        String query = "SELECT * FROM comments where publicationId = ?;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Comment.class), publicationId);
    }
}
