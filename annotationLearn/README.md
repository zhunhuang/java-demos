# Annotation learn

## annotation 是什么

> java 注解是一种描述元数据的工具，应用于java中的包，类，方法，构造器，参数，变量的一种特殊修饰符。

## annotation 的作用

注解，简单理解是一种对目标物的注释，但当这个注释作为一种规范，可以被编译器识别，可以被JVM识别时，则可以用来做很多事情。

## 说一说annotation都能干什么吧

`@Override` 注解 标记一个方法，表明这个方法是覆盖了父类方法的，能够帮助程序员或者ide认识到这是一个覆盖方法。

`@Deprecated` 注解 标记方法，属性或者类， 表明这个对象已经废弃了，IDE识别到这个注解时，会将调用处的代码用横线划掉，编译时也会warn代码调用了废弃代码。

`@SuppressWarnings(“deprecation”)` 注解 标记方法等，告诉IDE不要对这些方法进行警告。

`@Resource` 注解 标记一个属性或者方法等，表明这个属性或方法是一种资源，需要被注入。

## 详细点解释？
以`@Document`注解为例,代码如下：
```java
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Documented {
    }
```
这个注解实现了`java.lang.annotation.Annotation`接口，但跟一般的接口实现使用 `implement`关键字不同，这里使用了`@interface`关键字，
这意味着这个接口的实现细节是由编译器实现的。

第一行的`@Documented` 自身描述自身，这个会被javadoc解析，表明这个注解会生成javadoc。默认是不生成javadoc文档，如果没有加这个注解的话。

第二行`@Retention(RetentionPolicy.RUNTIME)` 指定这个注解的策略属性为`RetentionPolicy.RUNTIME`，策略属性的类型在RetentionPolicy中定义了，总共分为三种：
- `RetentionPolicy.SOURCE`， 这个只出现在源码中，在.class文件中就没有了。
- `RetentionPolicy.CLASS`， 这个会出现在编译后的class文件中，但不会被JVM识别。别人引你的jar包时，能看到有这个注解。
- `RetentionPolicy.RUNTIME`， 这个不仅出现在.class文件中，还能够被JVM识别，JVM可以针对这个作出策略

第三行`@Target(ElementType.ANNOTATION_TYPE)` 指定了这个注解的作用域只能作用于`ElementType.ANNOTATION_TYPE`也就是注解上，只能作用于注解的注解叫做元注解，java中一共有4个元注解，包括：
`@Documented`
`@Retention` 
`@Target`
`@Inherited`

刚刚其实已经解释了前面三个元注解，最后解释一下`@Inherited`注解：
> 如果一个类被 一个使用了@Inherited修饰的注解 进行修饰， 则这个类的子类会自动继承这个注解。
inherited表示可被继承的，并不是说被这个元注解修饰的注解是可被继承的，而是说这个注解的使用是可以被继承的。比如在接口A上声明了一个注解X， 如果注解X被@Inherited修饰了
，则所有实现接口A的类都自动包含了注解X的功能。也就是说通过反射能拿到类的注解X 的annotation属性。




  




