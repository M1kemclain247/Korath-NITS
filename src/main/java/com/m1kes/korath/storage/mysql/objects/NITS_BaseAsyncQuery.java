package com.m1kes.korath.storage.mysql.objects;


import com.m1kes.korath.storage.mysql.NITS_BaseMysql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**

 This runs a query async and returns the resultset in a sync task!

 */
public class NITS_BaseAsyncQuery extends NITS_BaseQuery {

    public NITS_BaseAsyncQuery(NITS_BaseMysql mysql, String query) throws SQLException {
        super(mysql, query);
    }

    public NITS_BaseAsyncQuery(NITS_BaseMysql mysql, String query, int flag) throws SQLException {
        super(mysql, query, flag);
    }

    public NITS_BaseAsyncQuery(NITS_BaseMysql mysql, String query, String[] returnkeys) throws SQLException {
        super(mysql, query, returnkeys);
    }

    public void query(final NITS_BaseQueryCallback<ResultSet> callback) {
        new Thread(() -> {
            try {
                ResultSet rs = statement.executeQuery();
                if ( callback != null ) callback.onSuccess(rs);
            } catch (SQLException e) {
                if ( callback != null ) callback.onFailure(e);
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }


    public void execute(final NITS_BaseQueryCallback<Boolean> callback) {
        new Thread(() -> {
            try {
                boolean response = statement.execute();
                if ( callback != null ) callback.onSuccess(response);
            } catch (SQLException e) {
                if ( callback != null ) callback.onFailure(e);
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    public void update(final NITS_BaseQueryCallback<Integer> callback) {
        new Thread(() -> {
            try {
                int response = statement.executeUpdate();
                if ( callback != null ) callback.onSuccess(response);
            } catch (SQLException e) {
                if ( callback != null ) callback.onFailure(e);
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    @Override
    public int update() {
        update(null);
        return -1;
    }

    @Override
    public ResultSet query() throws IllegalAccessException { throw new IllegalAccessException("Calling this method on an async query is useless!"); }
}