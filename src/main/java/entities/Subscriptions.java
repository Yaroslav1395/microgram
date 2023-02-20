package entities;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class Subscriptions {
    private String subscribeUserName;
    private String subscribedUserName;
    private String dateTime;

    public Subscriptions(String subscribeUserName, String subscribedUserName) {
        Objects.requireNonNull(subscribeUserName, "Подписавшийся не должен быть пустым");
        Objects.requireNonNull(subscribeUserName, "Подписываемый не должен быть пустым");
        this.subscribeUserName = subscribeUserName;
        this.subscribedUserName = subscribedUserName;
        dateTime = LocalDateTime.now().toString();
    }
}

