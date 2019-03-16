package org.summer.easydb;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.summer.easydb.annotation.Column;
import org.summer.easydb.annotation.SheetInfo;
import org.summer.easydb.impl.DataTable;
import org.summer.easydb.util.Util;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DataSourceFactory {

    public static DataAble connectSheet(Object obj , DataSourceConfig data){
        Class<?> clazz =  obj.getClass();
        SheetInfo sheetInfo = clazz.getAnnotation(SheetInfo.class);
        Integer sheetIndex = sheetInfo.sheetIndex();
        if(sheetIndex==null){
            throw  new NullPointerException(clazz.getTypeName()+"需要配置sheetIndex");
        }
        Field[] fields = clazz.getDeclaredFields();
        Map map=new HashMap<Integer,String>();
        for(Field field:fields){
            Column hl = field.getAnnotation(Column.class);
            map.put(Util.transferA21(hl.index()),field.getName());
        }
        return new DataTable(sheetInfo.beginRowIndex(),sheetIndex,data.DataSource(),map,clazz);
    }

    /*
     * @Name create
     * @Desc  工厂方法，自动判断xls 文件或者xlsx 文件；
     * @Date 16:55 2019/3/16
     **/
    public static Workbook getDataSource(String src) {
        try {
            InputStream inp = new FileInputStream(new File(src));
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
            System.out.println(src+ "没有找到,请检查路径");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
