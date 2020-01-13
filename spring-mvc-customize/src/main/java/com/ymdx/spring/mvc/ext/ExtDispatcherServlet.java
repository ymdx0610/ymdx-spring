package com.ymdx.spring.mvc.ext;

import com.ymdx.spring.mvc.annotation.ExtController;
import com.ymdx.spring.mvc.annotation.ExtRequestMapping;
import com.ymdx.spring.mvc.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ExtDispatcherServlet
 * @Description: 自定义核心控制器
 *
 * 实现步骤：
 *
 * 基于servlet实现
 *
 * 重写servlet的init方法：
 * 1. 扫包，获取packageName下的所有类信息并进行遍历
 * 1.1 判断类上是否存在@ExtController注解，若存在，则
 * 1.2 通过反射实例化出对象，暂存到map集合springMvcBeans中（key为首字母小写的类名称，value为类对象）
 *
 * 2. 遍历springMvcBeans
 * 2.1 判断类上是否存在@ExtRequestMapping注解，若存在，则取出注解的值作为基础路径baseUrl
 *
 * 3. 通过反射获取类对象的所有声明的方法并进行遍历
 * 3.1 判断方法上是否存在@ExtRequestMapping注解，若存在，则取出注解的值methodUrl，拼接前缀baseUrl形成完整的url
 * 3.2 将url与对应的类对象暂存到map集合urlBeans中（key为baseUrl，value为对应的类对象）
 * 3.3 将url与对应的方法名称暂存到map集合urlMethodNameMap中（key为url，value为方法名称）
 *
 * 重写servlet的doGet或者doPos方法（doGet/doPost底层实现是service方法）：
 * 4. 根据用户请求地址url从urlBeans集合中获取对应的类对象obj，若找不到，则返回404
 * 5. 根据用户请求地址url从urlMethodNameMap集合中获取对应的方法名称
 * 6. 通过反射调用方法，并拿到返回结果
 * 7. 将返回结果拼接上前缀prefix、后缀suffix，找到对应的视图
 * 8. 返回响应给用户的结果，浏览器渲染视图
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-13 10:38
 * @Version: 1.0
 **/
public class ExtDispatcherServlet extends HttpServlet {

    private static final String PACKAGE_NAME = "com.ymdx.spring.mvc.web";
    private ConcurrentHashMap<String, Object> springMvcBeans = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> urlMethodNameMap = new ConcurrentHashMap<>();
    private static final String PREFIX = "/";
    private static final String SUFFIX = ".jsp";

    /**
     * 重写servlet的init方法
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        try {
            initSpringMvcBeans();
            handleMapping();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 重写doPost方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = handle(req, resp);
            resolveView(result, req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 视图解析
     * @param result
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void resolveView(Object result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将返回结果拼接上前缀prefix、后缀suffix，找到对应的视图
        String view = PREFIX + result + SUFFIX;
        // 返回响应给用户的结果，浏览器渲染视图
        req.getRequestDispatcher(view).forward(req, resp);
    }

    /**
     * 处理请求
     * @param req
     * @param resp
     * @return
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 根据用户请求地址url从urlBeans集合中获取对应的类对象obj，若找不到，则返回404
        String requestURI = req.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return "";
        }
        Object obj = urlBeans.get(requestURI);
        if(obj == null){
            resp.getWriter().println("request uri not found 404");
        }
        // 根据用户请求地址url从urlMethodNameMap集合中获取对应的方法名称
        String methodName = urlMethodNameMap.get(requestURI);
        // 通过反射调用方法，并拿到返回结果
        Method method = obj.getClass().getMethod(methodName);
        return method.invoke(obj);
    }

    /**
     * 处理映射
     */
    private void handleMapping() {
        // 遍历springMvcBeans
        Iterator<Map.Entry<String, Object>> iterator = springMvcBeans.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            Object obj = next.getValue();
            // 判断类上是否存在@ExtRequestMapping注解，若存在，则取出注解的值作为基础路径baseUrl
            String baseUrl = "";
            Class<?> clazz = obj.getClass();
            ExtRequestMapping classAnnotation = clazz.getAnnotation(ExtRequestMapping.class);
            if(classAnnotation != null){
                baseUrl = classAnnotation.value();
            }
            // 通过反射获取类对象的所有声明的方法，遍历方法
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                // 判断方法上是否存在@ExtRequestMapping注解，若存在，则取出注解的值methodUrl，拼接前缀baseUrl形成完整的url
                ExtRequestMapping methodAnnotation = method.getDeclaredAnnotation(ExtRequestMapping.class);
                if(methodAnnotation != null){
                    String url = baseUrl + methodAnnotation.value();
                    // 将url与对应的类对象暂存到map集合urlBeans中（key为baseUrl，value为对应的类对象）
                    urlBeans.put(url, obj);
                    // 将url与对应的方法名称暂存到map集合urlMethodNameMap中（key为url，value为方法名称）
                    urlMethodNameMap.put(url, method.getName());
                }
            }
        }
    }

    /**
     * 初始化Spring MVC Beans
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initSpringMvcBeans() throws InstantiationException, IllegalAccessException {
        // 扫包，获取packageName下的所有类信息并进行遍历
        List<Class<?>> classes = ClassUtil.getClasses(PACKAGE_NAME);
        for(Class<?> clazz : classes){
            // 判断类上是否存在@ExtController注解
            ExtController annotation = clazz.getAnnotation(ExtController.class);
            if(annotation != null){
                // 通过反射实例化出对象，暂存到map集合springMvcBeans中（key为首字母小写的类名称，value为类对象）
                String beanId = ClassUtil.toLowerCaseForFirstChar(clazz.getSimpleName());
                Object obj = ClassUtil.newInstance(clazz);
                springMvcBeans.put(beanId, obj);
            }
        }
    }

}
