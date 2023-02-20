package entities;

import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private String nickName;
    private String email;
    private String password;
    private int publicationsNumber = 0;
    private int subscriptionsNumber = 0;
    private int subscribersNumber = 0;

    public User(String nickName, String email, String password) {
        Objects.requireNonNull(nickName, "Имя не должно быть пустым");
        Objects.requireNonNull(email, "Email не должен быть пустым");
        Objects.requireNonNull(password, "Пароль не должен быть пустым");
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }
}
