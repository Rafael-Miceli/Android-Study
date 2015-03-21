package com.unitteststudy.rafael.unitteststudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;


public class MainActivity extends ActionBarActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        try {
            MobileServiceClient mobileServiceClient =  new MobileServiceClient("https://arduinoapp.azure-mobile.net/", "QkTMsFHSEaNGuiKVsywYYHpHnIHMUB64", this);
            WrappedMobileServiceJsonTable wrappedMobileServiceJsonTable = new WrappedMobileServiceJsonTable(mobileServiceClient.getTable("accounts"));
            AccountInsertCallbackHandler accountInsertCallbackHandler = new AccountInsertCallbackHandler(context, new Intent("Main"));
            UserAzureRepository userAzureRepository = new UserAzureRepository(wrappedMobileServiceJsonTable, accountInsertCallbackHandler);
            UserService userService = new UserService(userAzureRepository);
            userService.login("rafael.miceli@hotmail.com", "12345678");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void showInScreen(User user)
    {
        if (user != null)
            Toast.makeText(this, user.client.Name, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Not registered", Toast.LENGTH_LONG).show();
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
