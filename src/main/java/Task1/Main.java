package Task1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UselessBox box = new UselessBox();
        User user = new User(box);
        Thread boxThread = new Thread(null, box::switchBack, "Box");
        boxThread.start();
        Thread userThread = new Thread(null, user::tryToSwitch, "StupidUser");
        userThread.start();

        while (userThread.isAlive()) {
            Thread.sleep(500);
        }
        boxThread.interrupt();
    }
}
