package com.m1kes.korath.storage.yaml;

public class Configuration {


    private String host;
    private String username;
    private String password;
    private int port;
    private String[] databases;

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String[] getDatabases() {
        return databases;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDatabases(String[] databases) {
        this.databases = databases;
    }
}
