package com.example.mvvm3.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author Jian.cui
 * @date 2019/11/3 18:50
 */
@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    public User() {
    }

    @Ignore
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
