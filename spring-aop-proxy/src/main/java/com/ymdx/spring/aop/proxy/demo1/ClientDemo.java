package com.ymdx.spring.aop.proxy.demo1;

/**
 * @ClassName: ClientDemo
 * @Description: 测试应用
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-09-30 10:33
 * @Version: 1.0.1
 **/
public class ClientDemo {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("义码当仙");
        IGamePlayer proxy = new GamePlayerProxy(player);

        proxy.killBoss();
        proxy.upGrade();
    }

}
