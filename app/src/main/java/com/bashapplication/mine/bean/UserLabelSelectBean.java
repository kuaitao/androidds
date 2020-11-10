package com.bashapplication.mine.bean;

public class UserLabelSelectBean {

    private String nameStr;
    public boolean checked;

    public UserLabelSelectBean(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
