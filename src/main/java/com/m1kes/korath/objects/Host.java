package com.m1kes.korath.objects;

public class Host {

    private String name;
    private String ip;
    private int category_id;
    private String img_path;

    public Host() {
    }

    public Host(String name, String ip, int category_id, String img_path) {
        this.name = name;
        this.ip = ip;
        this.category_id = category_id;
        this.img_path = "/resources/img/" + img_path;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
