package misiuk.jakub.blog.article;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleStore {

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Article> articleList = new ArrayList<>();


    public void close() {
        scanner.close();
    }

    public int getInt() {
        try {
            return scanner.nextInt();

        } finally {
            scanner.nextLine();
        }
    }


    public Article createArticle() {
        System.out.println("Podaj temat: ");
        String topic = scanner.nextLine();
        System.out.println("Wpisz treść artykułu: ");
        String content = scanner.nextLine();
        int articleId = getNewId();
        return new Article(articleId, topic, content, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public int getNewId() {
        int id = articleList.size();
        return id;
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

    public void editArticle() {
      if (articleList.isEmpty()){
          System.out.println("Brak artykułów w bazie!");
      } else {
          getArticleList();
          System.out.println("Wybierz artykuł do edycji wpisując id");
          int id = scanner.nextInt();
          scanner.nextLine();
          for (Article article : articleList) {
              if (article.getArticleId() == id) {
                  System.out.println("Wpisz nową treść artykułu: ");
                  String newContent = scanner.nextLine();
                  
                  article.setContent(newContent);
                  System.out.println("Wpisz nowy tytuł: ");
                  String title = scanner.nextLine();
                  article.setTopic(title);


              } else {
                  System.out.println("Brak artykułu o podanym id w bazie");
              }
          }
      }
    }




    public void removeArticle() {
        if (!articleList.isEmpty()) {
            getArticleList();
            System.out.println("Wybierz id artykułu do usunięcia!");
            int id = scanner.nextInt();
            for (int i = 0; i < articleList.size(); i++) {
                if (articleList.get(i).getArticleId() == id) {
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

    public void printArticlesById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getArticleId() == id) {
                System.out.println(articleList.get(i));
            }
        }

    }
}
