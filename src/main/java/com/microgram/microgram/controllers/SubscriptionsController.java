package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.SubscriptionDto;
import com.microgram.microgram.services.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionsController {
    private final SubscriptionsService subscriptionsService;

    @GetMapping("/createSubscriptionsTable")
    public void createSubscriptionsTable(){
        subscriptionsService.createSubscriptionsTable();
    }
    @GetMapping("/dropSubscriptionsTable")
    public void dropSubscriptionsTable(){
        subscriptionsService.dropSubscriptionsTable();
    }
    @PostMapping("/createNewSubscription")
    public ResponseEntity<Long> createNewSubscription(@RequestBody SubscriptionDto subscriptionDto){
        return new ResponseEntity<>(subscriptionsService.createNewSubscription(subscriptionDto), HttpStatus.OK);
    }
}
