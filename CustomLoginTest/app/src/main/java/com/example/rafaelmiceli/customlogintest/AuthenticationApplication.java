package com.example.rafaelmiceli.customlogintest;

import android.app.Activity;
import android.app.Application;

/**
 * Created by rafael.miceli on 26/01/2015.
 */
public class AuthenticationApplication extends Application {
    private AuthService mAuthService;
    private Activity mCurrentActivity;

    public AuthenticationApplication() {}

    public AuthService getAuthService() {
        if (mAuthService == null) {
            mAuthService = new AuthService(this);
        }
        return mAuthService;
    }

    public void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }
}
