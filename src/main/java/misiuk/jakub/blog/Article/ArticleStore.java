package misiuk.jakub.blog.Article;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleStore {

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Article> articleList = new ArrayList<>();
    private static volatile ArticleStore instance;

    public static ArticleStore getInstance(){
        if (instance == null){
            synchronized (ArticleStore.class){
                instance = new ArticleStore();
            }
        }
        return instance;
    }


    public void getArticleList() {
        if (articleList.isEmpty()) {
            System.out.println("Brak artykułów w bazie");
        } else {
            for (Article article : articleList) {
                System.out.println(article);
            }
        }

    }

    public void addArticle(Article article) {
        articleList.add(article);
    }

    public void removeArticle() {
        if (!articleList.isEmpty()) {
            getArticleList();
            System.out.println("Wybierz id artykułu do usunięcia!");
            String id = scanner.nextLine();
            for (int i = 0; i < articleList.size(); i++) {
                    if (articleList.get(i).getArticleId().equals(id)) {
                        articleList.remove(articleList.get(i));
                    } else {
                        System.out.println("Brak artykułu o podanym id! Wybierz ponownie ");
                        removeArticle();
                    }
            }
        } else {
            System.out.println("Brak artykułów w bazie!");
        }

    }

    public boolean articleListIsEmpty() {
        return articleList.isEmpty();
    }

}
