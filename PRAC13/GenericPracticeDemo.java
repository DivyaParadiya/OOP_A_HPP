
class Box<T> {
    private T value;

    void set(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    A getFirst() {
        return first;
    }

    B getSecond() {
        return second;
    }
}

public class GenericPracticeDemo {
    static <T> T firstElement(T[] array) {
        return array[0];
    }

    static <N extends Number> double average(N[] values) {
        double sum = 0;

        for (N value : values)
            sum += value.doubleValue();

        return sum / values.length;
    }

    public static void main(String[] args) {
        System.out.println("1. GENERIC CONTAINERS");

        Box<String> b1 = new Box<>();
        b1.set("Hello Java");
        System.out.println("String Box: " + b1.get());

        Box<Integer> b2 = new Box<>();
        b2.set(100);
        System.out.println("Integer Box: " + b2.get());

        Pair<String, Integer> p = new Pair<>("Aarav", 20);
        System.out.println("Pair: " + p.getFirst() + ", " + p.getSecond());

        String[] names = {"Diya", "Riya", "Aarav"};
        Integer[] numbers = {10, 20, 30};

        System.out.println("First name: " + firstElement(names));
        System.out.println("First number: " + firstElement(numbers));

        System.out.println("\n2. BOUNDED STATS");

        Integer[] integers = {10, 20, 30};
        Double[] decimals = {2.5, 3.5, 4.5};
        Long[] longs = {100L, 200L, 300L};

        System.out.println("Integer average: " + average(integers));
        System.out.println("Double average: " + average(decimals));
        System.out.println("Long average: " + average(longs));
    }
}
