package com.unitteststudy.rafael.unitteststudy;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Rafael on 21/03/2015.
 */
public class BaseActionBarActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SenaApp senaApp = (SenaApp)getApplication();

        senaApp.getObjectGraph().inject(this);
    }
}
