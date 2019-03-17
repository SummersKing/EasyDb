package org.summer.easydb.impl;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.summer.easydb.util.SheetUtil;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.apache.poi.ss.usermodel.Cell.*;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;

public abstract class AbsrtactSelector<T> implements SelectAble<T>{
    DataTable dataTable;

    /*用来缓存符合条件的行号*/
    Set<Integer> rowIndexSet=new HashSet<>();
    /*本类获取的最后查询结果*/
    List<T> resultList;
    /*当 set 为0时则会调用set方法,设置为true,该对象每次查询都会判断是否为fasle ，若为false则不执行*/
    boolean ending=false;



    AbsrtactSelector( DataTable dataTable ) {
        this.dataTable=dataTable;
    }


   public  Set<Integer> getRowIndexSet(){
        return  this.rowIndexSet;
    }


    boolean multipleEqualsJudge(String target,String [] suspect){
        int size= suspect.length;
        for(int i=0;size>i;i++){
            if(target.equals(suspect[i])){
                return true;
            }
        }
        return false;
    }

    boolean multipleLikeJudge(String target,String[]suspect){
        int size= suspect.length;
        for(int i=0;size>i;i++){
            if(target.contains(suspect[i])){
                return true;
            }
        }
        return false;
    }

    // 将多个单元格和条件进行比较，返回符合条件的单元格的列数
    public Set<Integer> likeSelectInColumn( Set<Cell>column,String[] conditions) {
        if(conditions.length==1){
            column.forEach(cell->{
                if (SheetUtil.getCellContext(cell).contains(conditions[0])) {
                    rowIndexSet.add(cell.getRowIndex());
                }
            });
        }else{
                column.forEach(cell->{
                    if (multipleLikeJudge(SheetUtil.getCellContext(cell),conditions)) {
                        if(cell!=null)
                            rowIndexSet.add(cell.getRowIndex());
                    }
                });
            }

        return rowIndexSet;
    }
    /*当查询未初始化的时候为null,初始化set,当set长度为0时说明当下已经查询不到结果了，所以设置类状态为false*/
  public  void setRowIndexSet(Set<Integer> rowIndexSet) {
        if(this.rowIndexSet.size()==0) {
            this.rowIndexSet = rowIndexSet;
        }
        if(rowIndexSet.size()==0){
            this.ending=true;
        }else{
            this.rowIndexSet.retainAll(rowIndexSet);
        }
    }

    /*跟据属性rowindexSet 获取CelLSet*/
        Set<Cell> getCellSet(int fieldIndex){
        Set<Integer> rowIndexSet=getRowIndexSet();
        Set<Cell> cellSet=rowIndexSet.size()==0?getColumnCellSet(fieldIndex):getSpecifyCellSet(fieldIndex,rowIndexSet);
        return cellSet;
    }

        /*
         * @Name getSpecifyCellSet
         * @Desc 获取指定列，指定的行的Cell对象的集合
         * @Date 13:55 2019/3/1
         **/
      Set<Cell> getSpecifyCellSet(int fieldIndex, Set<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        Set<Cell> cellSet = new HashSet<>();
        while (iterator.hasNext()) {
            Cell cell = dataTable.getSheet().getRow(iterator.next()).getCell(fieldIndex);
            cellSet.add(cell);
        }
        return cellSet;
    }

    /* 通过属性索引获取某行的所有数据*/
    public Set<Cell> getColumnCellSet(int fieldIndex) {
        Set<Cell> cellSet = new HashSet<>( dataTable.getSheet().getLastRowNum());
        for (int i = dataTable.getBeginRowIndex(); i < dataTable.getSheet().getLastRowNum(); i++) {
            Row row = dataTable.getSheet().getRow(i);
            if(row==null){
                continue;
            }
            Cell cell = row.getCell(fieldIndex);
            cellSet.add(cell);
        }
        return cellSet;
    }



}
