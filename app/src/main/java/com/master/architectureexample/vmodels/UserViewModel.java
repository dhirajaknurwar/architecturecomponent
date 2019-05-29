package com.master.architectureexample.vmodels;

import android.app.Application;

import com.master.architectureexample.localdb.User;
import com.master.architectureexample.repo.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsersData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        allUsersData = userRepository.getAllUsers();

    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }


    public void deleteAllUsers() {
        userRepository.deleteAllUser();
    }

    public LiveData<List<User>> getAllUsers() {
        allUsersData = userRepository.getAllUsers();
        return allUsersData;
    }
}
