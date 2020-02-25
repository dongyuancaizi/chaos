package com.example.mvvm3.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvm3.model.User;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:19
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)// cache need update
    Long add(User user);

    @Query("select * from user where name = :username")
    User queryByUsername(String username);

    @Query("select * from user where name = :username")
    LiveData<User> queryByUsernameSync(String username);
}
