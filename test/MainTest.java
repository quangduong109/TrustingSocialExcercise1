import java.util.BitSet;

public class MainTest {
    public static void main(String[] args) {
        long a = 9976941955L;
        BitSet bitSet = BitSet.valueOf(new long[]{a});
        long b = bitSet.toLongArray()[0];
        System.out.println(b);

    }
}
