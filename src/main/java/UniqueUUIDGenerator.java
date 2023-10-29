import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueUUIDGenerator {

    public long generate(int method){
        return (method == 0) ? getLeastSignificantBits() :
                (method == 1) ? getMostSignificantBits() :
                (method == 2) ? gethashCode() :
                (method == 3) ? combineByteBuffer() : 0;
    }

    public long getLeastSignificantBits(){
        long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
        return Math.abs(leastSignificantBits);
    }

    public long getMostSignificantBits(){
        long mostSignificantBits = UUID.randomUUID().getMostSignificantBits();
        return mostSignificantBits;
        //return Math.abs(mostSignificantBits);
    }

    public long gethashCode(){
        long hashCode = UUID.randomUUID().toString().hashCode();
        return Math.abs(hashCode);
    }

    public long combineByteBuffer(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        bb.rewind(); // Kembalikan posisi buffer ke awal
        return bb.getLong();
        //return Math.abs(bb.getLong());
    }

    public long getByteBufferWrap(){
        UUID originalUUID = UUID.randomUUID();

        // Mengonversi UUID ke byte array
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(originalUUID.getMostSignificantBits());
        buffer.putLong(originalUUID.getLeastSignificantBits());
        byte[] uuidBytes = buffer.array();

        // Mengembalikan UUID dari byte array
        buffer = ByteBuffer.wrap(uuidBytes);
        long mostSigBits = buffer.getLong();
        long leastSigBits = buffer.getLong();
        UUID reconstructedUUID = new UUID(mostSigBits, leastSigBits);
        return reconstructedUUID.getMostSignificantBits();
        //return Math.abs(reconstructedUUID.getMostSignificantBits());
    }

    public long combineBitwise(){
        UUID uniqueUUID = UUID.randomUUID();
        return  ((long) (uniqueUUID.getMostSignificantBits()) << 32) | (uniqueUUID.getLeastSignificantBits() & 0xFFFFFFFFL);
    }

    public long combineDirect(){
        UUID uniqueUUID = UUID.randomUUID();
        long mostSignificantBits = uniqueUUID.getMostSignificantBits();
        long leastSignificantBits = uniqueUUID.getLeastSignificantBits();
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
    }
}
