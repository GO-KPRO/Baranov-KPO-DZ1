package bar.hse.ru.reversy.primitives;

public class Pair {
    private final int first;
    private final int second;

    public Pair(int f, int s) {
        first = f;
        second = s;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}