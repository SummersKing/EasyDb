package org.summer.easydb.impl;

import org.summer.easydb.EditAble;
import org.summer.easydb.util.IOUtil;


public abstract class AbstractEditor<T>  implements EditAble<T>  {

    private DataTable dataTable;
    private  String src;

    AbstractEditor(DataTable dataTable){
        this.dataTable=dataTable;
        this.src=dataTable.getSrc();

    }



    @Override
    public boolean write() {

    return  IOUtil.write(this.src,this.dataTable.getWorkbook());
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
