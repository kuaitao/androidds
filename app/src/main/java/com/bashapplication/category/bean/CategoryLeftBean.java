package com.bashapplication.category.bean;

import java.util.List;

public class CategoryLeftBean {
    private String id = "";
    private String name = "";
    private String icon = "";
    private boolean checked = false;
    private List<CategoryLeftBean> sub;

    public List<CategoryLeftBean> getSub() {
        return sub;
    }

    public void setSub(List<CategoryLeftBean> sub) {
        this.sub = sub;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /*public class SubBean extends CategoryLeftBean{

    }*/
}
