package com.m1kes.korath.dao;

import com.m1kes.korath.objects.Reloadable;

public abstract class Dao implements Reloadable {

    public abstract void reload();

    @Override
    public void onReload() {
        reload();
    }
}
