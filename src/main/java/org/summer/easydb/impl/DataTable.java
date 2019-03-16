package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.DataAble;
import org.summer.easydb.EditAble;
import org.summer.easydb.SelectAble;
import org.summer.easydb.annotation.Column;
import org.summer.easydb.DataSourceScanner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public  class DataTable<T> extends AbstractDataSource implements DataAble<T> {

    int sheetIndex;
    int primaryKey;
    Map<Integer, String> headListMap;
    Set<Integer> headListKey;
    org.apache.poi.ss.usermodel.Sheet sheet;
    int beginRowIndex;
    Class<T> t;
    public DataTable(){

    }

    public DataTable(int beginRowIndex, int sheetIndex, Integer primaryKey, String src, Map<Integer, String> headListMap, Class<T> t) {
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




    public Map<Integer, String> getHeadListMap() {
        return headListMap;
    }

    public Set<Integer> getHeadListKey() {
        return headListKey;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public int getBeginRowIndex() {
        return beginRowIndex;
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

    @Override
    public EditAble getEditor() {

        return new Editor(this);
    }

    @Override
    public SelectAble getSelector() {

        return new Selector(this);
    }





}
