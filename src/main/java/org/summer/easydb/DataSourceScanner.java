package org.summer.easydb;


import org.summer.easydb.annotation.Column;
import org.summer.easydb.annotation.SheetInfo;
import org.summer.easydb.impl.Sheet;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DataSourceScanner {

    public static DataTable connectSheet(Object obj , DataSourceConfig data){
        Class<?> clazz =  obj.getClass();
        SheetInfo sheetInfo = clazz.getAnnotation(SheetInfo.class);
        Integer sheetIndex = sheetInfo.sheetIndex();
        if(sheetIndex==null){
            throw  new NullPointerException(clazz.getTypeName()+"需要配置sheetIndex");
        }
        Field[] fields = clazz.getDeclaredFields();
        Map map=new HashMap<Integer,String>();
        for(Field field:fields){
            Column hl = field.getAnnotation(Column.class);
            map.put(transfer(hl.index()),field.getName());
        }
        return new Sheet(sheetInfo.beginRowIndex(),sheetIndex,sheetIndex,data.DataSource(),map,clazz);
    }

    public static int transfer(String s){

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
