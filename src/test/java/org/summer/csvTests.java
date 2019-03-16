package org.summer;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.summer.dao.Data;
import org.summer.easydb.*;
import org.summer.entity.Account;
import org.summer.entity.Company;

import java.util.List;


//@RunWith(SpringRunner.class)
@RunWith(JUnit4.class)
@SpringBootTest
public class csvTests {

    @Test
    /*横向数据采集测试*/
    public void rowTest() {
        DataAble<Company> dataTable = DataSourceFactory.connectSheet(new Company(), new Data());
        EditAble editor = dataTable.getEditor();
        Company company = new Company();
        company.setPhone("12321");
        editor.putObj(company);
        editor.write();
    }
    @Test
    /*横向数据采集测试*/
    public void rowTe1st() {
        DataAble<Account> dataTable = DataSourceFactory.connectSheet(new Account(), new Data());
        EditAble editor = dataTable.getEditor();
        Account company = new Account();
        company.setNickName("1ninoinlkjnioklnjnjnjnjio2321");
        DataWrapper<Account> accountDataWrapper = new DataWrapper<>();
        accountDataWrapper.add(2,company);
        accountDataWrapper.add(3,company);
        editor.postObjBatch(accountDataWrapper);
        editor.postObj(6,company);
        editor.write();
    }

    @Test
    /*横向数据采集测试*/
    public void select() {
        DataAble <Account>dataTable = DataSourceFactory.connectSheet(new Company(), new Data());
        System.out.println(dataTable.getClass().getName());
        System.out.println(dataTable.getClass().getTypeName());
        List resultList = dataTable.getSelector().like("name", "四川", "四川").getResultList();
        System.out.println(resultList.size());
       resultList.forEach(o -> {
           System.out.println(o);
       });


    }
        @Test
    public  void test()
    {
        DataAble db = DataSourceFactory.connectSheet(new Company(), new Data());
        EditAble editor = db.getEditor();
        Company company = new Company();
        company.setName("asifdhonsaiunholsa");
        editor.putObjForce(6356,company);
        editor.write();
    }

    @Test
    public  void put()
    {
        DataAble db = DataSourceFactory.connectSheet(new Company(), new Data());
        EditAble editor = db.getEditor();
        Company company = new Company();
        company.setName("asifdhonsaiunholsa");
        company.setPhone("1232");
        company.setCity("北京");
        editor.putObj(company);
        editor.write();
    }






    /*
    @Test
    *//*纵向数据采集测试*//*
    *//*废弃测试*//*
    public void columnTest() {
        DataTable db =DataSourceFactory.connectSheet(new Company(), new Data());
        List resultList = db.getSelector().like("name", "新").getResultList();
        System.out.println(resultList.size());
    }



    @Test
    public  void se(){
        DataTable<Company> sheet = DataSourceFactory.connectSheet(new Company(), new Data());
        Selector<Company> selector = sheet.getSelector();
        List resultList = selector.like("欣", "name").getResultList();
        System.out.println(resultList.size());
    }
    @Test
    public  void se1(){
        DataTable<Company> sheet = DataSourceFactory.connectSheet(new Company(), new Data());
        Selector<Company> selector = sheet.getSelector();
        List resultList = selector.like("name", "离","散").eq(2,"大连市").getResultList();
        System.out.println(resultList.size());
    }

    @Test
    public  void map(){
        Map<Integer,String> map=new HashMap<>();
        map.put(0,"name");
        map.put(2,"city");
        map.put(3,"USCC");
        map.put(4,"legalPerson");
        map.put(6,"createDate");
        Set<Integer> integers = map.keySet();
        integers.size();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            int i=0;
            do {
                i++;
                String s = map.get(iterator.next());
                System.out.println(s);
            } while (i < integers.size() - 1);
            String s = map.get(iterator.next());
            System.out.println(s);

        }


    }

    @Test

    public void annotation(){
        DataTable<Company> sheet = DataSourceFactory.connectSheet(new Company(), new Data());
        //sheet.getEditor().delete(3778);
        Selector<Company> selector = sheet.getSelector();
        DataWrapper<Company> wrapper=new DataWrapper<>();
        Company company=new Company();
        company.setCity("啥时能花花奴奴i市");
        wrapper.add(18,company);
        wrapper.add(20,company);
        Editor<Company> editor = sheet.getEditor();
        editor.postObjBatch(wrapper);
        editor.write();
    }

    @Test

    public void q2(){
        DataTable<Account> sheet = DataSourceFactory.connectSheet(new Account(), new Data());
        List<Account> rowsList = sheet.getSelector().getRowsList(new Integer[]{8, 10});
        System.out.println(rowsList.get(0));
        DataWrapper<Account> wrapper=new DataWrapper<>();
        Account a=new Account();
       a.setNickName("测试修改");
        wrapper.add(8,a);
        wrapper.add(10,a);
        Editor<Account> editor = sheet.getEditor();
        String s = editor.postObjBatch(wrapper);
        System.out.println(s);
        editor.write();
    }*/

}

