package com.dream.roomandpaging;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {People.class}, version = 1)
public abstract class PeopleDB extends RoomDatabase {

    private static PeopleDB INSTANCE;

    public static PeopleDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PeopleDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PeopleDB.class, "people.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new PopulateDbAsync(INSTANCE).execute();
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PeopleDao mDao;

        PopulateDbAsync(PeopleDB db) {
            mDao = db.getPeopleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<People> list = new ArrayList<>();
            // 假数据
            for (String name : data) {
                People p = new People();
                p.name = name;
                list.add(p);
                mDao.insert(p);
            }
            return null;
        }
    }


    public static final String[] data = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis"};


    public abstract PeopleDao getPeopleDao();

}
