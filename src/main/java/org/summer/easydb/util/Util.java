package org.summer.easydb.util;

import org.summer.easydb.annotation.Column;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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



    public static int getFieldIndexByName(String fieldName,Class clazz) {
        int index = 0;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Column c = field.getAnnotation(Column.class);
            if(c==null){
                throw new IllegalArgumentException("fieldName is  has no such annotation");
            }
            index = Util.transferA21(c.index());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("fieldName is wrong,please check it");
        }
        return index;
    }

    public  static Map<Integer,Field> getFieldMap(Map<Integer,String> map ,Class clazz) {
        Map<Integer,Field> fieldMap=new HashMap<>();
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            try {
                Integer next = iterator.next();
                Field field = clazz.getDeclaredField(map.get(next));
                fieldMap.put(next,field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return fieldMap;
    }



}
