package com.m1kes.korath.dao;

import com.m1kes.korath.objects.Reloadable;

import java.util.ArrayList;
import java.util.List;

public class DaoManager implements Reloadable {

    private HostsDao hostsDao;
    private CategoryDao categoryDao;

    private List<Dao> daos = new ArrayList<>();

    public DaoManager(){
        load();
    }

    private void load(){
        hostsDao = new HostsDao();
        categoryDao = new CategoryDao();

        setup();
    }

    private void setup(){
        daos.add(hostsDao);
        daos.add(categoryDao);
    }


    public HostsDao getHosts() {
        return hostsDao;
    }

    public CategoryDao getCategories() {
        return categoryDao;
    }

    @Override
    public void onReload() {
        if(daos == null || daos.isEmpty())return;
        for(Dao d : daos){
            if(d == null)return;
            d.onReload();
        }

    }
}
