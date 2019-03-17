package org.summer.entity;

import org.summer.easydb.annotation.Column;
import org.summer.easydb.annotation.SheetInfo;

import java.io.Serializable;

@SheetInfo(sheetIndex = 0 ,beginRowIndex = 1)
public class Company implements Serializable {

    @Column(index = "A")
    private String name;
    @Column(index="B")
    private String province;
    @Column(index = "C")
    private String city;
    @Column(index = "D")
    private String USCC;
    @Column(index = "E")
    private String legalPerson;
    @Column(index="F")
    private String type;
    @Column(index = "G")
    private String  createDate;
    @Column(index="H")
    private String registeredMoney;
    @Column(index="I")
    private String address;
    @Column(index="J")
    private String  mail;
    @Column(index = "K")
    private String  description;
    @Column(index = "L")
    private String  phone;



    public Company() {

    }

    public Company(String name, String province, String city, String USCC, String legalPerson, String type, String createDate, String registeredMoney, String address, String mail, String description, String phone) {
        this.name = name;
        this.province = province;
        this.city = city;
        this.USCC = USCC;
        this.legalPerson = legalPerson;
        this.type = type;
        this.createDate = createDate;
        this.registeredMoney = registeredMoney;
        this.address = address;
        this.mail = mail;
        this.description = description;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUSCC() {
        return USCC;
    }

    public void setUSCC(String USCC) {
        this.USCC = USCC;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRegisteredMoney() {
        return registeredMoney;
    }

    public void setRegisteredMoney(String registeredMoney) {
        this.registeredMoney = registeredMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", USCC='" + USCC + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", type='" + type + '\'' +
                ", createDate='" + createDate + '\'' +
                ", registeredMoney='" + registeredMoney + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
