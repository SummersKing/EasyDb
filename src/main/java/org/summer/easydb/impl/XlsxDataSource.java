package org.summer.easydb.impl;

import org.summer.easydb.Constant;

public  class XlsxDataSource extends AbstractDataSource {



    public XlsxDataSource(String src){
        super(src);
        this.src=src;
        this.maxRowNums= Constant.MAX_XLSX_ROWS_NUMBER;
    }



}
