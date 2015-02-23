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
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";

    private Button new_button;
    private Button btnCadastrar;
    protected AuthService mAuthService;

    private EditText mTxtUsername;
    private EditText mTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthService = new AuthService(this);

        mTxtUsername = (EditText) findViewById(R.id.editTextLogin);
        mTxtPassword = (EditText) findViewById(R.id.editTextPassword);

        new_button = (Button)findViewById(R.id.button);
        new_button.setOnClickListener(this);
        btnCadastrar = (Button)findViewById(R.id.btnIrParaCadastro);
        btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button)
            loginClick(v);
        if (v.getId() == R.id.btnIrParaCadastro)
            registerClick(v);
    }

    public void loginClick(View v){
        Toast.makeText(this, "Me Clicou", Toast.LENGTH_LONG).show();

        if (mTxtPassword.getText().toString().equals("") ||
                mTxtUsername.getText().toString().equals("")) {
            //We're just logging this here, we should show something to the user
            Log.w(TAG, "Username or password not entered");
            return;
        }
        mAuthService.login(mTxtUsername.getText().toString(), mTxtPassword.getText().toString(), new TableJsonOperationCallback() {
            @Override
            public void onCompleted(JsonObject jsonObject, Exception exception,
                                    ServiceFilterResponse response) {
                if (exception == null) {
                    //If they've registered successfully, we'll save and set the userdata and then
                    //show the logged in activity
                    mAuthService.setUserAndSaveData(jsonObject);
                    Intent loggedInIntent = new Intent(getApplicationContext(), LoggedIn.class);
                    startActivity(loggedInIntent);
                } else {
                    //Erro ocorrendo neste momento

                    Log.e(TAG, "Error loggin in: " + exception.getMessage());
                }
            }
        });
    }

    public void registerClick(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterAccountActivity.class);
        startActivity(intent);
    }
}
