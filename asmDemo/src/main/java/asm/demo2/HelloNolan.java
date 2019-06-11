package asm.demo2;


import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.MethodVisitor;
import aj.org.objectweb.asm.Opcodes;
import asm.ClassGenerateHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: zhun.huang
 * @create: 2018-06-10 下午2:40
 * @email: nolan.zhun@gmail.com
 * @description: demo 2
 */
public class HelloNolan extends ClassLoader{

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /**
         * new ClassWriter(0),表明需要手动计算栈帧大小、本地变量和操作数栈的大小；
         * COMPUTE_MAXS 需要自己计算栈帧大小，但本地变量与操作数已自动计算好；调用 visitMaxs(max_stack, max_locals), 慢10%
         * COMPUTE_FRAMES 表明需要手动计算栈帧大小、本地变量和操作数栈的大小 调用 visitFrame(type, nLocal, local, nStack, stack)   慢20%
         */
        ClassWriter cw = ClassGenerateHelper.createClassWriter(
                "Hello","java/lang/Object"
                ,null
        );

        MethodVisitor sayHello = cw.visitMethod(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "sayHello",
                "(Ljava/lang/String;)V",
                null,
                null);

        sayHello.visitCode();
        /**
         * Opcode, owner, name, FieldDescriptors
         * 将System.out入栈
         */
        sayHello.visitFieldInsn(Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");
        /**
         * 将入参的第0个参数入栈(如果是非静态方法, 第0个参数就是this, 这里静态方法就是方法的第一个入参)
         */
        sayHello.visitVarInsn(Opcodes.ALOAD,0);
        /**
         * 执行某个方法, 进入执行该方法栈
         * Opcode, owner, name, descriptor, 是否是接口方法调用
         */
        sayHello.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "print",
                "(Ljava/lang/String;)V",
                false);
        /**
         * 返回
         */
        sayHello.visitInsn(Opcodes.RETURN);
        /**
         * 定义最大操作数栈, 局部变量
         */
        sayHello.visitMaxs(2,3);
        /**
         * 结束
         */
        sayHello.visitEnd();

        byte[] code = cw.toByteArray();
        FileOutputStream fout = new FileOutputStream("Hello.class");

        fout.write(code);
        fout.close();

        HelloNolan loader = new HelloNolan();
        /**
         * classLoader 加载class文件
         * 参数: 文件名, 二进制数组, 起始位置, 结束位置
         */
        Class<?> hello = loader.defineClass("Hello",
                code,
                0,
                code.length);
        hello.getMethod("sayHello",String.class).invoke(
                null,"hello, nolan!");
    }
}
