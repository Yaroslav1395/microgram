package com.microgram.microgram.dao;

import com.microgram.microgram.entities.Subscription;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SubscriptionsDao {
    private final JdbcTemplate jdbcTemplate;
    public void createSubscriptionsTable(){
        String query ="CREATE TABLE subscriptions( " +
                "Id SERIAL PRIMARY KEY, " +
                "dateTime TIMESTAMP, " +
                "subscribeUserId INTEGER, " +
                "subscribedUserId INTEGER, " +
                "FOREIGN KEY (subscribeUserId) REFERENCES Users (Id) " +
                "ON DELETE CASCADE " +
                "ON UPDATE CASCADE, " +
                "FOREIGN KEY (subscribedUserId) REFERENCES Users (Id) " +
                "ON DELETE CASCADE " +
                "ON UPDATE CASCADE " +
                ");";
        jdbcTemplate.update(query);
    }
    public void dropSubscriptionsTable(){
        String query = "DROP TABLE subscriptions;";
        jdbcTemplate.update(query);
    }

    public Long createNewSubscription(Subscription subscription){
        String query = "INSERT INTO subscriptions (datetime, subscribeUserId, subscribedUserId)" +
                "VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setTimestamp(1, Timestamp.valueOf(subscription.getDateTime()));
            ps.setInt(2, subscription.getSubscribeUserId());
            ps.setInt(3, subscription.getSubscribedUserId());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

}
