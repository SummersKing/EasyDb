package org.summer.entity;

import org.summer.easydb.annotation.Column;
import org.summer.easydb.annotation.SheetInfo;

import java.io.Serializable;

@SheetInfo(sheetIndex = 1 ,beginRowIndex = 1)
public class Account implements Serializable {
    @Column(index = "A")
    private String account;
    @Column(index = "B")
    private String password;
    @Column(index = "C")
    private String nickName;

    public Account() {

    }

    public Account(String account, String password, String nickName) {
        this.account = account;
        this.password = password;
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
