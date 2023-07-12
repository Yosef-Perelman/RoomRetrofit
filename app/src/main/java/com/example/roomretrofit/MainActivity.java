package com.example.roomretrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private PostViewModel viewModel;
    private List<Post> allPosts;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private Repository repository;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository(getApplication());
        allPosts = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerPostList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        postAdapter = new PostAdapter(this, allPosts);
        makeRequest();

        viewModel.getAllPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                recyclerView.setAdapter(postAdapter);
                postAdapter.setPosts(posts);
                Log.d("main", "onChanged: " + posts);
            }
        });
    }

    private void makeRequest() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient(BASE_URL);
        apiService api = retrofit.create(apiService.class);
        compositeDisposable.add(api.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postModels -> {
                    if (postModels != null) {
                        repository.insert(postModels);
                    }
                }));
    }
}
