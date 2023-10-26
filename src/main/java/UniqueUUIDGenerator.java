import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueUUIDGenerator {

    public long generate(int method){
        return (method == 0) ? getLeastSignificantBits() :
                (method == 1) ? getMostSignificantBits() :
                (method == 2) ? gethashCode() :
                (method == 3) ? getByteBuffer() : 0;
    }

    public long getLeastSignificantBits(){
        long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
        return Math.abs(leastSignificantBits);
    }

    public long getMostSignificantBits(){
        long mostSignificantBits = UUID.randomUUID().getMostSignificantBits();
        return Math.abs(mostSignificantBits);
    }

    public long gethashCode(){
        long hashCode = UUID.randomUUID().toString().hashCode();
        return Math.abs(hashCode);
    }

    public long getByteBuffer(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        bb.rewind(); // Kembalikan posisi buffer ke awal
        return Math.abs(bb.getLong());
    }
}
