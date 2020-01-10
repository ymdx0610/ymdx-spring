package com.ymdx.spring.ioc.xml.demo01;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: Dom4jParseXmlDemo
 * @Description: 使用dom4j方式解析xml示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 14:13
 * @Version: 1.0
 **/
public class Dom4jParseXmlDemo {

    public static void main(String[] args) throws DocumentException {
        Dom4jParseXmlDemo dom4jParseXmlDemo = new Dom4jParseXmlDemo();
        dom4jParseXmlDemo.parseXml();
    }

    public void parseXml() throws DocumentException {
        SAXReader reader = new SAXReader();
        InputStream is = loadXml("person.xml");
        Document document = reader.read(is);
        // 获取根节点
        Element rootElement = document.getRootElement();
        getNodes(rootElement);
    }

    private static void getNodes(Element element) {
        System.out.println("当前节点名称：" + element.getName());
        String textTrim = element.getTextTrim();
        if (StringUtils.isNotEmpty(textTrim)) {
            System.out.println("当前节点值：" + textTrim);
        }
        // 获取属性信息
        List<Attribute> attributes = element.attributes();
        for(Attribute attr : attributes){
            String name = attr.getName();
            String text = attr.getText();
            System.out.println("属性信息：" + name + "=" + text);
        }
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            getNodes(next);
        }
    }

    public InputStream loadXml(String xmlPath){
        return this.getClass().getClassLoader().getResourceAsStream(xmlPath);
    }

}
