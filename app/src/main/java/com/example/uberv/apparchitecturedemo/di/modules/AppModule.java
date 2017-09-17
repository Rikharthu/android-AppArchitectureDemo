package com.example.uberv.apparchitecturedemo.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.uberv.apparchitecturedemo.GithubViewModelFactory;
import com.example.uberv.apparchitecturedemo.data.database.UserDao;
import com.example.uberv.apparchitecturedemo.data.database.UserDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Named("databaseName")
    String provideDatabaseName() {
        return "app-architecture-db";
    }

    @Provides
    UserDao provideUserDao(UserDatabase database) {
        return database.userDao();
    }

    @Provides
    Executor provideExecutor() {
        final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60l,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>()
        );
    }

    @Provides
    @Singleton
    UserDatabase provideUserDatabase(@Named("databaseName") String databaseName) {
        UserDatabase database = Room.databaseBuilder(mApplication, UserDatabase.class, databaseName).build();
        return database;
    }
}
