package com.m1kes.korath.dao;

import com.m1kes.korath.NITS_Core;
import com.m1kes.korath.objects.Category;
import com.m1kes.korath.objects.Reloadable;
import com.m1kes.korath.storage.mysql.core.NITS_Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends Dao {

    private List<Category> categories;

    public CategoryDao(){
        load();
    }

    private void load(){
        getAll();
    }

    public List<Category> getAll(){
        if(categories == null || categories.isEmpty()) {

            categories = new ArrayList<>();

            NITS_Query query = null;
            try {
                query = NITS_Core.getMain().getMysqlManager().getQuery("nits_config", "SELECT * FROM host_categories");

                ResultSet rs = query.query();
                while (rs.next()) {
                    categories.add(new Category(rs.getInt("id"), rs.getString("name")));
                }

                rs.close();
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                if (query != null) query.close();
            }

        }

        return categories;
    }


    @Override
    public void reload() {
        load();
    }

}
