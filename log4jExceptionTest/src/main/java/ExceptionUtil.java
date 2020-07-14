import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/1
 */
public class ExceptionUtil {

    /**
     * 过滤反射相关类，JVM反射优化后 log4j输出异常栈会因反复加载这些类block线程，尤其GeneratedMethodAccessor
     *
     * @param e
     * @return
     */
    public static Throwable filterReflectTrace(Throwable e) {
        Throwable cause = e;

        while (cause != null) {
            StackTraceElement[] traces = cause.getStackTrace();
            List<StackTraceElement> list = new ArrayList<>();
            for (StackTraceElement element : traces) {
                String className = element.getClassName();
                if (className.contains("GeneratedMethodAccessor") || className.contains("DelegatingMethodAccessorImpl")
                        || className.contains("NativeMethodAccessorImpl")) {
                    continue;
                }
                list.add(element);
            }
            StackTraceElement[] newTraces = new StackTraceElement[list.size()];
            cause.setStackTrace(list.toArray(newTraces));

            cause = cause.getCause();
        }

        return e;
    }
}
