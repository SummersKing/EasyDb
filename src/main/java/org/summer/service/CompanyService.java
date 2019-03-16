package org.summer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.summer.dao.Data;
import org.summer.easydb.DataAble;
import org.summer.easydb.DataWrapper;
import org.summer.entity.Company;
import org.summer.easydb.EditAble;

import java.util.List;
import java.util.Random;
@Service
public class CompanyService {
    @Autowired
    private Data data;


    public String testEq1(){

    return data.company().getSelector().like("name","新","美").eq(2,"西安市","杭州市","北京市").getResultList().size()+"";
    }

    public String testEq(){
        return data.company().getSelector().eq("city","成都市","南昌市","芝麻饼市","衡阳市").getResultList().size()+"";
    }
    public String testLike(){

        return data.company().getSelector().like("name","吃","睡","离").getResultList().size()+"";
    }
    public String testMultipe(){

        return data.company().getSelector().like("name","新","").like(0,"新").eq("city","北京市").getResultList().size()+"";
    }

    public String testPutBatch(){
        DataAble db = data.company();
        String[]array={"王","涛","杰","健","坤","颖"};
        List resultList = (List<Company>)db.getSelector().like(4, array[new Random().nextInt(6)]).getResultList();
        EditAble editor = db.getEditor();
        editor.putObjBatch(resultList);
        boolean write = editor.write();
        String s = write ? "成功写入"+resultList.size()+"条数据" : "写入失败";
        return s;
    }

    public String testPut(){
        DataAble db = data.company();
        String[]array={"海南","广东","福建","四川","浙江","陕西"};
        int i = new Random().nextInt(6);
        List resultList = db.getSelector().like(1, array[i]).getResultList();
        EditAble editor = db.getEditor();
        editor.putObj(resultList.get(0));
        String s =editor.write() ? "成功写入"+resultList.get(0): "写入失败";
        return s;
    }

    public String testPostBatch(){
        DataAble db = data.company();
        String[]array={"福州市","安","阳","西","江","州"};
        int i = new Random().nextInt(6);
        DataWrapper wrapper = db.getSelector().like(2, array[i]).getResultWrapper();
        EditAble editor = db.getEditor();
        String s = editor.postObjBatch(wrapper);
        editor.write();
        return s;
    }
    public String testPost(){
        DataAble db = data.company();
        EditAble editor = db.getEditor();
        Company company = new Company();
        int i = new Random().nextInt(65000);
        company.setPhone("139 9242 "+i);
        boolean s = editor.postObj(i,company);
        editor.write();
        return s+""+i;
    }
    public String testDel(){
        DataAble db = data.company();
        int i = new Random().nextInt(65000);
        EditAble editor = db.getEditor();
        editor.del(i);
        editor.write();
        return "删除第"+i+"行";
    }
    public String testDelBatch(){
        DataAble db = data.company();
        int range = new Random().nextInt(300);
        int[] a=new int[range];
        for(int i=0;i<range;i++) {
         a[i]=new Random().nextInt(65000);
        }
        EditAble editor = db.getEditor();
        int i = editor.delBatch(a);
        editor.write();
        return "批量删除+"+i+"行";
    }

    public String testGetWrapper(){
        DataAble db = data.company();
        db.getSelector().like(0,"欣").like("city","市","山").getResultWrapper();
        return "";
    }




}
