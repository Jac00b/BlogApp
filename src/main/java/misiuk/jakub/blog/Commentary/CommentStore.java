package misiuk.jakub.blog.Commentary;

import misiuk.jakub.blog.Article.Article;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentStore {
    Scanner scanner = new Scanner(System.in);

    private ArrayList<Comment> commentsList = new ArrayList<>();

    public void printComments() {
        if (commentsList.isEmpty()) {
            System.out.println("Brak artykułów w bazie");
        } else {
            for (Comment comment : commentsList) {
                System.out.println(comment);
            }
        }
    }

    public void addComment(Comment comment) {
        commentsList.add(comment);
    }

    public void removeCommentbyId() {
        if (!commentsList.isEmpty()) {
            printComments();
            System.out.println("Wybierz id komentarza do usunięcia!");
            String id = scanner.nextLine();
            for (int i = 0; i < commentsList.size(); i++) {
                if (commentsList.get(i).getCommentId().equals(id)) {
                    commentsList.remove(commentsList.get(i));
                } else {
                    System.out.println("Brak komentarza o podanym id! Wybierz ponownie ");
                    removeCommentbyId();
                }
            }
        } else {
            System.out.println("Lista komentarzy jest pusta");
        }
    }

    public Comment createComment() {
        System.out.println("Wprowadź id komentarza: ");
        String commentId = scanner.nextLine();
        System.out.println("Podaj id artykułu do ktorego chcesz dodać komentarz:");
        String articleId = scanner.nextLine();
        System.out.println("Podaj nazwę użytkownika:");
        String username = scanner.nextLine();
        System.out.println("Wpisz komentarz:");
        String comment = scanner.nextLine();

        return new Comment(commentId, articleId, username, comment);
    }

}

