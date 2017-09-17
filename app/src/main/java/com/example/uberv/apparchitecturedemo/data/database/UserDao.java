package com.example.uberv.apparchitecturedemo.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.uberv.apparchitecturedemo.data.models.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(long userId);
    // by returning LiveData, Room knows when the database is modified and it will automatically
    // notify all active observers when the data changes

    @Query("SELECT * FROM user WHERE username = :username")
    LiveData<User> load(String username);
}
