import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDUniqueTest {
    final static int n = 1000000;
    final static double threshold = 0.001;
    UUIDLongGenerator UUIDLongGenerator = new UUIDLongGenerator();
    DecimalFormat decimalFormat = new DecimalFormat("#.#####");

    @Test
    void whenForeachGenerateLongValue_thenCollisionsCheck() {
        printTableHeader();
        Class<?> objClass = UUIDLongGenerator.getClass();
        Method[] methods = objClass.getDeclaredMethods();
        for (Method method : methods) {
            collisionCheck(method);
        }
    }

    private void printTableHeader() {
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Approach/method", "collisions", "negatives", "p(c)", "p(n");
        System.out.println("------------------------------------------------------------------------------");
    }

    private void collisionCheck(Method method) {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            try {
                long uniqueValue = (long) method.invoke(UUIDLongGenerator);
                if (!uniqueValues.add(uniqueValue)) {
                    collisions++;
                }
                if (uniqueValue < 0) {
                    negative++;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", method.getName(), collisions, negative,
                decimalFormat.format(collisionsProbability), decimalFormat.format(negativeProbability));
    }
}
