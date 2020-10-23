package com.bashapplication.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaBean implements Parcelable {

    private String id;
    private String duration;

    private String size;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;


    @Override
    public String toString() {
        return "MediaBean{" +
                "id='" + id + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
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


    private String name;


    public MediaBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.duration);
        dest.writeString(this.size);
        dest.writeString(this.url);
        dest.writeString(this.name);

    }

    protected MediaBean(Parcel in) {
        this.id = in.readString();
        this.duration = in.readString();
        this.size = in.readString();
        this.url = in.readString();
        this.name = in.readString();

    }

    public static final Creator<MediaBean> CREATOR = new Creator<MediaBean>() {
        @Override
        public MediaBean createFromParcel(Parcel source) {
            return new MediaBean(source);
        }

        @Override
        public MediaBean[] newArray(int size) {
            return new MediaBean[size];
        }
    };
}
