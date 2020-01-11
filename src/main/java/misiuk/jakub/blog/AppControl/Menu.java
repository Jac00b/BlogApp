package misiuk.jakub.blog.AppControl;

import misiuk.jakub.blog.Article.Article;
import misiuk.jakub.blog.Article.ArticleCreation;
import misiuk.jakub.blog.Article.ArticleStore;
import misiuk.jakub.blog.Commentary.Comment;
import misiuk.jakub.blog.Commentary.CommentStore;
import misiuk.jakub.blog.Exceptions.NoSuchOptionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    ArticleStore articleStore = new ArticleStore();
    ArticleCreation articleCreation = new ArticleCreation();
    CommentStore commentStore = new CommentStore();

    public void controlLoop() {

        Option option;
    do{
        printOptions();
        option = getOption();
        switch (option){

            case EXIT:
                exit();
                break;
            case ADD_ARTICLE:
                addArticle();
                break;
            case REMOVE_ARTICLE:
                removeArticle();
                break;
            case PRINT_ARTICLES:
                printArticles();
                break;
            case ADD_COMMENT:
                addComment();
                break;
            case REMOVE_COMMENT:
                removeComment();
                break;
            case PRINT_COMMENT:
                printComment();
                break;
        }

    } while (option != Option.EXIT);


    }

    private void printComment() {
        commentStore.printComments();
    }

    private void removeComment() {
        commentStore.removeCommentbyId();
    }

    private void addComment() {
        if (!articleStore.articleListIsEmpty()) {
            Comment comment = commentStore.createComment();
            commentStore.addComment(comment);
        } else {
            System.out.println("Brak artykułów w bazie, Nie można dodać komentarza!");
        }
    }

    private void printArticles() {
        articleStore.getArticleList();


    }

    private void removeArticle() {
        articleStore.removeArticle();
    }

    private void addArticle() {
        Article article = articleCreation.createArticle();
        articleStore.addArticle(article);
    }

    private void exit() {
        System.out.println("Koniec programu!");
        articleCreation.close();
    }

    private Option getOption() {
        boolean OptionOk = false;
        Option option = null;
        while (!OptionOk) {
            try {
                option = Option.createFromInt(articleCreation.getInt());
                OptionOk = true;
            } catch (NoSuchOptionException e) {
                printLine(e.getMessage());
            } catch (InputMismatchException e){
                printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie");
            }
        }
        return option;
    }

    public void printLine(String text){
        System.out.println(text);
    }

    public void printOptions() {
        System.out.println("Wybierz opcję: ");
        for (Option value : Option.values()) {
            System.out.println(value);
        }

    }

    public enum Option {
        EXIT(0, "WYJŚCIE Z PROGRAMU."),
        ADD_ARTICLE(1, "Utwórz artykuł"),
        REMOVE_ARTICLE(2, "Usuń artykuł z bazy"),
        PRINT_ARTICLES(3, "Wyświetl artykuły"),
        ADD_COMMENT(4, "Dodaj komentarz do artykułu o podanym id"),
        REMOVE_COMMENT(5, "Usuń komentarz o podanym id"),
        PRINT_COMMENT(6, "Wyświetl wszystkie komentarze");


        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + "-" + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id: " + option);
            }
        }


    }


}

