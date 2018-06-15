package com.m1kes.korath.storage.mysql.core;

import com.m1kes.korath.storage.mysql.NITS_BaseMysql;
import com.m1kes.korath.storage.mysql.objects.NITS_BaseAsyncQuery;

import java.sql.SQLException;

public class NITS_AsyncQuery extends NITS_BaseAsyncQuery {

    public NITS_AsyncQuery(NITS_BaseMysql mysql, String query) throws SQLException {
        super(mysql, query);
    }

    public NITS_AsyncQuery(NITS_BaseMysql mysql, String query, int flag) throws SQLException {
        super(mysql, query, flag);
    }

    public NITS_AsyncQuery(NITS_BaseMysql mysql, String query, String[] returnkeys) throws SQLException {
        super(mysql, query, returnkeys);
    }

}
