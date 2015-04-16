package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;


public class Logout extends FragmentActivity implements ListView.OnItemClickListener{

    private NavigationDrawerHelper mNavigationDrawerHelper;

    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback =
            new Session.StatusCallback() {
                @Override
                public void call(Session session,
                                 SessionState state, Exception exception) {
                    onSessionStateChange(session, state, exception);
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mNavigationDrawerHelper = NavigationDrawerHelper.initializeInstance();
        mNavigationDrawerHelper.init(this, this);

        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        // Only make changes if the activity is visible

        if (state.isOpened()) {
            // If the session state is open:
            // Show the authenticated fragment
            //showFragment(MAIN, false);
        } else if (state.isClosed()) {
            // If the session state is closed:
            // Show the login fragment
            mNavigationDrawerHelper.handleSelect(mNavigationDrawerHelper.MY_PROFILE);
            Intent intent = new Intent(this, FacebookLogin.class);
            startActivity(intent);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mNavigationDrawerHelper.handleSelect(position);

        mNavigationDrawerHelper.callIntent(position, this);
    }
}
