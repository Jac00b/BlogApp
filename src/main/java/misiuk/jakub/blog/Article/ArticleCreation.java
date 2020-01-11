package misiuk.jakub.blog.Article;

import misiuk.jakub.blog.Exceptions.WrongFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ArticleCreation {
    Scanner sc = new Scanner(System.in);
    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();

        } finally {
            sc.nextLine();
        }
    }


    public Article createArticle() throws DateTimeParseException {
            try {
                System.out.println("Wpisz id artykułu: ");
                String articleId = sc.nextLine();
                System.out.println("Podaj datę opublikowania w formacie dd/MM/yyyy: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                String date1 = sc.nextLine();
                LocalDate publicationDateTime = LocalDate.parse(date1, formatter);
                System.out.println("Podaj datę stworzenia artykułu w formacie dd/MM/yyyy: ");
                String date2 = sc.nextLine();
                LocalDate creationDateTime = LocalDate.parse(date2, formatter);
                System.out.println("Podaj datę modyfikacji w formacie dd/MM/yyyy: ");
                String date3 = sc.nextLine();
                LocalDate updateTime = LocalDate.parse(date3, formatter);
                System.out.println("Podaj temat: ");
                String topic = sc.nextLine();
                System.out.println("Wpisz treść artykułu: ");
                String content = sc.nextLine();

                return new Article(articleId, topic, content, publicationDateTime, creationDateTime, updateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Podałeś datę w złym formacie spróbuj ponownie");
                return createArticle();
            }



    }

}
