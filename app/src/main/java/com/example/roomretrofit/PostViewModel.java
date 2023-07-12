package com.example.roomretrofit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private Repository repository;

    public LiveData<List<Post>> allPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allPosts = repository.getAllPosts();
    }

    public void insert(List<Post> posts){
        repository.insert(posts);
    }

    public LiveData<List<Post>> getAllPosts(){
        return allPosts;
    }
}
