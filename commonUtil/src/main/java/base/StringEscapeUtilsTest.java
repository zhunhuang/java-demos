package base;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * description:
 * StringEscapeUtils类可以对html js xml  sql 等代码进行转义来防止SQL注入及XSS注入
 * @author zhunhuang, 2020/4/3
 */
public class StringEscapeUtilsTest {

    @Test
    public void testEscape(){
        String str = StringEscapeUtils.escapeHtml("http://wwww.baidu.com/黄准?是我");

        List<NameValuePair> parse = URLEncodedUtils.parse("http://wwww.baidu.com/黄准?name=huangzhun&age=24", StandardCharsets.UTF_8);
        System.out.println(parse);
        System.out.println(str);
    }


}
