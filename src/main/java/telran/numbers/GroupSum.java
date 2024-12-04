package telran.numbers;

public abstract class GroupSum {

    // Двумерный массив
    int[][] groups;

    // Конструктор
    public GroupSum(int[][] groups) {
        this.groups = groups;
    }

    // Некое абстрактное высчитывание суммы
    public abstract long computeSum();
}
