package moebiusSolutionsInc_TestCode;

import java.util.Base64;

public class Bonus {

    /*
    The program first decodes the Base64-encoded message into bytes. It then uses the same key, "MOEBIUS",
    to decrypt the message byte-by-byte using the XOR operator.
    Finally, it converts the decrypted bytes back into a string and prints it out.
     */

    public static void main(String[] args) {
        String encodedMessage = "DiArJTs0JzgjJDYgOj0+Y2U7JiBzPiApNCwxczknIGIrOj04PGUzPDAgOSYqLGhfWW1vZWJpdXNtb2ViaXVzbW9" +
                "lYml1c21vZWJpdXNtb2ViaXVzY2hibEN1c21vZWJpe3RqYWViaXVzbWFlYml1c21vZWhucnltb2VicwoPYhB/Yml1c21hT2JpdXNtb38d" +
                "FXoMd29lYhYJe2IQZWJnb31nEBltFn9zbW9/YmYJc3dvZWxue2ljaGtIaXV9amhreGl6D211ZWJpe3xkE2ViaXJpamVlbRV1eW11ZWJue" +
                "31qYWViZGhpInV4b0N1aRITah1zcn13dX9saXVzbWhlaG5yeW1vZWJjdXRjaGpsbnUMEWdqHW57dHdoa2VDdWltYBlic3Vpd3V/eGl1c21vb" +
                "x0Vegxnb2ViaXV+cG8qYnR4c21gbB5pdXNtaE9iaXJ9Y2hlYm5vaXdoZWJpdXNnb2oeaX9zbW9lYmdyfGNoa2JpdXRHb2ViaXVzbW9lYml1c21vZWJpdXljYW9iaXVzbW9lYmlvWQ==";
        byte[] encryptedBytes = Base64.getMimeDecoder().decode(encodedMessage);
        byte[] key = "MOEBIUS".getBytes();
        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] ^ key[i % key.length]);
        }
        String decryptedMessage = new String(decryptedBytes);
        System.out.println(decryptedMessage);
    }


}