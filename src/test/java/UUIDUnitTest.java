import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UUIDUnitTest {

    final static int n = 1000000;
    final static double threshold = 0.001;
    UUIDLongGenerator UUIDLongGenerator = new UUIDLongGenerator();
    
    DecimalFormat decimalFormat = new DecimalFormat("#.#####");
    
    @Test
    @Order(0)
    void header() {
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n","Approach", "collisions", "negatives", "p(c)", "p(n)");
        System.out.println("------------------------------------------------------------------------------");
    }

    @Test
    void runTest() {
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n","Approach", "collisions", "negatives", "p(c)", "p(n)");
        System.out.println("------------------------------------------------------------------------------");
        //UUIDLongGenerator = new UUIDLongGenerator();
        Class<?> objClass = UUIDLongGenerator.getClass();

        // Dapatkan semua metode dari kelas tersebut
        Method[] methods = objClass.getDeclaredMethods();

        // Cetak nama semua metode
        for (Method method : methods) {
            //System.out.println("Method: " + method.getName());
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
                    decimalFormat.format(collisionsProbability),
                    decimalFormat.format(negativeProbability));
        }
    }


    /*void runTest(){
        UUIDLongGenerator = new UUIDLongGenerator();
        Class<?> objClass = UUIDLongGenerator.getClass();

        // Dapatkan semua metode dari kelas tersebut
        Method[] methods = objClass.getDeclaredMethods();

        // Cetak nama semua metode
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            Set<Long> uniqueValues = new HashSet<>();
            int collisions = 0;
            int negative = 0;
            for (int i = 0; i < n; i++) {
                try {

                    long uniqueValue = (long) method.invoke(method.getName());
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
            System.out.format("%-30s %-12s %-12s %-10s %-10s%n", method.getName(),collisions, negative,
                    decimalFormat.format(collisionsProbability),
                    decimalFormat.format(negativeProbability));
        }
    }*/

    @Test
    void whenGivenLeastSignificantBits_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.getLeastSignificantBits();
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
            if (uniqueValue < 0) {
                negative++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "leastSignificantBits()",collisions, negative,
                decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

    @Test
    void whenGivenMostSignificantBits_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.getMostSignificantBits();
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
            if (uniqueValue < 0) {
                negative++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "getMostSignificantBits()", collisions, negative, 
                decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }


    @Test
    void whenGivenHashCode_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.gethashCode();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "hashCode()",collisions, negative, 
                decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }


    @Test
    void whenGivenCombineByteBuffer_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.combineByteBuffer();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine ByteBuffer", collisions, negative, 
                decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

    /*@Test
    void whenGivenCombineByteBufferWrap_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.getByteBufferWrap();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "ByteBuffer Wrap", collisions, negative, decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }*/

    @Test
    void whenGivenCombineBitwise_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.combineBitwise();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Bitwise", collisions, negative, decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

    @Test
    void whenGivenCombineCombineDirect_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.combineDirect();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Direct", collisions, negative, decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

    @Test
    void whenGivenCombinePermutation_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = UUIDLongGenerator.combinePermutation();
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Permutation", collisions, negative, decimalFormat.format(collisionsProbability),
                decimalFormat.format(negativeProbability));
    }

}
