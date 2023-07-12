package com.example.roomretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.post_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.txtTitle.setText(String.valueOf(posts.get(position).getTitle()));
        holder.txtBody.setText(new StringBuilder(posts.get(position).getBody()
                .substring(0, 20)).append("...").toString());
        holder.txtUserId.setText(String.valueOf(posts.get(position).getUserId()));
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle, txtBody, txtUserId;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtUserId = itemView.findViewById(R.id.txtUserId);
        }
    }
}
