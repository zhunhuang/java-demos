package crc;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * description:
 *
 * @author zhunhuang, 2020/12/8
 */
public class CRCUtil {

    public static long CRC32(String data) {
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(data.getBytes(StandardCharsets.UTF_8));
            return crc32.getValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            String callerOrderId = i + "123123131231";
            System.out.println(CRC32(callerOrderId));
            System.out.println("callerOrderId:" + callerOrderId + "," + "payOrderIdx：" + (CRC32(callerOrderId) % 1000));
        }
    }
}
