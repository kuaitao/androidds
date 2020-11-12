package com.bashapplication.mine.bean;

public class AddressBean {


    private String name;
    private String phone;
    private String addres;
    private String flag;

    public AddressBean(String name, String phone, String addres, String flag) {
        this.name = name;
        this.phone = phone;
        this.addres = addres;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
