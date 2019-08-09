jdk1.7 新增了一个字节码命令 invokedynamic 以实现方法的动态调用.

如何实现指鹿为马? 背景:
```java
class Horse {
	public void race(){
		System.out.println("马跑步");
	}
}

class Deer {
	public void race(){
		System.out.println("鹿跑步");
	}
}
```
在没有这个命令之前, 我们调用方法, 必须指定类或者是接口.
因此 ,除非Horse和Deer这两个类都实现同样的接口, 或者有继承关系, 否则无法同时调用这两个类.

而使用invokedynamic则能够实现.