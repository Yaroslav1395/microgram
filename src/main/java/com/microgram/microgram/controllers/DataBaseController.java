package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.CustomerDto;
import com.microgram.microgram.entities.Customer;
import com.microgram.microgram.services.DataBaseQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контролер обрабатывающий запросы к базе данных
 */

@RequiredArgsConstructor
public class DataBaseController {
    private final DataBaseQueryService dataBaseQueryService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> customers(){
        return new ResponseEntity<>(dataBaseQueryService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String name){
        return new ResponseEntity<>(dataBaseQueryService.getCustomer(name), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> newCustomer(@RequestBody CustomerDto customer){
        return new ResponseEntity<>(dataBaseQueryService.createCustomer(customer), HttpStatus.OK);
    }
}
