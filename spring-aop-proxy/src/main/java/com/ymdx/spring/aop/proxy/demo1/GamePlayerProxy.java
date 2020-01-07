package com.ymdx.spring.aop.proxy.demo1;

import java.util.Date;

/**
 * @ClassName: GamePlayerProxy
 * @Description: 游戏玩家代理对象
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-09-30 10:29
 * @Version: 1.0.1
 **/
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer player = null;

    public GamePlayerProxy(IGamePlayer player){
        this.player = player;
    }

    // 记录打怪的时间
    private void log(){
        System.out.println("打怪时间 " + new Date().toString());
    }


    @Override
    public void killBoss() {
        this.log();
        player.killBoss();
    }

    @Override
    public void upGrade() {
        player.upGrade();
        this.count();
    }

    // 计算升级所用时间
    private void count() {
        System.out.println("升级1级耗费50小时");
    }

}
