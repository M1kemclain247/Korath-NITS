package com.m1kes.korath.storage.mysql.objects;

import java.sql.SQLException;

public abstract class NITS_BaseQueryCallback<T> {
    public abstract void onSuccess(T response) throws SQLException;
    public void onFailure(SQLException ex) {
        ex.printStackTrace();
    }
}