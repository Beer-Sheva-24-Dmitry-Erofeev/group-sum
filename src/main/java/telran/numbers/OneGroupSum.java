package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.Callable;

// Callable - что-то вызываемое. Выполняет действие и возвращает результат
// или выбрасывает exeption при проблемах.
// Тут у нас просто считается сумма всего массива на входе, когда вызывается call()
public class OneGroupSum implements Callable<Long> {

    private final int[] group;

    // Конструктор
    public OneGroupSum(int[] group) {
        this.group = group;
    }

    @Override
    public Long call() throws Exception {
        return Arrays.stream(group).asLongStream().sum();
    }

}
