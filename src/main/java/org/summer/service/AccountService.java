package org.summer.service;

import org.summer.easydb.impl.Sheet;
import org.summer.util.BeanContainer;
public class AccountService {
    private    Sheet wb= BeanContainer.getBean("account", new Sheet().getClass());



}
