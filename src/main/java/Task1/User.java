package Task1;

public class User {

    UselessBox box;
    int tryCount = 4;

    public User(UselessBox box) {
        this.box = box;
    }

    void tryToSwitch() {
        System.out.println("О, коробочка.");
        try {
            while (tryCount-- > 0) {
                System.out.println("Тык");
                Thread.sleep(2000);
                box.changeSwitch(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Да ну его.");
    }
}
