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
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.println("Collisions Probability getLeastSignificantBits(): " + new DecimalFormat("#.#####").format(collisionsProbability));
    }

    @Test
    void whenGivenMostSignificantBits_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.getMostSignificantBits();
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.println("Collisions Probability getMostSignificantBits(): " + new DecimalFormat("#.#####").format(collisionsProbability));
    }


    @Test
    void whenGivenHashCode_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;

        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.gethashCode();
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.println("Collisions Probability hashCode(): " + new DecimalFormat("#.#####").format(collisionsProbability));
    }


    @Test
    void whenGivenByteBuffer_thenCollisionsCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            long uniqueValue = uniqueUUIDGenerator.getByteBuffer();
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        double collisionsProbability = (double) collisions / n;
        assertTrue(collisionsProbability <= threshold);
        System.out.println("Collisions Probability ByteBuffer : " + new DecimalFormat("#.#####").format(collisionsProbability));
    }
}
