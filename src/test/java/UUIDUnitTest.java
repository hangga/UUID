import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UUIDUnitTest {

    static int n = 1000000;

    @Test
    void whenGenerateWithgetLeastSignificantBits_thenCollisionsProbabilityCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getLeastSignificantBits());
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        // assertTrue(collisions > 0);
        //int uniqueCount = uniqueValues.size();
        System.out.println("-------------------------------");
        System.out.println("LeastSignificantBits");
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format((double)collisions/n));
    }

    @Test
    void whenGenerateWithgetMostSignificantBits_thenCollisionsProbabilityCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getMostSignificantBits());

            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        //assertTrue(collisions > 0);
        //int uniqueCount = uniqueValues.size();
        System.out.println("-------------------------------");
        System.out.println("MostSignificantBits");
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format((double)collisions/n));
    }


    @Test
    void whenGenerateWithhashCode_thenCollisionsProbabilityCheck() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;

        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.toString().hashCode());

            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        assertTrue(collisions > 0);
        //int uniqueCount = uniqueValues.size();
        System.out.println("-------------------------------");
        System.out.println("hashCode");
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format((double)collisions/n));
    }


    @Test
    void ByteBufferTest() {
        Set<Long> uniqueValues = new HashSet<>();
        int collisions = 0;
        for (int i = 0; i < n; i++) {

            UUID uuid = UUID.randomUUID();
            ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
            bb.putLong(uuid.getMostSignificantBits());
            bb.putLong(uuid.getLeastSignificantBits());
            long uniqueValue = Math.abs(bb.getLong(0));
            assertTrue(uniqueValue > 0);
            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        //assertTrue(collisions > 0);
        //int uniqueCount = uniqueValues.size();
        System.out.println("-------------------------------");
        System.out.println("ByteBuffer");
        System.out.println("Collisions Probability : " + new DecimalFormat("#.#####").format((double)collisions/n));
    }
}
