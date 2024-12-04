package telran.numbers;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GroupSumTest {

    private static final long N_GROUPS = 100_000;
    private static final long N_NUMBERS_PER_GROUP = 1_000;

    int[][] groups = {
        {1, 2}, {3, 4}, {5, 6}
    };

    static int[][] groupPerformance = Stream.generate(() -> getRandomArray()).limit(N_GROUPS).toArray(int[][]::new);

    private static int[] getRandomArray() {
        return new Random().ints(N_NUMBERS_PER_GROUP).toArray();
    }

    @Test
    void treadsPoolGroupSumTest() {
        runFunctionalTest(new ThreadsPoolGroupSum(groups));
    }

    @Test
    void performanceThreadsPoolGroupSumTest() {
        runPerformanceTest(new ThreadsPoolGroupSum(groupPerformance));
    }

    @Test
    @Disabled
    void threadsGroupSumTest() {
        runFunctionalTest(new ThreadsGroupSum(groups));
    }

    @Test
    @Disabled
    void performanceThreadsGroupSumTest() {
        runPerformanceTest(new ThreadsGroupSum(groupPerformance));
    }

    private void runFunctionalTest(GroupSum groupSum) {
        assertEquals(21, groupSum.computeSum());
    }

    private void runPerformanceTest(GroupSum groupSum) {
        groupSum.computeSum();
    }
}
