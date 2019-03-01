package org.summer.easydb.impl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;


public  class WorkBook  extends AbstractWorkBook {



    public WorkBook(){

    }
    public WorkBook(String src){
        this.src=src;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(this.src));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }



}
