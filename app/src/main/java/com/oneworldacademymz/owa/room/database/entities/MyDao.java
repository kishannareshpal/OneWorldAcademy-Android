package com.oneworldacademymz.owa.room.database.entities;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.oneworldacademymz.owa.room.database.entities.entities.Mensalidade;
import com.oneworldacademymz.owa.room.database.entities.entities.User;

import java.util.List;

@Dao
public interface MyDao {


// USERS Methods
    @Insert()
    void addUser(User user); // to add users to the "users" entity/table

    @Update
    void updateUser(User user); // to update the values inside "users" entity/table


    @Query("SELECT * FROM users")
    List<User> getUsers(); // to get all of the logged in users from "users" entity/table


    @Query("SELECT id FROM users LIMIT 1")
    int getAutoId_User(); // for 1 student only logged in

    @Query("SELECT id FROM users WHERE username = :username LIMIT 1")
    int getIdFromUsername_User(String username); // self explanatory


    @Query("SELECT id FROM users as u WHERE u.first_name || ' ' || u.last_name = :fullname LIMIT 1")
    int getIdFromFullName_User(String fullname); // self explanatory

    @Query("SELECT COUNT(id) FROM users")
    int getNumberOfRows_User();  // self explanatory




// MENSALIDADES Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMensalidade(Mensalidade mnsalidade); // add mensalidades or update if record exists




}
