引入  oauth2依赖， 是spring cloud家族管理的，因此还需要指定
jar包版本。
引入  redis， 作为token存储。


## 本demo的示例是作为oauth2服务的提供方!

认证过程：

1. 用户被第三方客户端，重定向到申请授权页面：
http://127.0.0.1:8080/oauth/authorize?response_type=code&client_id=lvhaibao&redirect_uri=http://baidu.com&state=test&scope=app

2. 如果用户自身没有登录，则没办法授权，因此，当用户没有登录时，会重定向到登录界面：
http:127.0.0.1/8080:/login

3. 用户完成登录后，重定向回来申请授权页面，用户选择允许授权操作,此时获取到code。


4.通过code获取到accesstoken：
http://127.0.0.1:8080/oauth/token?grant_type=authorization_code&client_id=lvhaibao&client_secret=123456&redirect_uri=http://baidu.com&code=tjdOVH&state=test&scope=app

5. 带着accesstoken访问用户信息：
