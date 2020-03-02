package com.himoyi.img_api.domain;

public class Img {
    private String path;
    private String name;
    private String md5;

    public Img() {
    }

    public Img(String path, String name, String md5) {
        this.path = path;
        this.name = name;
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
