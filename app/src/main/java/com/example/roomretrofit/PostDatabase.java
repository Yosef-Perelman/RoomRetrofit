package com.example.roomretrofit;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {

    public abstract PostDao postDao();

    public static PostDatabase instance;

    public static PostDatabase getInstance(Context context){
        if (instance == null){
            synchronized (PostDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context,
                            PostDatabase.class, "post")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
