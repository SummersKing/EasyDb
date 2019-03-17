package org.summer.easydb.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.summer.easydb.DataAble;
import org.summer.easydb.EditAble;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public  class  DataTable<T> extends DataSource implements DataAble<T> , Serializable {

     int sheetIndex;
     Map<Integer, String> headListMap;
     Set<Integer> headListKey;
     int beginRowIndex;
     Class<T> t;
     Sheet sheet;
    public DataTable(){

    }

    public DataTable(int beginRowIndex, int sheetIndex, String src, Map<Integer, String> headListMap, Class<T> t) {
        super(src);
        this.headListMap = headListMap;
        this.headListKey = headListMap.keySet();
        this.beginRowIndex = beginRowIndex;
        this.sheetIndex = sheetIndex;
        this.t = t;
        if(super.getWorkbook().getClass()== HSSFWorkbook.class){
            this.sheet=getWorkbook().getSheetAt(sheetIndex);
        }else{
            this.sheet= (XSSFSheet) getWorkbook().getSheetAt(sheetIndex);

        }
    }


    public Map<Integer, String> getHeadListMap() {
        return headListMap;
    }

    public Set<Integer> getHeadListKey() {
        return headListKey;
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


    public int getSheetIndex() {return sheetIndex;}

    public void setSheetIndex(int sheetIndex) { this.sheetIndex = sheetIndex;}

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
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
