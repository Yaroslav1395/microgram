package entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Like {
    private String whoLiked;
    private String likedPublicationId;
    private String dateTime;

    public Like(String whoLiked, String likedPublicationId) {
        Objects.requireNonNull(whoLiked, "Лайкнувший не должен быть пустым");
        Objects.requireNonNull(likedPublicationId, "ID публикации не должен быть пустым");
        this.whoLiked = whoLiked;
        this.likedPublicationId = likedPublicationId;
        dateTime = LocalDateTime.now().toString();
    }

}
