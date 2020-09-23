package Task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        int filialsNum = 3;
        int procCount = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(procCount < filialsNum ? procCount : filialsNum);

        List<List<Integer>> shopsIncome = new ArrayList<>();
        while (filialsNum-- > 0) {
            shopsIncome.add(getfilialIncome());
        }

        AtomicInteger filialNum = new AtomicInteger(1);
        LongAdder stat = new LongAdder();

        shopsIncome.forEach(incm -> executorService.submit(() -> {
            int income = incm.stream().reduce((x, y) -> x + y ).get();
            System.out.printf(" Доход филиала %d: %d \n", filialNum.getAndIncrement(), income);
            stat.add(income);
        }));

        executorService.awaitTermination(4, TimeUnit.SECONDS);

        System.out.println("\nОбщий доход: " + stat.sum());
        executorService.shutdown();
    }

    static List<Integer> getfilialIncome() {
        List<Integer> res = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> res.add(rand(i, 1000)));
        return res;
    }

    public static int rand(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }
}
