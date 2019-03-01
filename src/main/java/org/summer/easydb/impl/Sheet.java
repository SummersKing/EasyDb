package org.summer.easydb.impl;
import org.summer.easydb.SelectAble;
import org.summer.easydb.EditAble;

import java.util.*;
public class Sheet<T> extends AbstractSheet<T>  {

    public Sheet() {

    }
    public Sheet(int beginRowIndex, int sheetIndex, int primaryKey,String src, Map<Integer, String> headListMap, Class<T> t) {

       super(beginRowIndex,sheetIndex,src,primaryKey,headListMap,t);

    }



    @Override
    public EditAble getEditor() {

        return new SheetEditor(this);
    }

    @Override
    public SelectAble getSelector() {

        return new SheetSelector(this);
    }



}

