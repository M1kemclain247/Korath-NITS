package com.m1kes.korath.storage.mysql;


import com.google.common.base.Strings;
import com.m1kes.korath.storage.mysql.objects.NITS_BaseAsyncQuery;
import com.m1kes.korath.storage.mysql.objects.NITS_BaseQuery;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class NITS_BaseMysql {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    private final int maximumPoolSize;

    private HikariDataSource pool;

    private NITS_BaseMysql(String host, int port, String database, String username, String password, int maximumPoolSize, boolean connect) {
        this.host = checkNotNull(Strings.emptyToNull(host), "host can't be null nor empty.");
        this.port = port;
        this.database = checkNotNull(Strings.emptyToNull(database), "database can't be null nor empty.");
        this.username = checkNotNull(Strings.emptyToNull(username), "username can't be null nor empty.");
        this.password = Strings.emptyToNull(password);
        this.maximumPoolSize = maximumPoolSize;

        checkArgument((maximumPoolSize > 0), "maximumPoolSize can't be less or equal to 0.");

        if (connect) {
            try {
                connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MySQLBuilder {

        private String host;
        private int port;
        private String database;
        private String username;
        private String password;
        private int maximumPoolSize;

        public final MySQLBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public final MySQLBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public final MySQLBuilder setDatabase(String database) {
            this.database = database;
            return this;
        }

        public final MySQLBuilder setUser(String username) {
            this.username = username;
            return this;
        }

        public final MySQLBuilder setPass(String password) {
            this.password = password;
            return this;
        }

        public final MySQLBuilder setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public final NITS_BaseMysql build(boolean connect) {
            if (maximumPoolSize <= 0) maximumPoolSize = 10;
            return new NITS_BaseMysql(host, port, database, username, password, maximumPoolSize, connect);
        }
    }

    public final synchronized void connect() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);

        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password != null ? password : "");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("useLocalSessionState", true);
        config.addDataSourceProperty("useLocalTransactionState", true);
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.addDataSourceProperty("cacheServerConfiguration", true);
        config.addDataSourceProperty("elideSetAutoCommits", true);
        config.addDataSourceProperty("maintainTimeStats", true);

        config.setMaximumPoolSize(maximumPoolSize);
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(4);
        config.setIdleTimeout(10000);
        config.setLeakDetectionThreshold(5000);
        config.setAutoCommit(true);
        config.setPoolName(database);

        pool = new HikariDataSource(config);
    }

    public final synchronized void disconnect() {
        pool.close();
        pool = null;
    }

    public final synchronized Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public NITS_BaseQuery getQuery(String querystring) throws SQLException {
        return new NITS_BaseQuery(this, querystring);
    }

    public NITS_BaseAsyncQuery getAsyncQuery(String querystring) throws SQLException {
        return new NITS_BaseAsyncQuery(this, querystring);
    }
}

