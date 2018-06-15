package com.m1kes.korath.storage.mysql;


import com.m1kes.korath.storage.mysql.core.NITS_AsyncQuery;
import com.m1kes.korath.storage.mysql.core.NITS_Query;
import com.m1kes.korath.storage.yaml.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class NITS_MysqlManager {

    private HashMap<String, NITS_BaseMysql> databases = new HashMap<>();

    public NITS_MysqlManager() {

        Yaml yaml = new Yaml();
        try {

            Resource resource = new ClassPathResource("config.yml");
            InputStream resourceInputStream = resource.getInputStream();

            Configuration config = yaml.loadAs(resourceInputStream,Configuration.class);

            System.out.println("Loading config.yml");

            String host = config.getHost();
            String user = config.getUsername();
            String pass = config.getPassword();
            int    port = config.getPort();

            for ( String db : config.getDatabases() ) {
                System.out.println("Adding Database : " + db);
                NITS_BaseMysql mysql = new NITS_BaseMysql.MySQLBuilder()
                        .setHost(host).setPort(port).setUser(user).setPass(pass)
                        .setDatabase(db).build(true);
                addDatabase(db, mysql);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeAll() {
        for ( String key : new ArrayList<>(databases.keySet()) ) {
            databases.remove(key).disconnect();
        }
    }

    public void addDatabase(String key, NITS_BaseMysql mysql) {
        databases.put(key, mysql);
    }

    public NITS_BaseMysql getDatabase(String database) {
        NITS_BaseMysql mysql = databases.get(database);
        if ( mysql == null ) mysql = databases.get("nits" + database);
        return mysql;
    }

    public NITS_Query getQuery(String database, String sql) throws SQLException {
        return new NITS_Query(getDatabase(database), sql);
    }

    public NITS_AsyncQuery getAsyncQuery(String database, String sql) throws SQLException {
        return new NITS_AsyncQuery(getDatabase(database), sql);
    }

}
