import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UUIDUnitTest {

    final static int n = 1000000;
    final static double threshold = 0.001;
    UniqueLongGenerator uniqueLongGenerator = new UniqueLongGenerator();

    /*@BeforeEach
    void setUp() {
        uniqueLongGenerator = new UniqueLongGenerator();
        System.out.println("Approach |P(C)");
    }*/

    @Test
    @Order(0)
    void header() {
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n","Approach", "collisions", "negatives", "p(c)", "p(n)");
        System.out.println("---------------------------------------------------------------------------------");
    }

    @Test
    void whenGivenLeastSignificantBits_thenCollisionsCheck() {

        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.getLeastSignificantBits();
            //System.out.println("getLeastSignificantBits(): " + uniqueValue);
            //assertTrue(uniqueValue > 0);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "leastSignificantBits()",collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("LeastSignificantBits |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

    @Test
    void whenGivenMostSignificantBits_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.getMostSignificantBits();
            //System.out.println("Output : " + uniqueValue);
            //assertTrue(uniqueValue > 0);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "getMostSignificantBits()", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("MostSignificantBits  |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }


    @Test
    void whenGivenHashCode_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.gethashCode();
            //System.out.println("gethashCode(): " + uniqueValue);
            //assertTrue(uniqueValue > 0);
            if (uniqueValue < 0) {
                negative++;
            }
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        double negativeProbability = (double) negative / n;
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        assertTrue(collisionsProbability <= threshold);
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "hashCode()",collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("HashCode             |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }


    @Test
    void whenGivenCombineByteBuffer_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.combineByteBuffer();
            //System.out.println("Output : " + uniqueValue);
            //assertTrue(uniqueValue > 0);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine ByteBuffer", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("ByteBuffer           |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

    @Test
    void whenGivenCombineByteBufferWrap_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.getByteBufferWrap();
            //System.out.println("ByteBufferWrap(): " + uniqueValue);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "ByteBuffer Wrap", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("ByteBufferWrap       |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

    @Test
    void whenGivenCombineBitwise_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.combineBitwise();
            //System.out.println("Output: " + uniqueValue);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Bitwise", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("Bitwise              |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

    @Test
    void whenGivenCombineCombineDirect_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.combineDirect();
            //System.out.println("Output: " + uniqueValue);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Direct", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("CombineDirect        |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

    @Test
    void whenGivenCombinePermutation_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        int negative = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueLongGenerator.combinePermutation();
            //System.out.println("Output: " + uniqueValue);
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
        //System.out.println("Example Output : " + uniqueLongGenerator.getMostSignificantBits());
        //System.out.println("Permutation          |"+ collisions +"  |  "+ negative +"  |  "+  new DecimalFormat("#.#####").format(collisionsProbability));
        System.out.format("%-30s %-12s %-12s %-10s %-10s%n", "Combine Permutation", collisions, negative, new DecimalFormat("#.#####").format(collisionsProbability),
                new DecimalFormat("#.#####").format(negativeProbability));
        /*System.out.println("Permutation          |   " + collisions + "    |   " + negative + "   |    "
                + new DecimalFormat("#.#####").format(collisionsProbability)+" |    " +new DecimalFormat("#.#####").format(negativeProbability));*/
    }

}
