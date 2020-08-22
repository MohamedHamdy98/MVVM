package com.roomDatabase.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.roomDatabase.dao.PostsDao;
import com.roomDatabase.models.Posts;

@Database(entities = Posts.class, version = 1)
public abstract class PostsDatabase extends RoomDatabase {
    private static PostsDatabase INSTANCE;

    public abstract PostsDao postsDao();

    public static PostsDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (PostsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostsDatabase.class, "posts_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
