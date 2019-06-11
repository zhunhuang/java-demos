package asm;

import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.MethodVisitor;
import aj.org.objectweb.asm.Opcodes;

/**
 * @author: zhun.huang
 * @create: 2018-06-10 下午8:34
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class ClassGenerateHelper {

    public static ClassWriter createClassWriter(String className, String extendsClass, String[] implementsInterfaces) {
        extendsClass = extendsClass==null? "java/lang/Object":extendsClass;
        /**
         * new ClassWriter(0),表明需要手动计算栈帧大小、本地变量和操作数栈的大小；
         * COMPUTE_MAXS 需要自己计算栈帧大小，但本地变量与操作数已自动计算好；调用 visitMaxs(max_stack, max_locals), 慢10%
         * COMPUTE_FRAMES 表明需要手动计算栈帧大小、本地变量和操作数栈的大小 调用 visitFrame(type, nLocal, local, nStack, stack)   慢20%
         */
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        /**
         * Opcodes.V1_8 指定生成的class版本号为52, 对应class文件的minor_version 和major_version
         * Opcodes.ACC_PUBLIC 指定生成的class的访问标志, 对应class文件的access_flags
         * className, 指定生成的class的类名, 必须是全限定名,如com/test/flight/test/Demo, 对应class文件的this_class
         * 第四个参数是和泛型相关的, 传入null表示不是泛型类, 对应class文件的Signature 属性.
         * 第五个参数是指定该类的父类的全限定名, 对应class文件的super_class字段
         * 第六个参数是String[]类型的, 指定当前生成的类要实现的接口.对应class文件的interfaces字段
         * */
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC
                ,className,null,extendsClass,implementsInterfaces);

        /**
         * Opcodes.ACC_PUBLIC 第一个参数指定该方法的访问标志, 对应method_info中的 access_flags
         * <init> 第二个参数是方法的方法名, 构造函数的方法名为"<init>", 这个参数对应method_info中的name_index, name_index引用常量池中的方法名字符串
         * 第三个参数是方法描述符, 在这里要生成的构造方法无参数,无返回值,所以方法描述符为()v. 这个参数对应method_info中的descriptor_index, 同样引用常量池中的方法描述符字符串.
         * 关于描述符 以一个L开头的描述符，就是类描述符，它后紧跟着类的字符串，然后分号“；”结束, 比如"Ljava/lang/String;"就是表示类型String
         * 第四个参数和泛型相关,传入null表示该方法不是泛型方法.这个参数对应method_info中的Signature属性.
         * 第五个参数String[]指定方法可能抛出的异常,传入null表示无异常抛出, 对应method_info中的Exceptions属性.
         */
        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        /**
         * 如果要生成方法的代码，需要先以visitCode开头，访问结束需要调用visitEnd方法；
         */
        constructor.visitCode();

        /**
         * 调用visitVarInsn方法, 生成aload指令,将第0个变量(也就是this)压入操作数栈.
         */
        constructor.visitVarInsn(Opcodes.ALOAD,0);

        /**
         * 调用父类的构造方法, 使用INVOKESPECIAL指令
         */
        // 调用visitMethodInsn方法, 生成invokespecail指令, 调用父类(也就是Object)的构造方法. 调用类型, 调用的类, 调用方法的方法名, 调用方法的描述符, 是否是接口
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL,
                extendsClass,
                "<init>",
                "()V",false);

        /**
         * 生成return指令, 方法返回
         */
        // 调用visitInsn方法, 生成return指令, 方法返回
        constructor.visitInsn(Opcodes.RETURN);

        /**
         * 指定当前要生成的方法的最大局部变量和最大操作数栈
         */
        // 调用visitMaxs方法, 指定当前要生成的方法的最大局部变量和最大操作数栈,
        // 对应Code属性中的max_stack和max_locals.
        constructor.visitMaxs(2,2);

        /**
         * 结束
         */
        // 调用visitEnd方法, 表示当前要生成的构造方法已经创建完成.
        constructor.visitEnd();
        return cw;
    }
}
