package org.summer.easydb.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.summer.easydb.Constant;
import org.summer.easydb.DataSourceFactory;

import java.io.*;


public abstract class DataSource implements Serializable {
    private String src;
    private Workbook workbook;
    private int maxRowNums;


    public DataSource(){

    }
    public DataSource(String src){
        setSrc(src);
        setWorkbook();
        setMaxRowNums();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {


        this.src = src;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public DataSource setWorkbook() {
        if(src!=null){
           this.workbook = DataSourceFactory.getDataSource(this.src);
        }else {
            throw new IllegalArgumentException(src+"数据源创建失败");
        }
        return this;
    }

    public int getMaxRowNums() {
        return maxRowNums;
    }

    public DataSource setMaxRowNums() {
        if(workbook.getClass()!=null){
            if(workbook.getClass()==HSSFWorkbook.class){
                this.maxRowNums= Constant.MAX_XLS_ROWS_NUMBER;
            }else{
                this.maxRowNums= Constant.MAX_XLSX_ROWS_NUMBER;
            }
        }
        return this;
    }
}
