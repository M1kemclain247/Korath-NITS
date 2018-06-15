package com.m1kes.korath.storage.mysql.core;

import com.m1kes.korath.storage.mysql.NITS_BaseMysql;
import com.m1kes.korath.storage.mysql.objects.NITS_BaseQuery;

import java.sql.SQLException;

public class NITS_Query extends NITS_BaseQuery {

    public NITS_Query(NITS_BaseMysql mysql, String query) throws SQLException {
        super(mysql, query);
    }

    public NITS_Query(NITS_BaseMysql mysql, String query, int flag) throws SQLException {
        super(mysql, query, flag);
    }

    public NITS_Query(NITS_BaseMysql mysql, String query, String[] returnkeys) throws SQLException {
        super(mysql, query, returnkeys);
    }

}