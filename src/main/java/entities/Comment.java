package entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class Comment {
    private String comment;
    private Userable commentUser;
    private Publishable commentPublication;
    private String dateTime;

    public Comment(String comment, Userable commentUser, Publishable commentPublication) {
        Objects.requireNonNull(comment, "Комментарий не должен быть пустым");
        Objects.requireNonNull(comment, "Оставивший комментарий не должен быть пустым");
        Objects.requireNonNull(comment, "Комментируемая публикация не должна быть пустой");
        this.comment = comment;
        this.commentUser = commentUser;
        this.commentPublication = commentPublication;
        dateTime = LocalDateTime.now().toString();
    }
}
