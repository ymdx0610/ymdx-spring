package com.ymdx.spring.aop.proxy.demo1;

/**
 * @ClassName: GamePlayer
 * @Description: 真实游戏玩家对象
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-09-30 10:26
 * @Version: 1.0.1
 **/
public class GamePlayer implements IGamePlayer {

    private String name = "";

    public GamePlayer(String name){
        this.name = name;
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "在打怪！");
    }

    @Override
    public void upGrade() {
        System.out.println(this.name + "成功升了1级！");
    }

}
