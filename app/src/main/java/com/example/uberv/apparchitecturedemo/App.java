package com.example.uberv.apparchitecturedemo;

import android.app.Application;

import com.example.uberv.apparchitecturedemo.di.components.DaggerNetComponent;
import com.example.uberv.apparchitecturedemo.di.components.NetComponent;
import com.example.uberv.apparchitecturedemo.di.modules.AppModule;
import com.example.uberv.apparchitecturedemo.di.modules.NetModule;
import com.example.uberv.apparchitecturedemo.utils.DevelopmentTree;

import timber.log.Timber;

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new DevelopmentTree());

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
