package com.bashapplication.home.bean;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;
import java.util.Objects;

public class ForumListBean implements Parcelable {

    private String nickname;
    private String avatar;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUnRead() {
        return unRead;
    }

    public void setUnRead(int unRead) {
        this.unRead = unRead;
    }

    private int unRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumListBean that = (ForumListBean) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    private String chatType;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    private List<ForumListBean> article;

    public List<ForumListBean> getArticle() {
        return article;
    }

    public List<String> getImages() {
        if(images == null)
            return null;
        if(images.size() == 0)
            return null;
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images;

    public void setArticle(List<ForumListBean> article) {
        this.article = article;
    }
    private List<ForumListBean> list;
    private int total;
    private String msgTime;
    private boolean isMsgRead;

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    private GroupBean group;

    private int unReadCount;

    public int getShowOpenVip() {
        return showOpenVip;
    }

    public void setShowOpenVip(int showOpenVip) {
        this.showOpenVip = showOpenVip;
    }

    private int showOpenVip;

    public boolean isMsgRead() {
        return isMsgRead;
    }

    public void setMsgRead(boolean msgRead) {
        isMsgRead = msgRead;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    private Author author;
    private String comments;
    private String content;
    private String cover;
    private String id;
    private String isPlatform;
    private String permission;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    private String praises;
    private long publishTime;

    public String getShareUrl() {
        return shareUrl;
    }

    @Override
    public String toString() {
        return "ForumListBean{" +
                ", chatType='" + chatType + '\'' +
                ", article=" + article +
                ", images=" + images +
                ", list=" + list +
                ", total=" + total +
                ", msgTime='" + msgTime + '\'' +
                ", isMsgRead=" + isMsgRead +
                ", group=" + group +
                ", author=" + author +
                ", comments='" + comments + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", id='" + id + '\'' +
                ", isPlatform='" + isPlatform + '\'' +
                ", permission='" + permission + '\'' +
                ", praises='" + praises + '\'' +
                ", publishTime=" + publishTime +
                ", rewardable='" + rewardable + '\'' +
                ", shares='" + shares + '\'' +
                ", authorName='" + authorName + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", digest='" + digest + '\'' +
                ", articlePraises=" + articlePraises +
                ", temp=" + temp +
                ", hasCollect=" + hasCollect +
                ", isAuthor=" + isAuthor +
                ", isEditable=" + isEditable +
                ", location='" + location + '\'' +
                ", media=" + media +
                ", hasPraise=" + hasPraise +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", views='" + views + '\'' +
                ", isInDiscover=" + isInDiscover +
                ", isPublic=" + isPublic +
                ", state='" + state + '\'' +
                '}';
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    private String rewardable;
    private String shares;
    private String authorName;
    private String shareUrl;

    public int getArticlePraises() {
        return articlePraises;
    }

    public void setArticlePraises(int articlePraises) {
        this.articlePraises = articlePraises;
    }

    private String digest;
    private int articlePraises;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public int getHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(int hasCollect) {
        this.hasCollect = hasCollect;
    }

    public int getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(int isAuthor) {
        this.isAuthor = isAuthor;
    }

    public int getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(int isEditable) {
        this.isEditable = isEditable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MediaBean getMedia() {
        return media;
    }

    public void setMedia(MediaBean media) {
        this.media = media;
    }

    public int temp;

    private int hasCollect;
    private int isAuthor;

    public int getHasPraise() {
        return hasPraise;
    }

    public void setHasPraise(int hasPraise) {
        this.hasPraise = hasPraise;
    }

    private int isEditable;
    private String location;
    private MediaBean media;
    private int hasPraise;

    private String title;

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getIsInDiscover() {
        return isInDiscover;
    }

    public void setIsInDiscover(int isInDiscover) {
        this.isInDiscover = isInDiscover;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String type ;
    private String views;

    private int isInDiscover;
    private int isPublic;
    private String state;

    public List<ForumListBean> getList() {
        return list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setList(List<ForumListBean> list) {
        this.list = list;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(String isPlatform) {
        this.isPlatform = isPlatform;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPraises() {
        return praises;
    }

    public void setPraises(String praises) {
        this.praises = praises;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getRewardable() {
        return rewardable;
    }

    public void setRewardable(String rewardable) {
        this.rewardable = rewardable;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public static class Author implements Parcelable {
        private String avatar;
        private int hasFollow;

        @Override
        public String toString() {
            return "Author{" +
                    "avatar='" + avatar + '\'' +
                    ", hasFollow=" + hasFollow +
                    ", id='" + id + '\'' +
                    ", isIntelligentPerson='" + isIntelligentPerson + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        private String id;
        private String isIntelligentPerson;
        private String name;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getHasFollow() {
            return hasFollow;
        }

        public void setHasFollow(int hasFollow) {
            this.hasFollow = hasFollow;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsIntelligentPerson() {
            return isIntelligentPerson;
        }

        public void setIsIntelligentPerson(String isIntelligentPerson) {
            this.isIntelligentPerson = isIntelligentPerson;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.avatar);
            dest.writeInt(this.hasFollow);
            dest.writeString(this.id);
            dest.writeString(this.isIntelligentPerson);
            dest.writeString(this.name);
        }

        public Author() {
        }

        protected Author(Parcel in) {
            this.avatar = in.readString();
            this.hasFollow = in.readInt();
            this.id = in.readString();
            this.isIntelligentPerson = in.readString();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
            @Override
            public Author createFromParcel(Parcel source) {
                return new Author(source);
            }

            @Override
            public Author[] newArray(int size) {
                return new Author[size];
            }
        };
    }

    public ForumListBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.article);
        dest.writeTypedList(this.list);
        dest.writeInt(this.total);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.comments);
        dest.writeString(this.content);
        dest.writeString(this.cover);
        dest.writeString(this.id);
        dest.writeString(this.isPlatform);
        dest.writeString(this.permission);
        dest.writeString(this.praises);
        dest.writeLong(this.publishTime);
        dest.writeString(this.rewardable);
        dest.writeString(this.shares);
        dest.writeString(this.authorName);
        dest.writeString(this.digest);
        dest.writeInt(this.temp);
        dest.writeParcelable(this.group, flags);
        dest.writeInt(this.hasCollect);
        dest.writeInt(this.isAuthor);
        dest.writeInt(this.isEditable);
        dest.writeString(this.location);
        dest.writeParcelable(this.media, flags);
        dest.writeInt(this.hasPraise);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.views);
        dest.writeInt(this.isInDiscover);
        dest.writeInt(this.isPublic);
        dest.writeString(this.state);
        dest.writeString(this.chatType);
        dest.writeString(this.shareUrl);
        dest.writeInt(this.unReadCount);
        dest.writeInt(this.unRead);
        dest.writeString(nickname);
    }

    protected ForumListBean(Parcel in) {
        this.article = in.createTypedArrayList(ForumListBean.CREATOR);
        this.list = in.createTypedArrayList(ForumListBean.CREATOR);
        this.total = in.readInt();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.comments = in.readString();
        this.content = in.readString();
        this.cover = in.readString();
        this.id = in.readString();
        this.isPlatform = in.readString();
        this.permission = in.readString();
        this.praises = in.readString();
        this.publishTime = in.readLong();
        this.rewardable = in.readString();
        this.shares = in.readString();
        this.authorName = in.readString();
        this.digest = in.readString();
        this.temp = in.readInt();
        this.group = in.readParcelable(GroupBean.class.getClassLoader());
        this.hasCollect = in.readInt();
        this.isAuthor = in.readInt();
        this.isEditable = in.readInt();
        this.location = in.readString();
        this.media = in.readParcelable(MediaBean.class.getClassLoader());
        this.hasPraise = in.readInt();
        this.title = in.readString();
        this.type = in.readString();
        this.views = in.readString();
        this.isInDiscover = in.readInt();
        this.isPublic = in.readInt();
        this.state = in.readString();
        this.chatType = in.readString();
        this.shareUrl = in.readString();
        this.unReadCount = in.readInt();
        this.unRead = in.readInt();
        this.nickname = in.readString();

    }

    public static final Creator<ForumListBean> CREATOR = new Creator<ForumListBean>() {
        @Override
        public ForumListBean createFromParcel(Parcel source) {
            return new ForumListBean(source);
        }

        @Override
        public ForumListBean[] newArray(int size) {
            return new ForumListBean[size];
        }
    };

}
