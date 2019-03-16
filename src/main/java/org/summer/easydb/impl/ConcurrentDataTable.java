package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.util.Util;

import java.util.List;

/**
 * @ClassName ConcurrentDataTable
 * Created by Summer on 21:032019/3/16
 * @Description
 * @LastModified
 **/
public class ConcurrentDataTable extends  DataTable {
    volatile DataTable main;

    List<Sheet> temp;

    private  ConcurrentDataTable(){

    }
    void intial(DataTable main){
        this.main=main;
    }

    @Override
    public Sheet getSheet(){

        return this.create();
    }

    Sheet create(){
        Sheet dataTable = (Sheet)Util.deepClone(main.getSheet());
            temp.add(dataTable);
            return dataTable;
    }

    public void merge(){
    temp.forEach(sheet->{
        int mainLast = main.getSheet().getLastRowNum();
        int end = sheet.getLastRowNum() > mainLast ? sheet.getLastRowNum() : mainLast;
        Row row=null;
        Row mainRow=null;
        for(int i=main.getBeginRowIndex();i<end;i++){
            row = sheet.getRow(i);
            mainRow=sheet.getRow(i);
            if(row!=mainRow){



            }
        }

    });

    }
}
