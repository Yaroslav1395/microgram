package entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class Comment {
    private String comment;
    private String dateTime;

    public Comment(String comment) {
        Objects.requireNonNull(comment, "Комментарий не должен быть пустым");
        this.comment = comment;
        dateTime = LocalDateTime.now().toString();
    }

}
