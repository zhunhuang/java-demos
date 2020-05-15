package http;

import com.google.common.base.Joiner;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Map;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
    private static HttpClient fast_client = new HttpClient(new MultiThreadedHttpConnectionManager());

    static {
        client.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
        client.getHttpConnectionManager().getParams().setSoTimeout(15000);
        client.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler() {
            @Override
            public boolean retryMethod(HttpMethod method, IOException exception, int executionCount) {
                return executionCount < 2;
            }
        });
        fast_client.getHttpConnectionManager().getParams().setConnectionTimeout(500);
        fast_client.getHttpConnectionManager().getParams().setSoTimeout(2000);
    }

    public static String get(String url, Map<String, String> headers) {
        return get(url,null,headers);
    }

    public static String get(String url, Cookie[] cookies, Map<String, String> headers) {
        logger.info("url:{}", url);
        long startTime = System.currentTimeMillis();
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        method.setFollowRedirects(true);
        try {
            String cookieString = converCookiesToString(cookies);
            if (StringUtils.isNotBlank(cookieString)) {
                method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
                method.setRequestHeader("Cookie", cookieString);
            }
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    method.setRequestHeader(entry.getKey(), entry.getValue());
                }
            }

            int code = client.executeMethod(method);
            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1) {
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                } else {
                    return method.getResponseBodyAsString();
                }
            } else {
                logger.warn("get url:{} return {}", url, code);
            }
        } catch (Exception e) {
            logger.error("push fail: url={}", url, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    private static String converCookiesToString(Cookie[] cookies) {
        StringBuilder buff = new StringBuilder();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                buff.append(";");
                buff.append(cookie.getName());
                buff.append("=");
                buff.append(cookie.getValue());
            }
        }
        if (buff.length() > 0) {
            buff.delete(0, 1);
        }
        return buff.toString();
    }

    public static String postJson(String url, String body, ClientType clientType) {
        PostMethod method = new PostMethod(url);
        try {
            method.setRequestEntity(new StringRequestEntity(body, "application/json", "UTF-8"));

            int code = 0;
            switch (clientType) {
                case FAST:
                    code = fast_client.executeMethod(method);
                    break;
                case NORMAL:
                    code = client.executeMethod(method);
                    break;
                default:
                    logger.error("error ClientType:" + clientType.name());
                    return null;
            }
            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1) {
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                } else {
                    return method.getResponseBodyAsString();
                }
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body + ", ClientType=" + clientType.name(), e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    /**
     * HTTP客户端
     *
     * @param url        url
     * @param body       post请求的body
     * @param clientType
     * @param params     post的查询请求
     * @return
     */
    public static String postJson(String url, String body, ClientType clientType, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder(url);
            stringBuilder.append("?");
            String queryParam = Joiner.on("&").withKeyValueSeparator("=").join(params);
            url = stringBuilder.append(queryParam).toString();
        }
        return postJson(url, body, clientType);

    }

    public static String post(String url, Map<String, String> params, String body, ClientType clientType, Cookie[] cookies) {
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            String cookieString = converCookiesToString(cookies);
            if (StringUtils.isNotBlank(cookieString)) {
                method.setRequestHeader("Cookie", cookieString);
            }
            method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "UTF-8"));
            if (params != null) {
                for (String name : params.keySet()) {
                    method.addParameter(name, params.get(name));
                }
            }
            int code = 0;

            switch (clientType) {
                case FAST:
                    code = fast_client.executeMethod(method);
                    break;
                case NORMAL:
                    code = client.executeMethod(method);
                    break;
                default:
                    logger.error("error ClientType:" + clientType.name());
                    return null;
            }

            if (code == 200) {
                long contentLength = method.getResponseContentLength();
                if (contentLength == -1) {
                    return method.getResponseBodyAsString(Integer.MAX_VALUE);
                } else {
                    return method.getResponseBodyAsString();
                }
            } else {
                logger.error("http code={}", code);
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body + ", params=" + params + ", ClientType=" + clientType.name(), e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String post(String url, String body) {
        return post(url, null, body, ClientType.NORMAL, null);
    }

    public static String post(String url, String body, Cookie[] cookies) {
        return post(url, null, body, ClientType.NORMAL, cookies);
    }

    public static String postParamMap(String url, Map<String, String> params) {
        return post(url, params, "", ClientType.NORMAL, null);
    }

    public static String post(String url, String body, Map<String, String> params) {
        return post(url, params, body, ClientType.NORMAL, null);
    }

    public enum ClientType {
        FAST, NORMAL
    }

}
