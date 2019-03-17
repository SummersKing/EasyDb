package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.summer.easydb.DataWrapper;
import org.summer.easydb.util.Util;

import java.lang.reflect.Field;
import java.util.*;

public class Editor<T> extends AbstractEditor<T> {
    private DataTable dataTable;
    private  String src;
    public Editor(DataTable dataTable) {
        super(dataTable);
        this.dataTable=dataTable;
        this.src=dataTable.getSrc();
    }

    @Override
    public boolean putObj(T t) {
        int lastRowNum = dataTable.getSheet().getLastRowNum();
        if(lastRowNum< dataTable.getMaxRowNums()){
            return putObjForce(lastRowNum+1,t);
        }else {
            System.out.println("已经达到最大行数上限" + dataTable.getMaxRowNums());
            return false;
        }
    }

    @Override
    public synchronized  boolean putObjForce(int index,T t) {
        Row row = dataTable.getSheet().createRow(index);
        Map<Integer, Field> fieldMap = Util.getFieldMap(dataTable.getHeadListMap(),dataTable.getT());
        Iterator<Integer> iterator = fieldMap.keySet().iterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            putFieldObJToCell(fieldMap.get(key), row.createCell(key), t);
        }
        return true;
    }

    public boolean putFieldObJToCell(Field field, Cell cell, T t) {
        try {
            field.setAccessible(true);
            Object o = field.get(t);
            field.setAccessible(false);
            if (o != null) {
                cell.setCellValue(o.toString());
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int putObjBatch(List<T> list) {
        int lastRowNum = dataTable.getSheet().getLastRowNum();
        int end=dataTable.getMaxRowNums()>(lastRowNum+list.size())?list.size()+lastRowNum+1:dataTable.getMaxRowNums();
        int index=0;
        for (int i=lastRowNum+1; i <end; i++) {
            dataTable.getSheet().createRow(i);
            if(putObjForce(i,list.get(index++)))
            continue;
        }
        System.out.println(end-lastRowNum);
        return end-lastRowNum;
    }

    @Override
    public boolean postObj(int index, T t) {
        Row row=null;
        if(dataTable.getMaxRowNums()>index){
             row = dataTable.getSheet().getRow(index);
        }
        if (row == null) {
            return false;
        }
        Map<Integer, Field> fieldMap =  Util.getFieldMap(dataTable.getHeadListMap(),dataTable.getT());
        Iterator<Integer> iterator = fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            putFieldObJToCell(fieldMap.get(key), row.getCell(key), t);

        }
        return true;

    }



    @Override
    public String postObjBatch(DataWrapper<T> wrapper) {
        StringBuffer buffer = new StringBuffer();
        Map<Integer, T> map = wrapper.getDataMap();
        Iterator<Integer> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            T t = map.get(next);
            if (postObj(next,t)) {
                buffer.append(next + "、");
                 continue;
            }

        }
        buffer.append("修改成功");
        return new String(buffer);
    }


    @Override
    public boolean del(int index) {
        org.apache.poi.ss.usermodel.Sheet sheet = dataTable.getSheet();

        Row row = sheet.getRow(index);
        if (row != null) {
            sheet.removeRow(row);
            return true;
        }
        return false;
    }
    @Override
    public int delBatch(int[] batch) {
        org.apache.poi.ss.usermodel.Sheet sheet = dataTable.getSheet();
        int size = batch.length;
        for (int i = 0; i < batch.length; i++) {
            Row row = sheet.getRow(batch[i]);
            if (row != null) {
                sheet.removeRow(row);
            } else {
                size--;
            }
        }
        return size;
    }





}
