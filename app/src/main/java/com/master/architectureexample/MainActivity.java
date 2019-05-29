package com.master.architectureexample;

import android.os.Bundle;
import android.util.Log;

import com.master.architectureexample.adapters.UsersDataRecyclerViewAdapter;
import com.master.architectureexample.localdb.User;
import com.master.architectureexample.vmodels.UserViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate:","onCreate()");


        RecyclerView recyclerView=findViewById(R.id.usersRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        UsersDataRecyclerViewAdapter usersDataRecyclerViewAdapter=new UsersDataRecyclerViewAdapter();
        recyclerView.setAdapter(usersDataRecyclerViewAdapter);


        if (userViewModel==null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
            userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {

                    usersDataRecyclerViewAdapter.setUsersData(users);
                    usersDataRecyclerViewAdapter.notifyDataSetChanged();
                    for (User user : users) {
                        Log.d("Name:", String.valueOf(user.getName()));
                    }

                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy:", "onDestroy");

    }
}
