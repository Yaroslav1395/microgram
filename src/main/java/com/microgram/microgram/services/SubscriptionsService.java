package com.microgram.microgram.services;

import com.microgram.microgram.dao.SubscriptionsDao;
import com.microgram.microgram.dto.SubscriptionDto;
import com.microgram.microgram.entities.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionsService {
    private final SubscriptionsDao subscriptionsDao;

    public void createSubscriptionsTable(){
        subscriptionsDao.createSubscriptionsTable();
    }
    public void dropSubscriptionsTable(){
        subscriptionsDao.dropSubscriptionsTable();
    }
    public Long createNewSubscription(SubscriptionDto subscriptionDto){
        return subscriptionsDao.createNewSubscription(Subscription.builder()
                .subscribeUserId(subscriptionDto.getSubscribeUserId())
                .subscribedUserId(subscriptionDto.getSubscribedUserId())
                .dateTime(LocalDateTime.now())
                .build());
    }

}
