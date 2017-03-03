package com.lan.model;

public class Power {
    private Integer id;

    private String name;

    private String danwei;

    private String chushi;

    private String yiju;

    private String neirong;

    private String liucheng;

    private String zhuyi;

    public Power(){}

    public Power( String name, String danwei, String chushi, String yiju, String neirong, String liucheng, String zhuyi) {
        this.name = name;
        this.danwei = danwei;
        this.chushi = chushi;
        this.yiju = yiju;
        this.neirong = neirong;
        this.liucheng = liucheng;
        this.zhuyi = zhuyi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei == null ? null : danwei.trim();
    }

    public String getChushi() {
        return chushi;
    }

    public void setChushi(String chushi) {
        this.chushi = chushi == null ? null : chushi.trim();
    }

    public String getYiju() {
        return yiju;
    }

    public void setYiju(String yiju) {
        this.yiju = yiju == null ? null : yiju.trim();
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong == null ? null : neirong.trim();
    }

    public String getLiucheng() {
        return liucheng;
    }

    public void setLiucheng(String liucheng) {
        this.liucheng = liucheng == null ? null : liucheng.trim();
    }

    public String getZhuyi() {
        return zhuyi;
    }

    public void setZhuyi(String zhuyi) {
        this.zhuyi = zhuyi == null ? null : zhuyi.trim();
    }
}