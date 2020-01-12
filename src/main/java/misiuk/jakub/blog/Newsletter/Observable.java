package misiuk.jakub.blog.Newsletter;

public interface Observable {

    void attach(User user);

    void detach();

    void notifyObservers();
}
