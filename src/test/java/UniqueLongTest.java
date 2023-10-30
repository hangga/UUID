import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniqueLongTest {

    private final static int n = 1000000;

    private void printTableHeader(){
        System.out.format("%-30s %-15s %-15s %-15s %-15s%n", "Approach", "collisions", "negatives", "collision", "negative");
        System.out.format("%-30s %-15s %-15s %-15s %-15s%n", "(method name)", "count", "count", "probability", "probability");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private void printOutput(String method, int collisionsCount, int negativeCount, double collisionsProbability, double negativeProbability){
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        System.out.format("%-30s %-15s %-15s %-15s %-15s%n", method, collisionsCount, negativeCount,
                decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

    @Test
    void forEachMethod_generatedLongValue_checkCollisions() {
        printTableHeader();
        UUIDLongGenerator uuidLongGenerator = new UUIDLongGenerator();
        Arrays.stream(uuidLongGenerator.getClass().getDeclaredMethods()).forEach(method -> {
            Set<Long> uniqueValues = new HashSet<>();
            AtomicInteger collisions = new AtomicInteger(0);
            AtomicInteger negative = new AtomicInteger(0);

            IntStream.range(0, n).forEach(i -> {
                try {
                    long uniqueValue = (long) method.invoke(uuidLongGenerator);
                    if (!uniqueValues.add(uniqueValue)) {
                        collisions.incrementAndGet();
                    }
                    if (uniqueValue < 0) {
                        negative.incrementAndGet();
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            double collisionsProbability = (double) collisions.get() / n;
            double negativeProbability = (double) negative.get() / n;
            printOutput(method.getName(), collisions.get(), negative.get(), collisionsProbability, negativeProbability);

            assertTrue(collisionsProbability <= 0.001); // threshold = 0.001
        });
    }

    /*@Test
    void forEachMethod_generatedLongValue_checkCollisions() {
        printTableHeader();
        UUIDLongGenerator uuidLongGenerator = new UUIDLongGenerator();
        Arrays.stream(uuidLongGenerator.getClass().getDeclaredMethods()).forEach(method -> {
            Set<Long> uniqueValues = new HashSet<>();
            final int[] collisions = {0};
            final int[] negative = {0};

            IntStream.range(0, n).forEach(i -> {
                try {
                    long uniqueValue = (long) method.invoke(uuidLongGenerator);
                    if (!uniqueValues.add(uniqueValue)) {
                        collisions[0]++;
                    }
                    if (uniqueValue < 0) {
                        negative[0]++;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            double collisionsProbability = (double) collisions[0] / n;
            double negativeProbability = (double) negative[0] / n;
            printOutput(method.getName(), collisions[0], negative[0], collisionsProbability, negativeProbability);

            assertTrue(collisionsProbability <= 0.001); // threshold = 0.001
        });
    }*/
}
