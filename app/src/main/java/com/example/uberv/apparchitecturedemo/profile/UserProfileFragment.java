package com.example.uberv.apparchitecturedemo.profile;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberv.apparchitecturedemo.R;

public class UserProfileFragment extends LifecycleFragment {

    public static final String KEY_UUID = "uuid";

    private UserProfileViewModel mViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String userId = getArguments().getString(KEY_UUID);
        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        mViewModel.init(userId);

        mViewModel.getUser().observe(this, user -> {
            // Update UI
            // This will only be called between onStart() and onStop() (lifecycle-aware)
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile, container, false);
    }
}
