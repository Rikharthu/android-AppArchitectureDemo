package com.example.uberv.apparchitecturedemo.di.components;

import com.example.uberv.apparchitecturedemo.di.modules.AppModule;
import com.example.uberv.apparchitecturedemo.di.modules.NetModule;
import com.example.uberv.apparchitecturedemo.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
