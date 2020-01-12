package misiuk.jakub.blog.Newsletter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Newsletter implements Observable, Observer {


    Scanner scanner = new Scanner(System.in);


    private ArrayList<User> observers = new ArrayList<>();

    // Adres email osby która wysyła maila
    private static final String FROM = "BLOG1.0APP@gmail.com";

    // Temat wiadomości
    private static final String SUBJECT = "Blog newsletter - Nowy Artykuł !";
    // Treść wiadomości
    private static final String CONTENT = "Na blogu opublikowany został nowy artykuł! Zobacz";

    public void getUsers() {
        if (observers.isEmpty()) {
            System.out.println("Brak użytkowników w bazie");
        } else {
            for (User user : observers) {
                System.out.println(user);
            }
        }
    }

    @Override
    public void attach(User user) {
        observers.add(user);
    }

    @Override
    public void detach() {
        if (!observers.isEmpty()) {
            getUsers();
            System.out.println("Wpisz nick użytkownika do usunięcia!");
            String nickname = scanner.nextLine();
            for (int i = 0; i < observers.size(); i++) {
                if (observers.get(i).getNickname().equals(nickname)) {
                    observers.remove(observers.get(i));
                    System.out.println("Użytkownik usunięty");
                } else {
                    System.out.println("Brak użytkownika o podanym pseudonimie! Wybierz ponownie ");
                    detach();
                }
            }
        } else {
            System.out.println("Brak użytkowników w bazie!");
        }

    }

    @Override
    public void notifyObservers() {
        update(CONTENT);
    }


    public User readAndCreateUser() {
        System.out.println("Podaj adres email:");
        String email = scanner.nextLine();

        System.out.println("Podaj pseudonim: ");
        String nickname = scanner.nextLine();

        return new User(nickname, email);
    }


    @Override
    public void update(String topic) {
        if (!observers.isEmpty()) {
            send();
        } else {
            System.out.println("Brak subskrybentów, emaile nie wysłane!");
        }
    }


    private void send() {

        for (User observer : observers) {
            System.out.println("Na email " + observer.getEmail() + " został wysłany newsletter:::" + SUBJECT +" od " + FROM);
        }
    }
}
