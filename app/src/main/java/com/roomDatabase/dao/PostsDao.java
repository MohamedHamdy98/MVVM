package com.roomDatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.roomDatabase.models.Posts;

import java.util.List;

@Dao
public interface PostsDao {
    @Insert
    void insertData(Posts posts);

    @Query("select * from table_Posts")
    List<Posts> getPosts();
}
