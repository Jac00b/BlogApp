package misiuk.jakub.blog.Commentary;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentStore {
    Scanner scanner = new Scanner(System.in);


    private ArrayList<Comment> commentsList = new ArrayList<>();


    public void printComments() {
        if (commentsList.isEmpty()) {
            System.out.println("Brak komentarzy w bazie");
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
            int id = scanner.nextInt();
            for (int i = 0; i < commentsList.size(); i++) {
                if (commentsList.get(i).getCommentId() == id) {
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

        System.out.println("Podaj nazwę użytkownika:");
        String username = scanner.nextLine();
        System.out.println("Wpisz komentarz:");
        String comment = scanner.nextLine();
        System.out.println("Podaj id artykułu do ktorego chcesz dodać komentarz:");
        int articleId = scanner.nextInt();

        int commentId = getNewId();
        return new Comment(commentId, articleId, username, comment);
    }

    private int getNewId() {
        return commentsList.size();
    }

    public void printCommentsById(int id) {

        for (int i = 0; i < commentsList.size(); i++) {
            if (commentsList.get(i).getArticleId() == id) {
                System.out.println(commentsList.get(i));
            }
        }
    }
}

