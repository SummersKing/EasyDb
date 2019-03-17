package org.summer.easydb;


import org.summer.easydb.impl.SelectAble;

public interface DataAble<T>   {



    EditAble<T> getEditor();

    SelectAble<T> getSelector();







}
