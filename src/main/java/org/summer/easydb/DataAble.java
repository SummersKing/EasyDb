package org.summer.easydb;




public interface DataAble<T>  {

    int getFieldIndexByName(String fieldName);

    EditAble<T> getEditor();

    SelectAble<T> getSelector();







}
