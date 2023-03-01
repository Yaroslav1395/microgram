package com.microgram.microgram.dao;

import com.microgram.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class UsersDao {
    private final JdbcTemplate jdbcTemplate;

    public void createUsersTable(){
        String query = "CREATE TABLE users(" +
                " Id SERIAL PRIMARY KEY," +
                " Name CHARACTER VARYING(30)," +
                " NickName CHARACTER VARYING(30)," +
                " Email CHARACTER VARYING(30)," +
                " Password CHARACTER VARYING(30)" +
                ");";
        jdbcTemplate.execute(query);
    }

    public void dropUsersTable(){
        String query = "DROP TABLE users;";
        jdbcTemplate.execute(query);
    }

    public Long createNewUser(User user){
        String query = "INSERT INTO users (Name, NickName, Email, Password) " +
                "VALUES(?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getNickName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<User> getAllUsers(){
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersByName(String name){
        String query = "select * from users " +
                "where name = ?;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), name);
    }

    public User getUserByNickName(String nickName){
        String query = "select * from users " +
                "where nickName = ?;";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), nickName);
    }

    public User getUserByEmail(String email){
        String query = "select * from users " +
                "where email = ?;";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), email);
    }

    public Boolean isRegisteredEmail(String email){
        String query = "select * from users " +
                "where email = ?;";
        try {
            jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), email);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
