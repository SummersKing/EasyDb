package org.summer.easydb.impl;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;


public abstract class AbstractDataSource {
    String src;
    Workbook workbook;
    int maxRowNums;

    public AbstractDataSource(){

    }
    public AbstractDataSource(String src){
        this.src=src;
        if(src!=null){
                workbook = create(new File(this.src));
        }else {
            throw new IllegalArgumentException(src+"数据源创建失败");
        }

        maxRowNums = 0;
    }


    public static Workbook create(File file) {

        try {
            InputStream inp = new FileInputStream(file);
            inp = !inp.markSupported() ? new PushbackInputStream(inp, 8) : inp;
            if (POIFSFileSystem.hasPOIFSHeader(inp)) {

                return new HSSFWorkbook(inp);
            } else if (POIXMLDocument.hasOOXMLHeader(inp)) {
                return new XSSFWorkbook(OPCPackage.open(inp));
            } else {
                throw new IllegalArgumentException("该数据源无法读取，请检查文件");
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println(file.getAbsolutePath() + "没有找到");
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

}
