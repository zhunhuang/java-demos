# Spring MVC Simple Demo

- 无持久层支持
- 无json2MessageConverter支持
- 无Spring application Context

主要步骤如下：
- 基于maven-archetype-webapp初始化工程
- maven管理依赖，只需要引入spring webmvc依赖
- web.xml配置，添加encoding filter, DispatchServlet配置
- 配置spring MVC 注解支持
- 配置spring MVC 包扫描
- controller层代码编写
- 配置tomcat server启动

三个文件：
1. web.xml， java web 代码入口文件，servlet规范；这里配置了Spring 的DispatchServlet 代理所有的请求。
2. spring-mvc.xml 是Spring MVC 的配置文件， 配置了包扫描机制来自动扫描Spring 的bean。
3. HelloController 典型的Spring MVC的Controller 写法，通过Spring MVC提供的注解来实现一个Servlet。
