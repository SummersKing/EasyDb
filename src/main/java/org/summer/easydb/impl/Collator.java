package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.util.SheetUtil;

import java.util.*;


/**
 * @ClassName CollateDataTable
 * Created by Summer on 0:032019/3/17
 * @Description   观察者类，目前没什么用
 * @LastModified
 **/
public class Collator  implements Observer {


    @Deprecated
    public Set<Integer> check(DataTable dataTable) {
        Sheet dataTableSheet = dataTable.getSheet();
        int lastRowNum = dataTableSheet.getLastRowNum();
        Set<Integer> nullRows=new HashSet<>();
        for(int i=dataTable.getBeginRowIndex();i<lastRowNum;i++){
            if(dataTableSheet.getRow(i)==null){
                nullRows.add(i);
            }
        }
        return nullRows;

    }
    @Deprecated
    public void collate(Set<Integer> nullRows,DataTable dataTable) {
        Sheet sheet = dataTable.getSheet();
        int targetIndex = sheet.getLastRowNum();
        Integer blankIndex=null;
        Row blank=null;
        Row target=null;
        Iterator<Integer> iterator = nullRows.iterator();
        Object[] headList = dataTable.getHeadListKey().toArray();
        while (iterator.hasNext()) {
            blankIndex=iterator.next();
            blank=sheet.createRow(blankIndex);
            while(sheet.getRow(targetIndex)!=null){
                target=sheet.getRow(targetIndex);
               for(Object cellIndex:headList){
                   blankIndex=(Integer)cellIndex;
                   Cell cell = target.getCell(blankIndex);
                   if(cell!=null){
                       String context = SheetUtil.getCellContext(cell);
                       blank.createCell(blankIndex).setCellValue(context);
                       target.removeCell(cell);
                   }
               }
               break;
            }
        }
    }
    public void fillRow(int blank,int target){


    }

    @Override
    public void update(Observable o, Object arg) {
        ObservedEditor editor=((ObservedEditor) arg);
        DataTable dataTable = editor.getEditor().getDataTable();
        int lastRowNum = dataTable.getSheet().getLastRowNum();
 /*       if(lastRowNum>3000){
                editor.setNullRows(check(dataTable));
                collate(editor.getNullRows(),dataTable);
       }*/
        System.out.println(lastRowNum+"长度");
    }
}
