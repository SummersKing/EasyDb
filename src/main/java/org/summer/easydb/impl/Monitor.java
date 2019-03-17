package org.summer.easydb.impl;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * @ClassName Monitor
 * Created by Summer on 23:572019/3/16
 * @Description
 * @LastModified
 **/
public class Monitor implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Integer nullRowNums=((Set)arg).size();
        if(nullRowNums>5200)
        {
            System.out.println("数据库数量太多主义整理空行太多需要整理");
        }


    }
}
