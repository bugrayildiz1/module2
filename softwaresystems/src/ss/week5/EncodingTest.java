package ss.week5;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * A simple class that experiments with the Hex encoding of the Apache Commons Codec library.
 *
 */
public class EncodingTest {
    public static void main(String[] args) throws DecoderException {
        // P-5.17.
        String input = "Hello World";
        String input2 = "Hello Big World";

        System.out.println(Hex.encodeHexString(input.getBytes()));
        System.out.println(Hex.encodeHexString(input2.getBytes()));

        // P-5.18.
        String hexString = "4d6f64756c652032";
        byte[] decodedHex = Hex.decodeHex(hexString.toCharArray());
        String decodedString = new String(decodedHex);
        System.out.println(decodedString);

        // P-5.19.
        byte[] base64 = Base64.encodeBase64("Hello World".getBytes());
        byte[] hex = Hex.decodeHex("010203040506".toCharArray());
        byte[] hexBase64 = Base64.encodeBase64(hex);

        System.out.println(new String(base64));
        System.out.println(new String(hexBase64));

        char[] charBuffer = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'};
        for (int i = 1; i <= 10; i++) {
            String string = new String(charBuffer, 0, i);
            byte[] encoded = Base64.encodeBase64(string.getBytes());

            System.out.println(new String(encoded));
        }

        byte[] decodedBase64 = Base64.decodeBase64("U29mdHdhcmUgU3lzdGVtcw==");
        System.out.println(new String(decodedBase64));
    }
}
