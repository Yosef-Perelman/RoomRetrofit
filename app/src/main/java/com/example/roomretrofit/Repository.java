package com.example.roomretrofit;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class Repository {

    public PostDao postDao;

    public LiveData<List<Post>> allPosts;

    private PostDatabase database;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Repository(Application application){
        database = PostDatabase.getInstance(application);
        postDao = database.postDao();
        allPosts = postDao.getPosts();
    }

    public void insert(List<Post> posts){
        new Thread(() -> {
            postDao.insert(posts);
        }).start();
    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }
}
