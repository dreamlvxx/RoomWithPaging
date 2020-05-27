package com.dream.roomandpaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recy;
    private PeopleAdapter peopleAdapter;

    private PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recy = findViewById(R.id.recy);

        peopleAdapter = new PeopleAdapter(new DiffUtil.ItemCallback<People>() {
            @Override
            public boolean areItemsTheSame(@NonNull People oldItem, @NonNull People newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull People oldItem, @NonNull People newItem) {
                return oldItem.name.equals(newItem.name);
            }
        });

        recy.setAdapter(peopleAdapter);
        recy.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);
        peopleViewModel.listLiveData.observe(this, new Observer<PagedList<People>>() {
            @Override
            public void onChanged(PagedList<People> people) {
                for (People p :
                        people) {
                    if (null != p) {
                        Log.e("xxx", "onChanged: name = " + p.getName());
                    }
                }
                peopleAdapter.submitList(people);
            }
        });

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People p = new People();
                p.name = "ss" + new Random().nextInt();
                peopleViewModel.inertPeople(p);
            }
        });

        findViewById(R.id.tv_get_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PagedList<People> data = peopleViewModel.getData().getValue();
                for (People p :
                        data) {
                    Log.e("xxx", "onClick: getPeople id = " + p.getId() + "name = " + p.getName());
                }
            }
        });

    }
}
