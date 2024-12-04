package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadsGroupSum extends GroupSum {

    public ThreadsGroupSum(int[][] groups) {
        super(groups);
    }

    @SuppressWarnings("unchecked")
    @Override
    public long computeSum() {
        FutureTask<Long>[] tasks = new FutureTask[groups.length];
        startTasks(tasks);
        return getSum(tasks);
    }

    protected void startTasks(FutureTask<Long>[] tasks) {
        int amount = tasks.length;
        for (int i = 0; i < amount; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            new Thread(tasks[i]).start();
        }
    }

    private long getSum(FutureTask<Long>[] tasks) {
        return Arrays.stream(tasks).mapToLong(t -> {
            try {
                return t.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

}
