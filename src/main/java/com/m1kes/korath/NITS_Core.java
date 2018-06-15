package com.m1kes.korath;

import com.m1kes.korath.dao.DaoManager;
import com.m1kes.korath.storage.mysql.NITS_MysqlManager;
import org.springframework.beans.factory.InitializingBean;

public class NITS_Core implements InitializingBean {

    private NITS_MysqlManager mysqlManager;
    private DaoManager daoManager;
    private static NITS_Core main;

    @Override
    public void afterPropertiesSet() throws Exception {
        load();
    }


    private void load(){
        main = this;
        System.out.println("**********************************");
        System.out.println("LOADING ALL CRITICAL SYSTEM DATA");
        System.out.println("**********************************");


        mysqlManager = new NITS_MysqlManager();
        daoManager = new DaoManager();


    }

    public static NITS_Core getMain() {
        return main;
    }

    public NITS_MysqlManager getMysqlManager() {
        return mysqlManager;
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }
}
