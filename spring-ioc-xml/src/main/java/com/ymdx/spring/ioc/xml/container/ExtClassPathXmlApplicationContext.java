package com.ymdx.spring.ioc.xml.container;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: ExtClassPathXmlApplicationContext
 * @Description: 自定义容器，用于创建Bean
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 15:03
 * @Version: 1.0
 **/
public class ExtClassPathXmlApplicationContext {

    /**
     * 要加载的xml名称
     */
    private String xmlName;

    public ExtClassPathXmlApplicationContext(String xmlName){
        this.xmlName = xmlName;
    }

    /**
     * 实现步骤：
     * 1.加载xml，返回所有子节点信息
     * 2.根据传入的beanId获取xml中id与beanId相等的节点信息，返回该节点的class属性信息
     * 3.通过反射技术创建对应实例
     *
     * @return
     */
    public Object getBean(String beanId) throws Exception {
        List<Element> elements = loadXml();

        String className = getClassName(elements, beanId);
        if(StringUtils.isEmpty(className)){
            throw new RuntimeException(xmlName + "中未找到id为" + beanId + "的类信息！");
        }

        return newInstance(className);
    }

    private Object newInstance(String className) throws Exception {
        Class<?> cls = Class.forName(className);
        return cls.newInstance();
    }


    public String getClassName(List<Element> elements, String beanId) {
        for(Element element : elements){
            String xmlBeanId = element.attributeValue("id");
            if(StringUtils.isEmpty(xmlBeanId)){
                throw new RuntimeException(xmlName + "中未找到id为" + beanId + "的属性信息！");
            }
            if (!xmlBeanId.equals(beanId)){
                continue;
            }
            return element.attributeValue("class");
        }
        return null;
    }

    public List<Element> loadXml() throws DocumentException {
        if(StringUtils.isEmpty(xmlName)){
            throw new RuntimeException("要加载的xml名称为空！");
        }
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(xmlName);
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        if(CollectionUtils.isEmpty(elements)){
            throw new RuntimeException(xmlName + "没有配置子节点信息！");
        }
        return elements;
    }

}
