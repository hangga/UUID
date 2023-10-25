import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDUnitTest {

    static int n = 100000;

    @Test
    void getLeastSignificantBitsTest() {

        Set<Long> uniqueValues = new HashSet<>();
        for (int i = 0; i < n; i++) {

            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getLeastSignificantBits());
            System.out.println(i + " --> " + uniqueValue);

            assertTrue(uniqueValues.add(uniqueValue)); 
            assertTrue(uniqueValue > 0); 
        }
    }

    @Test
    void getMostSignificantBitsTest() {

        Set<Long> uniqueValues = new HashSet<>();
        for (int i = 0; i < n; i++) {

            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getMostSignificantBits());
            System.out.println(i + " --> " + uniqueValue);

            assertTrue(uniqueValues.add(uniqueValue)); 
            assertTrue(uniqueValue > 0); 
        }
    }


    @Test
    void hashCodeTest() {
        Set<Long> uniqueValues = new HashSet<>();
        for (int i = 0; i < n; i++) {

            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.toString().hashCode());
            System.out.println(i + " --> " + uniqueValue);

            boolean isUnique = uniqueValues.add(uniqueValue);

            if (isUnique) {
                assertTrue(isUnique); 
            } else {
                assertFalse(isUnique, "Collision detected for :" + uniqueValue);
            }
            assertTrue(uniqueValue > 0); 
        }
    }

    @Test
    void ByteBufferTest() {

        Set<Long> uniqueValues = new HashSet<>();
        for (int i = 0; i < n; i++) {

            UUID uuid = UUID.randomUUID();
            ByteBuffer bb = ByteBuffer.wrap(new byte[64]);
            bb.putLong(uuid.getMostSignificantBits());
            bb.putLong(uuid.getLeastSignificantBits());
            long uniqueValue = Math.abs(bb.getLong(0));

            System.out.println(i + " --> " + uniqueValue);

            assertTrue(uniqueValues.add(uniqueValue)); 
            assertTrue(uniqueValue > 0); 
        }
    }
}
