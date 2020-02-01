package misiuk.jakub.blog.appControl;


public class App {

    public static String APP_NAME = "BLOG v.1.0";
    public static void main(String[] args) {
        System.out.println(APP_NAME);
        Menu menu = new Menu();
        menu.controlLoop();

    }
}
