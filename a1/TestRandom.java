public class TestRandom {

    public static void main(String[] args) {

        // Integer random
        int r1 = RandomAlgorithm.generate(1, 100);
        System.out.println("Random Integer: " + r1);

        // Double random
        double r2 = RandomAlgorithm.generate(0.0, 10.0);
        System.out.println("Random Double: " + r2);

        // Float random
        float r3 = RandomAlgorithm.generate(5.0f, 20.0f);
        System.out.println("Random Float: " + r3);

        // Unique numbers
        System.out.println("Unique Integers: "
                + RandomAlgorithm.generateUnique(5, 1, 50));
    }
}
