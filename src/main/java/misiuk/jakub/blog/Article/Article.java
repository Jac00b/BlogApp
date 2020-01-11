package misiuk.jakub.blog.Article;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter

@EqualsAndHashCode
public class Article {

    private String articleId;
    private String topic;
    private String content;
    private LocalDate publicationDate;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @Override
    public String toString() {
        return "Artykuł o id " + articleId +"-" +
                "Temat : " + topic +
                ", Treść: " + content +
                ", Data publikacji: " + publicationDate +
                ", Data utworzenia: " + creationDate +
                ", Data modyfikacji: " + updateDate;
    }
}
