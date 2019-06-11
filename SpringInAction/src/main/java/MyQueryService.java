/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
public class MyQueryService {

    private QueryNameService queryNameService;

    public MyQueryService() {
    }

    public MyQueryService(QueryNameService queryNameService) {
        this.queryNameService = queryNameService;
    }

    public String saHello (String id) {
        return "hello," + queryNameService.getName(id);
    }
}
