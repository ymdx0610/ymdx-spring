package com.ymdx.spring.aop.transaction.annotation;

import java.lang.reflect.Method;

/**
 * @ClassName: MyAnnotationDemo
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-08 22:14
 * @Version: 1.0
 **/
public class MyAnnotationDemo {

    @MyAnnotation // (name = "test001", age = 1)
    public void test(){

    }

    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("com.ymdx.spring.aop.transaction.annotation.MyAnnotationDemo");
            Method[] methods = cls.getDeclaredMethods();
            for(Method method : methods){
                MyAnnotation myAnnotation = method.getDeclaredAnnotation(MyAnnotation.class);
                if(null == myAnnotation)
                    continue;
                String name = myAnnotation.name();
                int age = myAnnotation.age();
                System.out.printf("name = " + name + "; age = " + age);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
