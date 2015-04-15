package com.unitteststudy.rafael.unitteststudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends BaseActionBarActivity {

    private Context context;

    @Inject
    UserService userService;

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

            String clienName = intent.getStringExtra("clientName");

            if (clienName != null)
                Toast.makeText(context, clienName, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Not registered", Toast.LENGTH_LONG).show();
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
