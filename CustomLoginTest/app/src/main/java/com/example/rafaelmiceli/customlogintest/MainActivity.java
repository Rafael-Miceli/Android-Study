package com.example.rafaelmiceli.customlogintest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button new_button;
    protected AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_button = (Button)findViewById(R.id.button);
        new_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Me Clicou", Toast.LENGTH_LONG).show();

//        if (mTxtPassword.getText().toString().equals("") ||
//                mTxtUsername.getText().toString().equals("")) {
//            //We're just logging this here, we should show something to the user
//            Log.w(TAG, "Username or password not entered");
//            return;
//        }
//        mAuthService.login(mTxtUsername.getText().toString(), mTxtPassword.getText().toString(), new TableJsonOperationCallback() {
//            @Override
//            public void onCompleted(JsonObject jsonObject, Exception exception,
//                                    ServiceFilterResponse response) {
//                if (exception == null) {
//                    //If they've registered successfully, we'll save and set the userdata and then
//                    //show the logged in activity
//                    mAuthService.setUserAndSaveData(jsonObject);
//                    Intent loggedInIntent = new Intent(getApplicationContext(), LoggedIn.class);
//                    startActivity(loggedInIntent);
//                } else {
//                    Log.e(TAG, "Error loggin in: " + exception.getMessage());
//                }
//            }
//        });
    }
}
