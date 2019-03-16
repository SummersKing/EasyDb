package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.DataAble;
import org.summer.easydb.EditAble;
import org.summer.easydb.SelectAble;
import org.summer.easydb.annotation.Column;
import org.summer.easydb.util.Util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public  class  DataTable<T> extends DataSource implements DataAble<T> , Serializable {

    private int sheetIndex;
    private Map<Integer, String> headListMap;
    private Set<Integer> headListKey;
    private Sheet sheet;
    private int beginRowIndex;
    private Class<T> t;
    public DataTable(){

    }

    public DataTable(int beginRowIndex, int sheetIndex, String src, Map<Integer, String> headListMap, Class<T> t) {
        super(src);
        this.headListMap = headListMap;
        this.headListKey = headListMap.keySet();
        this.beginRowIndex = beginRowIndex;
        this.sheetIndex = sheetIndex;
        this.sheet = getWorkbook().getSheetAt(sheetIndex);
        this.t = t;

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

    @Override
    public EditAble getEditor() {

        return new Editor(this);
    }

    @Override
    public SelectAble getSelector() {

        return new Selector(this);
    }





}
