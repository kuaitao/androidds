package com.bashapplication.home.adapter;

public class TestBean1 {

    private String name;
    private String des;
    private int  pic;

    public TestBean1(String name, String des, int pic) {
        this.name = name;
        this.des = des;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
