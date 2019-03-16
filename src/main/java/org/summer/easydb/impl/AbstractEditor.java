package org.summer.easydb.impl;

import org.summer.easydb.impl.DataTable;
import org.summer.easydb.EditAble;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class AbstractEditor<T> implements EditAble<T> {

    DataTable dataTable;
    String src;

    AbstractEditor(DataTable dataTable){
        this.dataTable=dataTable;
        this.src=dataTable.src;

    }
    OutputStream getOutputStrean(){
        OutputStream fos=null;
        try {
            fos = new FileOutputStream(dataTable.src);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fos;
    }


}
