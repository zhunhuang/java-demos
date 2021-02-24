package collection.set;

/**
 * description:
 *
 * @author zhunhuang, 2020/10/28
 */
public class EnumSetTest {


    public enum Status {

        /**
         * 商家是否清分完成
         */
        MERCHANT_PROCEED,
        /**
         * 代理商是否清分完成
         */
        AGENT_PROCEED,
        /**
         * 服务商是否清分完成
         */
        PROVIDER_PROCEED,
        /**
         * 通道是否清分完成
         */
        CHANNEL_PROCEED;
    }
}
