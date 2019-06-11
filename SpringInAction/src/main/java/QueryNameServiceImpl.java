import org.springframework.stereotype.Service;

import java.io.PrintStream;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
@Service
public class QueryNameServiceImpl implements QueryNameService{

    private PrintStream printStream;

    public QueryNameServiceImpl() {
        this(System.out);
    }

    public QueryNameServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public String getName(String id) {
        printStream.println("hello,"+id);
        return "hello,"+id;
    }
}
