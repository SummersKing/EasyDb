package org.summer.easydb.impl;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.DataWrapper;
import org.summer.easydb.util.SheetUtil;
import org.summer.easydb.util.Util;

import java.lang.reflect.Field;
import java.util.*;


public class Selector<T> extends AbsrtactSelector<T>  {

    Selector(DataTable sheet) {
        super(sheet);
    }

    public List<T> getRowsList(Integer[] specified) {
        ArrayList<T> list = new ArrayList<>();
        for (int i : specified) {
            list.add((T) getRowToObj(i));
        }
        return list;
    }

    //默认的
    public DataWrapper<T> getWrapper(Integer[] specified) {
        DataWrapper<T> dataWrapper=new DataWrapper<>();
        for (int i : specified) {
              dataWrapper.add(i,(T)getRowToObj(i));
        }
        return dataWrapper;
    }



        /*行号，查询条件， 查询条件和数据相等时返回*/




    /* like 查询，匹配同一行 =  if(cell 包含 cd1||cd2||...)则返回*/
    public SelectAble like(int fieldIndex, String ...conditions) {
        if(this.ending) return this;
        Set<Cell> cellSet = getCellSet(fieldIndex);
        setRowIndexSet(  likeSelectInColumn(cellSet,conditions));
        return  this;
    }
    //like 方法的重载
    public SelectAble like(String  fieldName, String ...conditions) {
        if(this.ending) return this;
        int columnIndex = Util.getFieldIndexByName(fieldName,dataTable.getT());
        like(columnIndex,conditions);
        return  this;
    }

    boolean onlyEq(int rowIndex,int fieldIndex,String condition,Sheet sheet){
        Row row = sheet.getRow(rowIndex);
        if(row!=null) {
            Cell cell = dataTable.getSheet().getRow(rowIndex).getCell(fieldIndex);
            if (condition.equals(SheetUtil.getCellContext(cell))) {
                return true;
            }
        }
        return false;
    }
    boolean multipleEq(int rowIndex,int fieldIndex,String[] conditions,Sheet sheet){

        Row row = sheet.getRow(rowIndex);
        if(row!=null) {
            Cell cell = row.getCell(fieldIndex);
            if (multipleEqualsJudge(SheetUtil.getCellContext(cell), conditions)) {
               return  true;
            }
        }
        return false;

    }

    public  void EqNewSelect(int fieldIndex,String ...conditions) {
        Sheet sheet = dataTable.getSheet();
        int  i=dataTable.getBeginRowIndex();
        int last=sheet.getLastRowNum();
        if(conditions.length==1){
            for (; i < last; i++) {
                if(multipleEq(i,fieldIndex,conditions,sheet)){
                    rowIndexSet.add(i);
                }
            }
        }else{
            for (; i < last; i++) {
                if(onlyEq(i,fieldIndex,conditions[0],sheet)){
                    rowIndexSet.add(i);
                }
            }
        }

    }

    public  void EqOldSelect(int fieldIndex,String ...conditions){
        Sheet sheet = dataTable.getSheet();
        Iterator<Integer> iterator = rowIndexSet.iterator();
        if(conditions.length==1){
            rowIndexSet.forEach(index->{ if(onlyEq(index,fieldIndex,conditions[0],sheet)){ rowIndexSet.add(index);}});}
        else{
            rowIndexSet.forEach(index -> {if (multipleEq(index, fieldIndex, conditions, sheet)) { rowIndexSet.add(index);} });
        }

    }
    @Override
    public SelectAble eq(int fieldIndex, String... conditions){
        if(this.ending) return this;
        boolean firstQuery = rowIndexSet.size() == 0 ? true : false;
        if(firstQuery){
           EqNewSelect( fieldIndex, conditions);
        }else{
            EqOldSelect(fieldIndex, conditions);
        }
        return this;
    }
    @Override
    public SelectAble eq(String fieldName, String... conditions){
        if(this.ending) return this;
        int fieldIndex = Util.getFieldIndexByName(fieldName,dataTable.getT());
        return eq(fieldIndex,conditions);
    }




    /**
     * 单个单元格数值比较的方法
     *
     * */



    /*获取最后的查询结果将结果转换为T list*/

    @Override
    public List<T>getResultList(){
        Object[] temp = this.rowIndexSet.toArray();
        if(temp.length==0){
            return new ArrayList<T>();
        }
        Integer[] specify = Arrays.copyOf(temp, temp.length, Integer[].class);
        return  getRowsList(specify);
    }

    @Override
    public DataWrapper<T> getResultWrapper(){
        Object[] temp = this.rowIndexSet.toArray();
        if(temp.length==0){
            return new DataWrapper<T>();
        }
        Integer[] specify = Arrays.copyOf(temp, temp.length, Integer[].class);
        return  getWrapper(specify);
    }






    /*获取某行中特定的单元格*/
    static  List<String> getRow(Row row, List<Integer> columnIndexList) {
        List<String> rowData = new ArrayList<>();
        for (int i = 0; i < columnIndexList.size(); i++) {
            final Cell cell = row.getCell(columnIndexList.get(i));
            if (cell != null) {
                rowData.add(SheetUtil.getCellContext(cell));
            } else {
                rowData.add("");
            }
        }
        return rowData;
    }




    public Object getRowToObj(int rowIndex) {
        final Row row = dataTable.getSheet().getRow(rowIndex);
        if (row == null) {
            return null;
        }
        Object obj=null;
        Class<T> clazz = dataTable.getT();
        try {
            obj = clazz.newInstance();
            for (Object i : dataTable.getHeadListKey()) {
                String value = SheetUtil.getCellContext(row.getCell((int)i));
                Field field = clazz.getDeclaredField((String) dataTable.getHeadListMap().get(i));
                field.setAccessible(true);
                field.set(obj, value);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return obj;
    }






}
