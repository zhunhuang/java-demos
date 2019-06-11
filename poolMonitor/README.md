
##目标



使用方：
-

1.  配置一个启动类，调用启动类的start方法即可实现scp系统的start。
2. 调用启动类的stop方法，即可实现scp系统的gracefulStop。


内部
-
register
-
用来注册要监控的资源。怎么获取到要监控的资源，不同的框架，获取方式是不一样的。
提供手动注册功能。

ResouceHolder持有所有要监控的资源引用，并且分类保存，实质就是一个map。

scanner
-

数据生产者。扫描对应的资源，挖掘和加工资源池的数据，并存储到 ResourceDataRecorder。

monitor
-

数据生产者，通过拦截器过滤器等配置，记录影响池资源的对应的数据，并存储到ResourceDataRecorder.TaskData节点下去。

alerter
-

