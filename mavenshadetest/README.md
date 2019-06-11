## 为什么有这么一个模块
该模块的目的是为了解决多个 elasticsearch java client 版本在一个java工程中共存的问题

因为es java api client在各个版本之间是不兼容的, 而一个java工程又可能存在查询不同版本的es集群的需要, 因此需要提供两个es client 共存的方案.
#### 问题参考:
https://discuss.elastic.co/t/use-two-versions-of-es-client-api-in-single-java-project/57760/1


## 怎么解决
- 使用 maven shade 插件, 将可能出现重复类以及依赖冲突问题的jar包和类进行重命名.
- 不会出现版本兼容问题的依赖, 尽量不要进行shading, 避免shading后的jar包过大


## shading配置参考:
ES官方文档: https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-low-usage-shading.html

shade官方文档: https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html

实例参考: https://softeng.oicr.on.ca/vitalii_slobodianyk/2016/11/25/elasticsearch-shading/