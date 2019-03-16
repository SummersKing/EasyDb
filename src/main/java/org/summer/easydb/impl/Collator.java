package org.summer.easydb.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.summer.easydb.CollateAble;

import java.util.HashSet;
import java.util.Set;


/**
 * @ClassName CollateDataTable
 * Created by Summer on 0:032019/3/17
 * @Description
 * @LastModified
 **/
public class Collator implements CollateAble {
    private DataTable dataTable;
    private  Set<Integer> nullRows;


    @Override
    public void check() {
        Sheet dataTableSheet = dataTable.getSheet();
        int lastRowNum = dataTableSheet.getLastRowNum();
        nullRows=new HashSet<>();
        for(int i=dataTable.getBeginRowIndex();i<lastRowNum;i++){
            if(dataTableSheet.getRow(i)==null){
                nullRows.add(i);
            }
        }
        return;


    }

    @Override
    public void collate() {

    }
}
