package com.dream.roomandpaging;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executors;

public class PeopleViewModel extends AndroidViewModel {

    final LiveData<PagedList<People>> listLiveData;
    private PeopleDao dao;

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        dao = PeopleDB.getInstance(application).getPeopleDao();
        listLiveData = new LivePagedListBuilder<>(dao.getAllPeople(),
                new PagedList.Config.Builder()
                        .setPageSize(10)
                        .setEnablePlaceholders(true)
                        .build())
                .build();

    }

    public LiveData<PagedList<People>> getData(){
        return listLiveData;
    }

    public void inertPeople(final People p){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("xxx", "run: inertPeople" );
                dao.insert(p);
            }
        });
    }

}
