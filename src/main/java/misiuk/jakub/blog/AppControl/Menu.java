package misiuk.jakub.blog.AppControl;

import misiuk.jakub.blog.Article.Article;
import misiuk.jakub.blog.Article.ArticleStore;
import misiuk.jakub.blog.Commentary.Comment;
import misiuk.jakub.blog.Commentary.CommentStore;
import misiuk.jakub.blog.Exceptions.NoSuchOptionException;
import misiuk.jakub.blog.Newsletter.Newsletter;
import misiuk.jakub.blog.Newsletter.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    private ArticleStore articleStore = new ArticleStore();
    private CommentStore commentStore = new CommentStore();
    private Newsletter newsletter = new Newsletter();

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
            case PRINT_ARTICLE_WITH_COMMENTS:
                printArticlesWithComments();
                break;
            case ADD_SUBSCRIBER:
                addSubscriber();
                break;
            case REMOVE_SUBSCRIBER:
                removeSubscriber();
                break;
            case PRINT_SUBSCRIBERS:
                printSubscribers();
                break;
        }

    } while (option != Option.EXIT);


    }

    private void printSubscribers() {
        newsletter.getUsers();
    }

    private void removeSubscriber() {
        newsletter.detach();

    }

    private void addSubscriber() {
        User user = newsletter.readAndCreateUser();
        if(isValidEmailAddress(user.getEmail())) {
            newsletter.attach(user);
        } else {
            System.out.println("Nieprawidłowy adres email u użytkownika, utwórz ponownie");
            addSubscriber();
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private void printArticlesWithComments() {
        printArticles();
        System.out.println("Podaj id artykułu do wyświetlenia");
        int id = sc.nextInt();
        articleStore.printArticlesById(id);
        commentStore.printCommentsById(id);
    }

    private void printComment() {
        commentStore.printComments();
    }

    private void removeComment() {
        commentStore.removeCommentbyId();
    }

    private void addComment() {
        if (!articleStore.articleListIsEmpty()) {
            articleStore.getArticleList();
            try {
                Comment comment = commentStore.createComment();
                commentStore.addComment(comment);
            } catch (InputMismatchException e){
                System.out.println("Wprowadzono nieprawidłowe id komentarza musisz wprowadzić komentarz ponownie");
            }
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
        Article article = articleStore.createArticle();
        articleStore.addArticle(article);
        System.out.println("Artykuł dodany ! ");
        newsletter.notifyObservers();

    }

    private void exit() {
        System.out.println("Koniec programu!");
        articleStore.close();
    }

    private Option getOption() {
        boolean OptionOk = false;
        Option option = null;
        while (!OptionOk) {
            try {
                option = Option.createFromInt(articleStore.getInt());
                OptionOk = true;
            } catch (NoSuchOptionException e) {
                printLine(e.getMessage());
            } catch (InputMismatchException e){
                printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie");
            }
        }
        return option;
    }

    private void printLine(String text){
        System.out.println(text);
    }

    private void printOptions() {
        System.out.println("*********************MENU*************************");
        for (Option value : Option.values()) {
            System.out.println(value);
        }

        System.out.println("*****************BLOG v.1.0***********************");
    }

    public enum Option {
        EXIT(0, "WYJŚCIE Z PROGRAMU.                          *"),
        ADD_ARTICLE(1, "UTWÓRZ ARTYKUŁ                               *"),
        REMOVE_ARTICLE(2, "USUŃ ARTYKUŁ                                 *"),
        PRINT_ARTICLES(3, "WYŚWIETL ARTYKUŁY                            *"),
        ADD_COMMENT(4, "DODAJ KOMENTARZ DO ARTYKUŁU O PODANYM ID     *"),
        REMOVE_COMMENT(5, "USUŃ KOMENTARZ O PODANYM ID                  *"),
        PRINT_COMMENT(6, "WYŚWIETL WSZYSTKIE KOMENTARZE                *"),
        PRINT_ARTICLE_WITH_COMMENTS(7, "WYŚWIETL ARTYKUŁ O PODANYM ID Z KOMENTARZAMI *"),
        ADD_SUBSCRIBER(8, "DODAJ UŻYTKOWNIKA DO NEWSLETTERA             *"),
        REMOVE_SUBSCRIBER(9, "USUŃ UŻYTKOWNIKA Z NEWSLETTERA               *"),
        PRINT_SUBSCRIBERS(10, "WYŚWIETL WSZYSTKICH UŻYTKOWNIKÓW W BAZIE    *");




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
            return "* "+ value + "-" + description;
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

