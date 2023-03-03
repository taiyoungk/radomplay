package com.taeyoung.randomplay;

public class BearItem {
    /* 아이템의 정보를 담기 위한 클래스 */

    int num;
    int nam;
    String name;
    int resId;

    public BearItem(int nam, int num, String name, int resId) {
        this.num = num;
        this.nam = nam;
        this.name = name;
        this.resId = resId;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getNam() {
        return nam;
    }
    public void setNam(int nam) {
        this.nam = nam;
    }
    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }

}
