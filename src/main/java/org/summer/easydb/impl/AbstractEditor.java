package org.summer.easydb.impl;

import org.summer.easydb.EditAble;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class AbstractEditor<T> implements EditAble<T> {

    Sheet dataTable;
    String src;

    AbstractEditor(Sheet dataTable){
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
