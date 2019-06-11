package javassist;

import javassist.bytecode.AccessFlag;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * description:
 *
 * @author zhun.huang 2019-02-24
 */
public class demo {
    public static void main(String[] args) {
        DynGenerateClass();
    }

    public static void DynGenerateClass(){
        ClassPool pool=ClassPool.getDefault();
        //创建类
        CtClass ct=pool.makeClass("com.nolan.learn.javassist.GenerateClass");
        //让类实现Cloneable接口
        ct.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});
        try{

            // 1. 添加字段
            //获得一个类型为int，名称为id的字段
            CtField f=new CtField(CtClass.intType,"id",ct);
            //将字段设置为public
            f.setModifiers(AccessFlag.PUBLIC);
            //将字段设置到类上
            ct.addField(f);


            //2. 添加构造函数
            CtConstructor constructor=CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}",ct);
            ct.addConstructor(constructor);

            //3. 添加方法
            CtMethod helloM=CtNewMethod.make("public void hello(String des){ System.out.println(des);}",ct);
            ct.addMethod(helloM);

            //4. 将生成的.class文件保存到磁盘
            ct.writeFile("./asmDemo");

            //下面的代码为验证代码
            Field[]fields=ct.toClass().getFields();
            System.out.println("属性名称："+fields[0].getName()+"  属性类型："+fields[0].getType());
        }catch(CannotCompileException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
