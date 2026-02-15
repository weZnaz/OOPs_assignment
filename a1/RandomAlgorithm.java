import java.util.HashSet;
import java.util.Set;

public class RandomAlgorithm {

    private static long seed = System.nanoTime();

    // Core Novel Algorithm
    // f(|x|, |n|) = x * (n/x - 1)  and bit shifting
    private static long core(long x, long n) {
        long absX = Math.abs(x);
        long absN = Math.abs(n);

        if (absX == 0) absX = 1;

        long result = absX * (absN / absX - 1);

        result = result ^ (result << 13);
        result = result ^ (result >> 7);
        result = result ^ (result << 17);

        return Math.abs(result);
    }

    // 🔹 Overloaded method for int
    public static int generate(int min, int max) {
        seed = core(seed, max);
        return (int) (min + (seed % (max - min + 1)));
    }

    // 🔹 Overloaded method for double
    public static double generate(double min, double max) {
        seed = core(seed, (long) max);
        return min + (seed / (double) Long.MAX_VALUE) * (max - min);
    }

    // 🔹 Overloaded method for float
    public static float generate(float min, float max) {
        seed = core(seed, (long) max);
        return min + (seed / (float) Long.MAX_VALUE) * (max - min);
    }

    // 🔹 Unique Integer Generator
    public static Set<Integer> generateUnique(int count, int min, int max) {
        Set<Integer> set = new HashSet<>();
        while (set.size() < count) {
            set.add(generate(min, max));
        }
        return set;
    }
}
