import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueLongGenerator {

    public long getLeastSignificantBits(){
        /*long leastSignificantBits =*/ return UUID.randomUUID().getLeastSignificantBits();
        //return Math.abs(leastSignificantBits);
    }

    public long getMostSignificantBits(){
        /*long mostSignificantBits =*/ return UUID.randomUUID().getMostSignificantBits();
        //return Math.abs(mostSignificantBits);
    }

    public long gethashCode(){
        /*long hashCode =*/  return UUID.randomUUID().toString().hashCode();
        //return Math.abs(hashCode);
    }

    public long combineByteBuffer(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        bb.rewind(); // Kembalikan posisi buffer ke awal
        //return Math.abs(bb.getLong());
        return bb.getLong();
    }

    public long getByteBufferWrap(){
        UUID originalUUID = UUID.randomUUID();

        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(originalUUID.getMostSignificantBits());
        buffer.putLong(originalUUID.getLeastSignificantBits());
        byte[] uuidBytes = buffer.array();

        buffer = ByteBuffer.wrap(uuidBytes);
        long mostSigBits = buffer.getLong();
        long leastSigBits = buffer.getLong();
        UUID reconstructedUUID = new UUID(mostSigBits, leastSigBits);

        //return Math.abs(reconstructedUUID.getMostSignificantBits());
        return reconstructedUUID.getMostSignificantBits();
    }

    public long combineBitwise(){
        UUID uniqueUUID = UUID.randomUUID();
        //return Math.abs((uniqueUUID.getMostSignificantBits() << 32) | (uniqueUUID.getLeastSignificantBits() & 0xFFFFFFFFL));
        return (uniqueUUID.getMostSignificantBits() << 32) | (uniqueUUID.getLeastSignificantBits() & 0xFFFFFFFFL);
    }

    public long combineDirect(){
        UUID uniqueUUID = UUID.randomUUID();
        long mostSignificantBits = uniqueUUID.getMostSignificantBits();
        long leastSignificantBits = uniqueUUID.getLeastSignificantBits();
        //return Math.abs(mostSignificantBits ^ (leastSignificantBits >> 1));
        return mostSignificantBits ^ (leastSignificantBits >> 1);
    }

    public long combinePermutation(){
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        byte[] uuidBytes = new byte[16];

        for (int i = 0; i < 8; i++) {
            uuidBytes[i] = (byte) (mostSigBits >>> (8 * (7 - i)));
            uuidBytes[i + 8] = (byte) (leastSigBits >>> (8 * (7 - i)));
        }

        long result = 0;
        for (byte b : uuidBytes) {
            result = (result << 8) | (b & 0xFF);
        }
        return result;
        //return Math.abs(result);
    }
}
