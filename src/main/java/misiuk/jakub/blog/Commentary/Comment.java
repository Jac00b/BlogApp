package misiuk.jakub.blog.Commentary;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Comment {

    private String commentId;
    private String articleId;
    private String username;
    private String text;


    @Override
    public String toString() {
        return "Komentarz o id " +commentId+ " do artykułu o id " +articleId+" dodany przez " +
                "użytkownika: " + username +
                "(" + text+").";

    }
}
