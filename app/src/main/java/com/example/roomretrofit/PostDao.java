package com.example.roomretrofit;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(List<Post> posts);

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getPosts();
}
