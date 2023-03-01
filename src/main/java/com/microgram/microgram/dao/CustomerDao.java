package com.microgram.microgram.dao;

import com.microgram.microgram.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

/**
 * @Component - Аннотация для любого компонента фреймворка.
 */
@Component
@RequiredArgsConstructor
public class CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        String query ="select * from customers";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Customer.class));
    }
    public Customer getByName(String name){
        String query = "select * from customers " +
                "where name = ?;";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Customer.class), name);
    }

    public Long createCustomer(Customer customer){
        String query = "insert into customers " +
                "values(?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setLong(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setInt(3, customer.getAge());
            return  ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
