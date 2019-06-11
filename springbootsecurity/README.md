
最简单的spring boot + spring security的代码

引入 1.5.3版本的依赖
spring-boot-start-web
spring-boot-start-security
spring-boot-start-test

编写WebSecurityConfigurerAdapter的继承类，用来配置资源访问安全控制。

``` java
 @Configuration
 public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 //表单登录
                 .formLogin()
                 //允许访问
                 .and().authorizeRequests().antMatchers("/home").permitAll().anyRequest().authenticated()
                 //禁用跨站伪造
                 .and().csrf().disable();
     }
 }
```
