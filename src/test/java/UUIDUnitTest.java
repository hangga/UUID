import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDUnitTest {

    final static int n = 1000000;
    final static double threshold = 0.001;
    UniqueUUIDGenerator uniqueUUIDGenerator;

    @BeforeEach
    void setUp() {
        uniqueUUIDGenerator = new UniqueUUIDGenerator();
    }

    @Test
    void whenGivenLeastSignificantBits_thenCollisionsCheck() {

        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.getLeastSignificantBits();
            //System.out.println("getLeastSignificantBits(): " + uniqueValue);
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenMostSignificantBits_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.getMostSignificantBits();
            //System.out.println("Output : " + uniqueValue);
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }


    @Test
    void whenGivenHashCode_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;

        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.gethashCode();
            //System.out.println("gethashCode(): " + uniqueValue);
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        assertTrue(collisionsProbability <= threshold);
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }


    @Test
    void whenGivenByteBuffer_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.combineByteBuffer();
            //System.out.println("Output : " + uniqueValue);
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenByteBufferWrap_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.getByteBufferWrap();
            //System.out.println("ByteBufferWrap(): " + uniqueValue);
            assertTrue(uniqueValue >= 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenBitwise_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.combineBitwise();
            //System.out.println("Output: " + uniqueValue);
            assertTrue(uniqueValue >= 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenCombineDirect_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.combineDirect();
            //System.out.println("Output: " + uniqueValue);
            assertTrue(uniqueValue >= 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenPermutation_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.combinePermutation();
            //System.out.println("Output: " + uniqueValue);
            assertTrue(uniqueValue >= 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        //System.out.println("Example Output : " + uniqueUUIDGenerator.getMostSignificantBits());
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

}
