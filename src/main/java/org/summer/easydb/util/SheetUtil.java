package org.summer.easydb.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.SimpleDateFormat;

import static org.apache.poi.ss.usermodel.Cell.*;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;

/**
 * @ClassName SheetUtil
 * Created by Summer on 15:542019/3/17
 * @Description
 * @LastModified
 **/
public class SheetUtil {



    /*获取某个单元格数据*/
   public static  String getCellContext(Cell cell) {
        String cellValue = "";
        if(cell==null){return "";}
        switch (cell.getCellType()) {
            case CELL_TYPE_BLANK:
                cellValue = "空";
                break;
            case CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(cell.getDateCellValue());
                } else {
                    cellValue = "" + cell.getNumericCellValue();
                }
                break;
            case CELL_TYPE_BOOLEAN:
                cellValue = "" + cell.getBooleanCellValue();
                break;
        }
        return cellValue;
    }

}
