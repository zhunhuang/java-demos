package asm.demo1;

import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.MethodVisitor;
import aj.org.objectweb.asm.Opcodes;
import asm.ClassGenerateHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: zhun.huang
 * @create: 2018-06-10 上午11:19
 * @email: nolan.zhun@gmail.com
 * @description: asm 运行之后，会在编译期间生成一个类：Example.class
 */
public class HelloWord extends ClassLoader {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassWriter classWriter = ClassGenerateHelper.createClassWriter(
                "Example","java/lang/Object"
                ,null
        );
        MethodVisitor main = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);
        main.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System",
                "out","Ljava/io/PrintStream;");
        main.visitLdcInsn("hello world");
        main.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",false);
        main.visitInsn(Opcodes.RETURN);
        main.visitMaxs(2,2);
        main.visitEnd();

        byte[] code = classWriter.toByteArray();
        FileOutputStream fout = new FileOutputStream("./asmDemo/com/nolan/learn/asm/Example.class");
        fout.write(code);
        fout.close();

        HelloWord loader = new HelloWord();
        Class<?> example = loader.defineClass("Example", code, 0, code.length);
        example.getMethods()[0].invoke(null,new Object[]{null});
    }
}
