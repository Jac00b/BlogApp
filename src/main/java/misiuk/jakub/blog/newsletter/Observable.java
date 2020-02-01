package misiuk.jakub.blog.newsletter;

public interface Observable {

    void attach(User user);

    void detach();

    void notifyObservers();
}
