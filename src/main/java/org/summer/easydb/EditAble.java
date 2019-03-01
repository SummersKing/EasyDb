package org.summer.easydb;

import java.util.List;

public interface EditAble<T> {
    /*
     * @Name write
     * @Desc 所有的编辑操作左后都需要通过该方法写入数据库中；
     * @Date 19:20 2019/3/1
     **/
    boolean write();

    /*
     * @Name putObj
     * @Desc 将单个对象放入数据库最后一行
     * @Date 19:21 2019/3/1
     **/
    boolean putObj(T t);

    /*
     * @Name putObjBatch
     * @Desc 将对象集合放入数据中
     * @Date 19:22 2019/3/1
     **/
    int putObjBatch(List<T> t);

    /*
     * @Name postObj
     * @Desc 修改制定行的数据
     * @Date 19:22 2019/3/1
     **/
    boolean postObj(int index, T t);

    /*
     * @Name postObjBatch
     * @Desc 批量修改数据
     * @Date 19:23 2019/3/1
     **/
    String postObjBatch(DataWrapper<T> wrapper);

    /*
     * @Name del
     * @Desc 删除指定行的数据
     * @Date 19:23 2019/3/1
     **/
    boolean del(int index);

    /*
     * @Name delBatch
     * @Desc 批量删除数据
     * @Date 19:24 2019/3/1
     **/
    int delBatch(int[] batch);


}
