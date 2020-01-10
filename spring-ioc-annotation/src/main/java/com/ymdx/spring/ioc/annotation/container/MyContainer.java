package com.ymdx.spring.ioc.annotation.container;

import com.ymdx.spring.ioc.annotation.ext.ExtService;
import com.ymdx.spring.ioc.annotation.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MyContainer
 * @Description: 自定义容器
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 20:05
 * @Version: 1.0
 **/
public class MyContainer {

    /**
     * 需要扫描的包
     */
    private String packageName = null;

    /**
     * 定义容器，暂存拥有@ExtService注解的bean
     */
    private static ConcurrentHashMap<String, Object> beans = null;

    public MyContainer(String packageName){
        this.packageName = packageName;
        beans = new ConcurrentHashMap<>();
        initBeans();
    }

    /**
     * @ExtService 实现思路
     * 1. 扫描包，获取所有类列表
     * 2. 遍历类列表，判断类上是否有@ExtService注解
     * 3. 若类信息上存在@ExtService注解，通过反射技术实例化bean，并暂存到自定义容器（map：key为beanId，value为bean实例）中
     *
     * @ExtResource 实现思路
     * 1. 遍历被依赖注入的目标对象的属性
     * 2. 判断属性上是否有@ExtResource注解，若有，获取到该属性名称
     * 3. 根据属性名称，通过反射技术实例化出注入的对象
     * 4. 将注入的对象赋值给拥有@ExtResource注解的对应属性
     *
     */
    public Object getBean(String beanId){
        if(StringUtils.isEmpty(beanId)){
            throw new RuntimeException("beanId can't null");
        }
        return beans.get(beanId);
    }

    private void initBeans() {
        if(StringUtils.isEmpty(packageName)){
            throw new RuntimeException("packageName is null");
        }
        // 扫描包，获取所有类列表
        List<Class<?>> classes = ReflectionUtil.getClasses(packageName);
        // 遍历类信息列表，判断类上是否有@ExtService注解
        for (Class<?> clazz : classes){
            ExtService extService = clazz.getAnnotation(ExtService.class);
            if(null != extService){
                initBean(clazz);
            }
        }

    }

    /**
     * 若类信息上存在@ExtService注解，通过反射技术实例化bean，并暂存到自定义容器（map：key为beanId，value为bean实例）中
     * @param clazz
     */
    private void initBean(Class<?> clazz){
        String clazzName = clazz.getName();
        // 将clazzName首字母转化成小写
        String newClazzName = firstCharToLower(clazzName);
        Object obj = null;
        try {
            obj = clazz.newInstance();
        }catch (Exception e){
            throw new RuntimeException("");
        }
        beans.put(newClazzName, obj);
    }

    private String firstCharToLower(String clazzName) {
        char firstChar = clazzName.charAt(0);
        String tempStr = String.valueOf(firstChar).toLowerCase();
        return tempStr + clazzName.substring(1);
    }


}
