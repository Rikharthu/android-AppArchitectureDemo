package com.example.uberv.apparchitecturedemo.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = "username", unique = true)})
public class User {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    private long mId;

    @ColumnInfo(name = "username")
    @SerializedName("login")
    private String mUsername;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    private String mType;

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    private String mReposUrl;

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    private String mAvatarUrl;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public void setReposUrl(String reposUrl) {
        mReposUrl = reposUrl;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
