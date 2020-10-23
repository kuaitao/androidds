package com.bashapplication.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleGroupBean implements Parcelable {

        private String desc;
        private String icon;
        private String id;

    @Override
    public String toString() {
        return "ArticleGroupBean{" +
                "desc='" + desc + '\'' +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String name;
        private String state;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.desc);
        dest.writeString(this.icon);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.state);
    }

    public ArticleGroupBean() {
    }

    protected ArticleGroupBean(Parcel in) {
        this.desc = in.readString();
        this.icon = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<ArticleGroupBean> CREATOR = new Parcelable.Creator<ArticleGroupBean>() {
        @Override
        public ArticleGroupBean createFromParcel(Parcel source) {
            return new ArticleGroupBean(source);
        }

        @Override
        public ArticleGroupBean[] newArray(int size) {
            return new ArticleGroupBean[size];
        }
    };
}
