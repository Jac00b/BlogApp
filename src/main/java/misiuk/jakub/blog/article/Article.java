package misiuk.jakub.blog.article;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@Getter
@Setter

@EqualsAndHashCode
public class Article {

    private int articleId;
    private String topic;
    private String content;
    private LocalDateTime publicationDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public String toString() {
        return "Artykuł o id " + articleId +"-" +
                "Temat : " + topic +
                ", Treść: " + content +
                ", Data publikacji: " + showTime(publicationDate) +
                ", Data utworzenia: " + showTime(creationDate) +
                ", Data modyfikacji: " + showTime(updateDate);
    }

    public String showTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss"));
    }
}
