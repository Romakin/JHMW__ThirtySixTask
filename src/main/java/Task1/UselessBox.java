package Task1;

import java.util.concurrent.atomic.AtomicBoolean;

public class UselessBox {

    AtomicBoolean switcher;

    public UselessBox() {
        this.switcher = new AtomicBoolean(false);
    }

    void changeSwitch(boolean val) {
        switcher.set(val);
    }

    void switchBack() {
        System.out.println("[ --Активирован алгоритм стабилизации мирового баланса-- ]");
        while (true) {
            if (Thread.currentThread().isInterrupted()) return;
            if (switcher.get()) {
                System.out.println("Верни как было, кожанный мешок!");
                changeSwitch(false);
            }
        }
    }
}
