package com.unitteststudy.rafael.unitteststudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActionBarActivity {

    private Context context;

    @Inject
    UserService userService;

    TankPageAdapter mTankPageAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        userService.login("mjr@mjr.com", "12345678");
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<Tank> tanks = intent.getParcelableArrayListExtra("tanks");

            mTankPageAdapter = new TankPageAdapter(getSupportFragmentManager(), context, tanks);

            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPager.setAdapter(mTankPageAdapter);
        }
    };

    @Override
    public void onResume(){
        super.onResume();
        context.registerReceiver(messageReceiver, new IntentFilter("Main"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        context.unregisterReceiver(messageReceiver);
    }

}
