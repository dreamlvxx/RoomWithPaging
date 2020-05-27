package com.dream.roomandpaging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PeopleDao {

    @Query("SELECT * FROM xx_people")
    DataSource.Factory<Integer, People> getAllPeople();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(People people);

    @Delete
    void delete(People people);

}
