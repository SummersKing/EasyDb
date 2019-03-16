package org.summer.easydb.util;

import org.summer.easydb.impl.DataTable;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @ClassName Util
 * Created by Summer on 21:312019/3/16
 * @Description
 * @LastModified
 **/
public class Util {

    public static Object deepClone(Object obj){

        Object copy = null;
        ByteArrayOutputStream baos=null;
        try {
            baos = new ByteArrayOutputStream();
            new ObjectOutputStream(baos).writeObject(obj);
            copy =  new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(baos!=null)
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return copy;
    }
/*
 * @Name transfer
 * @Desc 将列的序列名称转化为列索引
 * @Date 23:32 2019/3/16
 **/
    public static int transferA21(String s){

        char[] chars = s.toUpperCase().toCharArray();
        double index=0;
        int j=0;
        for(int i=chars.length-1;i>-1;i--){
            int now=(int)chars[i]-65;
            index=index+(now+1)*Math.pow(26,j++)-1;
        }
        return (int)index;

    }


}
