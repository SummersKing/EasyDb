package org.summer.easydb;


import java.util.HashMap;
import java.util.Map;

public class DataWrapper<T> {
    private T t;
    private Integer fieldIndex;
    private Map<Integer,T> dataMap;
    public void add(Integer key, T t){
        this.dataMap.put(key,t);
    }
    public DataWrapper(Integer fieldIndex){
        this.fieldIndex=fieldIndex;

        dataMap=new HashMap<>();
    }
    public DataWrapper(){

        dataMap=new HashMap<>();
    }



    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Integer getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(Integer fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public Map<Integer, T> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Integer, T> dataMap) {
        this.dataMap = dataMap;
    }
}
