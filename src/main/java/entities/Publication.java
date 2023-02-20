package entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
@Data
public class Publication implements Publishable{
    private String image;
    private String description;
    private String dateTime;

    public Publication(String image) {
        Objects.requireNonNull(image, "Ссылка на картинку не должна быть пустой");
        this.image = image;
        dateTime = LocalDateTime.now().toString();
    }

    public Publication(String image, String description) {
        this(image);
        Objects.requireNonNull(image, "Описание не должно быть пустым");
        this.description = description;
    }
}
