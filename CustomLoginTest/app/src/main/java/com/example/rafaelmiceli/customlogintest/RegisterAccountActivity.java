package com.example.rafaelmiceli.customlogintest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;


public class RegisterAccountActivity extends ActionBarActivity implements View.OnClickListener {


    private final String TAG = "RegisterAccountActivity";

    private EditText editTextLoginCadastro;
    private EditText editTextPasswordCadastro;
    private AuthService mAuthService;
    private Button register_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        mAuthService = new AuthService(this);

        editTextLoginCadastro = (EditText) findViewById(R.id.editTextLoginCadastro);
        editTextPasswordCadastro = (EditText) findViewById(R.id.editTextPasswordCadastro);

        register_button = (Button)findViewById(R.id.btnCadastrar);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

            //We're just logging the validation errors, we should be showing something to the user
            if (editTextLoginCadastro.getText().toString().equals("") ||
                    editTextPasswordCadastro.getText().toString().equals("")) {
                Log.w(TAG, "You must enter all fields to register");
                return;
            }
            else {
                //mAuthService = myApp.getAuthService();
                mAuthService.registerUser(editTextLoginCadastro.getText().toString(),
                        editTextPasswordCadastro.getText().toString(),
                        new TableJsonOperationCallback() {
                            @Override
                            public void onCompleted(JsonObject jsonObject, Exception exception,
                                                    ServiceFilterResponse response) {
                                if (exception == null) {
                                    //If that was successful, set and save the user data
                                    mAuthService.setUserAndSaveData(jsonObject);
                                    //Finish this activity and run the logged in activity
                                    //mActivity.finish();
                                    Intent loggedInIntent = new Intent(getApplicationContext(), LoggedIn.class);
                                    startActivity(loggedInIntent);
                                } else {
                                    Log.e(TAG, "There was an error registering the user: " + exception.getMessage());
                                }
                            }
                        });
            }

    }
}
