import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDUniqueTest {
    static int n = 1000000;
    private Set<Long> uniqueValues;

    @BeforeEach
    void setUp() {
        uniqueValues = new HashSet<>();
    }

    @Test
    void testGetLeastSignificantBits() {
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getLeastSignificantBits());

            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        System.out.println("Collisions : " + (double)collisions/n);
        assertTrue(collisions < n * 0.01); // You can adjust the threshold as needed
    }

    @Test
    void testGetMostSignificantBits() {
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.getMostSignificantBits());

            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        System.out.println("Collisions : " + collisions /n);
        assertTrue(collisions < n * 0.01); // You can adjust the threshold as needed
    }

    @Test
    void testHashCode() {
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            long uniqueValue = Math.abs(uuid.toString().hashCode());

            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        System.out.println("Collisions : " + collisions /n);
        assertTrue(collisions < n * 0.01); // You can adjust the threshold as needed
    }

    @Test
    void testByteBuffer() {
        int collisions = 0;
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
            bb.putLong(uuid.getMostSignificantBits());
            bb.putLong(uuid.getLeastSignificantBits());
            long uniqueValue = Math.abs(bb.getLong(0));

            if (!uniqueValues.add(uniqueValue)) {
                collisions++;
            }
        }
        System.out.println("Collisions : " + (double)collisions/n);
        assertTrue(collisions < n * 0.01); // You can adjust the threshold as needed
    }
}
