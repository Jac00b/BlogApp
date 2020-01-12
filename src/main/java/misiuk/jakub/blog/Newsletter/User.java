package misiuk.jakub.blog.Newsletter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String nickname;
    private String email;


    @Override
    public String toString() {
        return "Użytkownik: "+
                "Pseudonim:" + nickname +
                ", email: " + email;
    }
}
