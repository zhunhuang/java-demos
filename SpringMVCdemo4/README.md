# Spring MVC demo4
和Spring MVC demo3 新增功能
- 新增了持久层，这里使用h2内存数据库。

改动点：
- 新引入了h2 jdbc驱动， 引入了spring-jdbc 中的SimpleDriverDataSource作为连接池支持。

- 通过 配置<context:property-placeholder> 支持xml文件中的变量替换。

- 通过 配置<jdbc:initialize-database>支持应用启动前执行一段sql脚本，实现环境初始化。

### h2 数据库
h2 可以用作内存数据库， 同时搭配sql启动脚本，可以实现不用增加额外数据库环境配置即可运行代码。
