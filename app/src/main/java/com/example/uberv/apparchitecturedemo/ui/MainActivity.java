package com.example.uberv.apparchitecturedemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.uberv.apparchitecturedemo.App;
import com.example.uberv.apparchitecturedemo.R;
import com.example.uberv.apparchitecturedemo.data.api.GithubApiService;
import com.example.uberv.apparchitecturedemo.data.api.Resource;
import com.example.uberv.apparchitecturedemo.data.models.User;
import com.example.uberv.apparchitecturedemo.data.repository.UserRepository;
import com.example.uberv.apparchitecturedemo.profile.UserProfileViewModel;

import javax.inject.Inject;

import timber.log.Timber;

// More info: https://developer.android.com/topic/libraries/architecture/guide.html

public class MainActivity extends AppCompatActivity {

    @Inject
    GithubApiService mApiService;
    @Inject
    UserRepository mUserRepository;

    private UserProfileViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getNetComponent().inject(this);
        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        mViewModel.init("Rikharthu");

        mViewModel.getUser().observe(this, user -> {
            // Update UI
            // This will only be called between onStart() and onStop() (lifecycle-aware)
            Timber.d("User updated: " + user);
        });


//        Resource<User> userResource = Resource.loading(new User());

        Timber.d("A");
    }
}
