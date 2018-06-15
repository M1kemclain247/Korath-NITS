package com.m1kes.korath.storage.mysql.objects;


import com.m1kes.korath.storage.mysql.NITS_BaseMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NITS_BaseQuery {

    private NITS_BaseMysql mysql;
    PreparedStatement statement;

    public NITS_BaseQuery(NITS_BaseMysql mysql, String query) throws SQLException {
        if ( mysql == null ) throw new NullPointerException("MySQL object cannot be null.");
        this.mysql = mysql;

        statement = mysql.getConnection().prepareStatement(query);
    }

    public NITS_BaseQuery(NITS_BaseMysql mysql, String query, int flag) throws SQLException {
        if ( mysql == null ) throw new NullPointerException("MySQL object cannot be null.");
        this.mysql = mysql;

        statement = mysql.getConnection().prepareStatement(query, flag);
    }

    public NITS_BaseQuery(NITS_BaseMysql mysql, String query, String[] returnkeys) throws SQLException {
        if ( mysql == null ) throw new NullPointerException("MySQL object cannot be null.");
        this.mysql = mysql;

        statement = mysql.getConnection().prepareStatement(query, returnkeys);
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setValue(int key, Object value) throws SQLException {
        statement.setObject(key, value);
    }

    public boolean execute() throws SQLException {
        return statement.execute();
    }

    public ResultSet query() throws SQLException, IllegalAccessException {
        return statement.executeQuery();
    }

    public int update() throws SQLException {
        return statement.executeUpdate();
    }

    public void close() {
        try {
            if ( !statement.isClosed() ) {
                statement.close();
            }

            if ( !statement.getConnection().isClosed() ) {
                statement.getConnection().close(); //IMPORTANT, it will not close the connection but notify that it is available to use.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}