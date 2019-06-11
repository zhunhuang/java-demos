参考:http://www.importnew.com/20612.html

首先，权限管理离不开登陆验证的，所以登陆验证拦截器`AuthenticationProcessingFilter`要讲；
还有就是对访问的资源管理吧，所以资源管理拦截器`AbstractSecurityInterceptor`要讲；
但拦截器里面的实现需要一些组件来实现，所以就有了`AuthenticationManager`、`accessDecisionManager`等组件来支撑。

现在先大概过一遍整个流程，用户登陆，会被`AuthenticationProcessingFilter`拦截，
调用`AuthenticationManager`的实现，而且AuthenticationManager会调用`ProviderManager`来获取用户验证信息（不同的Provider调用的服务不同，因为这些信息可以是在数据库上，可以是在LDAP服务器上，可以是xml配置文件上等），
如果验证通过后会将用户的权限信息封装一个User放到spring的全局缓存`SecurityContextHolder`中，以备后面访问资源时使用。

访问资源（即授权管理），访问url时，会通过`AbstractSecurityInterceptor`拦截器拦截，其中会调用`FilterInvocationSecurityMetadataSource`的方法来获取被拦截url所需的全部权限，
再调用授权管理器`AccessDecisionManager`，这个授权管理器会通过spring的全局缓存`SecurityContextHolder`获取用户的权限信息，还会获取被拦截的url和被拦截url所需的全部权限，
然后根据所配的策略（有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。
