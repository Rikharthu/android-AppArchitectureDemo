package com.example.uberv.apparchitecturedemo.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.uberv.apparchitecturedemo.data.models.User;
import com.example.uberv.apparchitecturedemo.data.repository.UserRepository;

import javax.inject.Inject;

/*
A ViewModel provides the data for a specific UI component, such as a fragment or activity,
and handles the communication with the business part of data handling, such as calling
other components to load the data or forwarding user modifications. The ViewModel does
not know about the View and is not affected by configuration changes such as recreating
an activity due to rotation.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> mUser;
    private UserRepository mUserRepo;

    @Inject
    public UserProfileViewModel(UserRepository mUserRepo) {
        this.mUserRepo = mUserRepo;
    }

    public void init(String userId) {
        if (mUser != null) {
            // ViewModel is created per fragment so
            // we know that userId won't change
            return;
        }
        mUser = mUserRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return mUser;
    }
}
