package com.example.roomretrofit;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post",indices = @Index(value = {"id"},unique = true))
public class Post {

    @NonNull
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    private Integer userId;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;


    @SerializedName("body")
    @ColumnInfo(name = "body")
    private String body;

    public Post(@NonNull Integer userId, @NonNull Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
