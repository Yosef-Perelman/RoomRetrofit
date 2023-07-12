package com.example.roomretrofit;

import io.reactivex.Observable;

import java.util.List;

import retrofit2.http.GET;

public interface apiService {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
