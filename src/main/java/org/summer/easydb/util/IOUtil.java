package org.summer.easydb.util;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName IOUtil
 * Created by Summer on 0:132019/3/17
 * @Description
 * @LastModified
 **/
public class IOUtil {



    public static  boolean write(String src, Workbook workBook) {
        OutputStream fos=null;
        try {
            fos = new FileOutputStream(src);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workBook.write(fos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
