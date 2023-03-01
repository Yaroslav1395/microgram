package com.microgram.microgram.services;

import com.microgram.microgram.dao.CustomerDao;
import com.microgram.microgram.dao.PublicationsDao;
import com.microgram.microgram.dao.UsersDao;
import com.microgram.microgram.dto.CustomerDto;
import com.microgram.microgram.entities.Customer;
import com.microgram.microgram.entities.Publication;
import com.microgram.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service - аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
 * Сервис является подтипом класса @Component. Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Data - аннотация заменяет getter, setter, toString, hashcode, equals.
 * Класс производит соединение к БД, и предоставляет возможность работать с ней.
 */
@Service
@RequiredArgsConstructor
public class DataBaseQueryService {
    private final CustomerDao customerDao;
    private final PublicationsDao publicationDao;


    public List<Customer> getCustomers(){
        return customerDao.getAllCustomers();
    }
    public Customer getCustomer(String name){
        return customerDao.getByName(name);
    }
    public Long createCustomer(CustomerDto customer){
        return customerDao.createCustomer(Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .build());
    }



}
