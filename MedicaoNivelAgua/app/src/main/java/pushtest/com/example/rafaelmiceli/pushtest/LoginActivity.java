package pushtest.com.example.rafaelmiceli.pushtest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;

public class LoginActivity extends Activity implements View.OnClickListener {

    private final String TAG = "MainActivity";

    private Button login_button;
    protected AuthService mAuthService;

    private EditText mTxtUsername;
    private EditText mTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuthService = new AuthService(this);

        mTxtUsername = (EditText) findViewById(R.id.editTextLogin);
        mTxtPassword = (EditText) findViewById(R.id.editTextPassword);

        login_button = (Button)findViewById(R.id.button);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loginClick(v);
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
                    Intent loggedInIntent = new Intent(getApplicationContext(), MyActivity.class);
                    startActivity(loggedInIntent);
                } else {
                    //Erro ocorrendo neste momento

                    Log.e(TAG, "Error loggin in: " + exception.getMessage());
                }
            }
        });
    }

}