package org.summer.easydb.impl;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.*;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;

public abstract class AbstractWorkBook {
    String src;
    Workbook workbook;
    public AbstractWorkBook(String src){
        this.src=src;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(this.src));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public AbstractWorkBook(){

    }


}
