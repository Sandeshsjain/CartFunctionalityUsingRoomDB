package com.example.sampleguipracticeproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 4)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDeo myDeo();
}
