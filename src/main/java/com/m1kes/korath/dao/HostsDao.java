package com.m1kes.korath.dao;

import com.m1kes.korath.NITS_Core;
import com.m1kes.korath.objects.Host;
import com.m1kes.korath.objects.Reloadable;
import com.m1kes.korath.storage.mysql.core.NITS_Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostsDao extends Dao {

    private List<Host> hosts;

    public HostsDao(){
        load();
    }

    private void load(){
        getAll(true);
    }

    public List<Host> getForCategory(int category_id){
        return hosts.stream()
                .filter(h -> h.getCategory_id() == category_id)
                .collect(Collectors.toList());
    }

    public List<Host> getAll(boolean reload){
        if(hosts == null || hosts.isEmpty() || reload) {
            hosts = getAllData();
        }
        return hosts;
    }

    private List<Host> getAllData(){

        List<Host> hosts = new ArrayList<>();

            NITS_Query query = null;
            try {
                query = NITS_Core.getMain().getMysqlManager().getQuery("nits_config", "SELECT * FROM hosts ORDER BY sort_order DESC");

                ResultSet rs = query.query();
                while (rs.next()) {
                    hosts.add(new Host(rs.getString("name"), rs.getString("ip"), rs.getInt("category_id"),rs.getString("img_path")));
                }

                rs.close();
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                if (query != null) query.close();
            }
            return hosts;
    }

    @Override
    public void reload() {
        load();
    }

}
