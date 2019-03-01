package org.summer.easydb;




public interface DataTable<T>  {

    int getFieldIndexByName(String fieldName);

    EditAble<T> getEditor();

    SelectAble getSelector();







}
