package monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * description:
 *
 * @author zhunhuang, 2021/2/3
 */
public class DataSourceHealthCheck implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceHealthCheck.class);

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws SQLException {
        checkDataSource();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected void checkDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
        LOGGER.info("数据源数量：{}", dataSourceMap.size());
        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            try (Connection connection = entry.getValue().getConnection()) {
                connection.isClosed();
            } catch (SQLException e) {
                LOGGER.error("get connect fail from {}", entry.getKey(), e);
                throw e;
            }
        }
    }
}
