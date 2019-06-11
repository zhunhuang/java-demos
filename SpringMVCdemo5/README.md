# Spring MVC demo5
和Spring MVC demo4 新增功能
- 新增了mybatis框架

改动点：
- 新增mybatis 和mybatis-spring依赖。mybatis-spring是为了让mybatis更好的接入spring
- 使用xml形式的mybatis配置。
- dao层不写实现，由MapperScannerConfigurer来实现。MapperScannerConfigurer需要知道dao层的接口的位置和sqlSessionFactory实例
- sqlSessionFactory 需要知道mapper.xml文件在什么地方，还需要datasource实例。
- 这里的datasource实例还是使用spring-jdbc 的SimpleDriverDataSource作为连接池支持

### mybatis
教程：http://www.mybatis.org/mybatis-3/zh/configuration.html



