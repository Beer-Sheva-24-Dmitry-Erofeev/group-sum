package telran.numbers;

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
        int amount = tasks.length;
        for (int i = 0; i < amount; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            executor.submit(tasks[i]); // Задача передаётся пулу
        }

        // Завершаем работу пула
        executor.shutdown();
    }

}
