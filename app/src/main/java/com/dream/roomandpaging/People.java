package com.dream.roomandpaging;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "xx_people")
public class People {
    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    @ColumnInfo(name = "first_name")
    String name;

    @Ignore
    String noUse;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getNoUse() {
        return noUse;
    }

    public void setNoUse(String noUse) {
        this.noUse = noUse;
    }
}
