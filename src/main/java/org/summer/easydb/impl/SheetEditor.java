package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.summer.easydb.Constant;
import org.summer.easydb.DataWrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

public class SheetEditor<T> extends AbstractEditor<T> {

    SheetEditor(Sheet sheet) {
        super(sheet);
    }

    @Override
    public boolean putObj(T t) {
        int lastRowNum = dataTable.getSheet().getLastRowNum();
        if(lastRowNum< Constant.MAX_XLS_ROWS_NUMBER){
            Row row = dataTable.getSheet().createRow(lastRowNum+1);
            Map<Integer, Field> fieldMap = dataTable.getFieldMap();
            Iterator<Integer> iterator = fieldMap.keySet().iterator();

            while (iterator.hasNext()) {
                Integer key = iterator.next();
                putFieldObJToCell(fieldMap.get(key), row.createCell(key), t);
            }
            return true;
        }
        System.out.println("已经达到最大行上限"+Constant.MAX_XLS_ROWS_NUMBER);
        return false;
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
        for (int i = 0; i < list.size(); i++) {
            Row row = dataTable.getSheet().createRow(lastRowNum + 1 + i);
            if(putObj(list.get(i)))
            break;
        }
        return lastRowNum + list.size();
    }

    @Override
    public boolean postObj(int index, T t) {
        Row row = dataTable.getSheet().getRow(index);
        if (row == null) {
            return false;
        }
        Map<Integer, Field> fieldMap = dataTable.getFieldMap();
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
    public boolean write() {
        OutputStream outputStrean = getOutputStrean();
        try {
            dataTable.getSheet().getWorkbook().write(outputStrean);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStrean != null) {
                    outputStrean.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
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
