package entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class User implements Userable{
    private String nickName;
    private String email;
    private String password;
    private List<Publishable> publications = new ArrayList<>();
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
