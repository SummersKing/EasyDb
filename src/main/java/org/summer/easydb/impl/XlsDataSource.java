package org.summer.easydb.impl;

import org.summer.easydb.Constant;

public  class XlsDataSource extends AbstractDataSource {



    public XlsDataSource(String src){
        super(src);
        this.src=src;
        this.maxRowNums= Constant.MAX_XLS_ROWS_NUMBER;
    }



}
