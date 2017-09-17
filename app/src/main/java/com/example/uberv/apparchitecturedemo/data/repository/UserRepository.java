package com.example.uberv.apparchitecturedemo.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.uberv.apparchitecturedemo.data.api.GithubApiService;
import com.example.uberv.apparchitecturedemo.data.api.NetworkBoundResource;
import com.example.uberv.apparchitecturedemo.data.api.Resource;
import com.example.uberv.apparchitecturedemo.data.database.UserDao;
import com.example.uberv.apparchitecturedemo.data.models.User;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class UserRepository {

    private final GithubApiService mApiService;
    private final UserDao mUserDao;
    private final Executor mExecutor;
    // simple in-memory cache
    private UserCache mUserCache;

    @Inject
    public UserRepository(GithubApiService apiService, UserDao userDao, Executor executor) {
        mApiService = apiService;
        mUserDao = userDao;
        mExecutor = executor;
    }

    public LiveData<User> getUser(String username) {
        refreshUser(username);
        // return a LiveData directly from the Database
        return mUserDao.load(username);
    }

    private void refreshUser(String username) {
        mExecutor.execute(() -> {
            // running in a background thread
            // check if user was fetched recently
//            boolean userExists = mUserDao.hasUser(FRESH_TIMEOUT); // TODO
            boolean userExists = false;
            if (!userExists) {
                // refresh the data
                Response<User> response = null;
                try {
                    response = mApiService.getUser(username).execute();
                } catch (IOException e) {
                    Timber.e(e);
                }
                // TODO check for error etc.
                // Update the database.The LiveData will automatically refresh so
                // we don't need to do anything else here besides updating the database
                mUserDao.save(response.body());
            }
        });
    }

    public LiveData<Resource<User>> loadUser(final String userId) {
        return new NetworkBoundResource<User, User>() {
            @Override
            protected void saveCallResult(@NonNull User item) {
                mUserDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
//                return rateLimiter.canFetch(userId) && (data == null || !isFresh(data));
                return true;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return mUserDao.load(userId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<User>> createCall() {
                return mApiService.getUser(userId);
            }
        }.getAsLiveData();
    }

    /*
    public LiveData<User> getUser(String username) {
        LiveData<User> cached = mUserCache.get(username);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<User> userData = new MutableLiveData<>();
        mApiService.getUser(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Timber.e(t, "Get user " + username + " request failed");
            }
        });
        return userData;
    }
    */
}
