package com.master.architectureexample.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.master.architectureexample.localdb.User;
import com.master.architectureexample.localdb.UserDao;
import com.master.architectureexample.localdb.UserDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();

    }

    public void insertUser(User user) {

        new UserInsertAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UserUpdateAsyncTask(userDao).execute(user);

    }

    public void deleteUser(User user) {

        new UserDeleteAsyncTask(userDao).execute(user);

    }

    public void deleteAllUser() {

        new DeleteAllUsersAsyncTask(userDao).execute();

    }

    //for this live data no need to write async task as livedata works in background thread
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }


    private static class UserInsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public UserInsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.insert(users[0]);
            return null;
        }
    }

    private static class UserUpdateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public UserUpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.update(users[0]);
            return null;
        }
    }

    private static class UserDeleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public UserDeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        public DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.userDao.deleteAllUserData();
            return null;
        }
    }

}
