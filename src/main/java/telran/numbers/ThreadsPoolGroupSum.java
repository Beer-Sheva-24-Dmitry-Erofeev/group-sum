package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        // Создаём пул потоков
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Запускаем задачи в пуле
        Arrays.stream(tasks).forEach(t -> executor.execute(t));

        // Завершаем работу пула
        executor.shutdown();
    }

}
