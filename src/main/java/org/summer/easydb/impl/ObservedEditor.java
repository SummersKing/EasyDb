package org.summer.easydb.impl;

import org.summer.easydb.DataWrapper;
import org.summer.easydb.EditAble;
import org.summer.easydb.util.IOUtil;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ObservedEditor
 * Created by Summer on 12:012019/3/17
 * @Description 可以被观察的编辑器对象，使用了 装饰器设计模式
 * @LastModified
 **/

public class ObservedEditor extends Observable implements EditAble {

    private volatile Editor editor;
    private Set<Integer> nullRows;

    public ObservedEditor(EditAble editAble) {
        this.editor = (Editor) editAble;
    }

    @Override
    public synchronized boolean write() {

        boolean successful = IOUtil.write(editor.getSrc(), editor.getDataTable().getWorkbook());

        if (successful) {
            super.setChanged();
            super.notifyObservers(this);
            IOUtil.write(editor.getSrc(), editor.getDataTable().getWorkbook());
        }

        return successful;
    }


    @Override
    public boolean putObj(Object o) {
        return editor.putObj(o);
    }

    @Override
    public boolean putObjForce(int index, Object o) {
        return editor.putObjForce(index, o);
    }

    @Override
    public int putObjBatch(List t) {

        if(t.size()>100){
            List<Object> list = Collections.synchronizedList(t);
            ExecutorService service = Executors.newCachedThreadPool();

            Runnable run1 = () -> {
                System.out.println("线程B");
                int i = editor.putObjBatch(list);

            };
            service.submit(run1);
            write();
        }

        return t.size();
    }

    @Override
    public boolean postObj(int index, Object o) {
        return editor.postObj(index, o);
    }

    @Override
    public String postObjBatch(DataWrapper wrapper) {
        return editor.postObjBatch(wrapper);
    }

    @Override
    public boolean del(int index) {
        return editor.del(index);
    }

    @Override
    public int delBatch(int[] batch) {
        return editor.delBatch(batch);
    }

    public Editor getEditor() {
        return editor;
    }

    public Set<Integer> getNullRows() {
        return nullRows;
    }

    public void setNullRows(Set<Integer> nullRows) {
        this.nullRows = nullRows;
    }
}
