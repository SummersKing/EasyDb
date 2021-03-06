package org.summer.easydb.impl;

import org.summer.easydb.DataWrapper;

import java.util.List;
import java.util.Set;

public interface SelectAble<T> {

    List<T> getResultList();
    DataWrapper<T> getResultWrapper();
    SelectAble eq(String fieldName, String... conditions);
    SelectAble eq( int fieldIndex,String ...conditions);
    SelectAble like( String  fieldName,String ...conditions);
    SelectAble like( int fieldIndex,String ...conditions);
    Set<Integer> getRowIndexSet();
    void setRowIndexSet(Set<Integer> rowIndexSet);








}
