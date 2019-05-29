package com.master.architectureexample.localdb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()//delete db every time
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new insertInitialUserDataAsyncTask(instance).execute();
        }
    };

    private static class insertInitialUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        public insertInitialUserDataAsyncTask(UserDatabase db) {
            this.userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("dhiraj", 33, "dhiraj@gmail.com", "Miyapur"));
            userDao.insert(new User("smita", 31, "smita@gmail.com", "Miyapur"));
            userDao.insert(new User("saanu", 4, "saanu@gmail.com", "Miyapur"));
            return null;
        }
    }
}
