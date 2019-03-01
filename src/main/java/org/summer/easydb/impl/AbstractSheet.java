package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.annotation.Column;
import org.summer.easydb.DataSourceScanner;
import org.summer.easydb.DataTable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AbstractSheet<T> extends AbstractWorkBook implements DataTable<T> {

    int sheetIndex;
    int primaryKey;
    Map<Integer, String> headListMap;
    Map<Integer,T> dataCache;
    Set<Integer> headListKey;
    org.apache.poi.ss.usermodel.Sheet sheet;
    int beginRowIndex;
    Class<T> t;

    public AbstractSheet(){

    }

    public AbstractSheet(int beginRowIndex, int sheetIndex, String src,int primaryKey, Map<Integer, String> headListMap, Class<T> t) {
        super(src);
        this.headListMap = headListMap;
        this.headListKey = headListMap.keySet();
        this.beginRowIndex = beginRowIndex;
        this.sheetIndex = sheetIndex;
        this.sheet = workbook.getSheetAt(sheetIndex);
        this.t = t;
        this.primaryKey=primaryKey;
    }

    public  Map<Integer,Field> getFieldMap() {
        Map<Integer, String> map = headListMap;
        Map<Integer,Field> fieldMap=new HashMap<>();
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            try {
                Integer next = iterator.next();
                Field field = t.getDeclaredField(map.get(next));
                fieldMap.put(next,field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return fieldMap;
    }


    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public Map<Integer, String> getHeadListMap() {
        return headListMap;
    }

    public void setHeadListMap(Map<Integer, String> headListMap) {
        this.headListMap = headListMap;
    }

    public Map<Integer, T> getDataCache() {
        return dataCache;
    }

    public void setDataCache(Map<Integer, T> dataCache) {
        this.dataCache = dataCache;
    }

    public Set<Integer> getHeadListKey() {
        return headListKey;
    }

    public void setHeadListKey(Set<Integer> headListKey) {
        this.headListKey = headListKey;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getBeginRowIndex() {
        return beginRowIndex;
    }

    public void setBeginRowIndex(int beginRowIndex) {
        this.beginRowIndex = beginRowIndex;
    }

    public Class<T> getT() {
        return t;
    }

    public void setT(Class<T> t) {
        this.t = t;
    }



    public int getFieldIndexByName(String fieldName) {
        int index = 0;
        try {
            Field field = t.getDeclaredField(fieldName);
            Column c = field.getAnnotation(Column.class);
            index = DataSourceScanner.transfer(c.index());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println("您输入的属性名有误，请检查");
        }
        return index;
    }




}
