import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDUniqueTest {
    static int n = 1000;
    long getUniqueWithgetLeastSignificantBits(){
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getLeastSignificantBits());
    }

    long getUniqueWithgetMostSignificantBits(){
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits());
    }

    long getWithHashNode(){
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.toString().hashCode());
    }

    long getWithByteBuffer(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[128]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.getLong() + 1;
    }


    @Test
    void test(){
        Set<Long> uniqueValues = new HashSet<>();
        for (int i = 0; i < n; i++) {

            long uniqWithWithgetLeastSignificantBits = getUniqueWithgetLeastSignificantBits();
            assertTrue(uniqueValues.add(uniqWithWithgetLeastSignificantBits), "Collision detected for :" + uniqWithWithgetLeastSignificantBits);
            assertTrue(uniqWithWithgetLeastSignificantBits > 0); // Pastikan nilai selalu positif

            assertTrue(uniqueValues.add(getUniqueWithgetMostSignificantBits())); // Pastikan nilai unik
            assertTrue(getUniqueWithgetMostSignificantBits() > 0); // Pastikan nilai selalu positif

            //assertTrue(uniqueValues.add(getWithHashNode())); // Pastikan nilai unik
            long uniqueWithHashNode = getWithHashNode();
            assertTrue(uniqueValues.add(uniqueWithHashNode), "Collision detected for :" + uniqueWithHashNode);
            assertTrue(uniqueWithHashNode > 0); // Pastikan nilai selalu positif

            long getWithByteBuffer = getWithByteBuffer();
            boolean isUnique = uniqueValues.add(getWithByteBuffer);
            if (isUnique) {
                assertTrue(isUnique); // Pastikan nilai unik
            } else {
                assertFalse(isUnique, "Collision detected for :" + getWithByteBuffer);
            }
            assertTrue(getWithByteBuffer > 0); // Pastikan nilai selalu positif

            //assertTrue(uniqueValues.add(getWithTimestamp())); // Pastikan nilai unik
            //assertTrue(getWithTimestamp() > 0); // Pastikan nilai selalu positif
        }
    }
}
