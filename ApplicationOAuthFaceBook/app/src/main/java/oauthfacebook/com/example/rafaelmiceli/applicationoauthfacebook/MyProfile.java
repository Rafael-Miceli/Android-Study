package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MyProfile extends Activity implements ListView.OnItemClickListener {

    private NavigationDrawerHelper mNavigationDrawerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mNavigationDrawerHelper = NavigationDrawerHelper.initializeInstance();
        mNavigationDrawerHelper.init(this, this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mNavigationDrawerHelper.handleSelect(position);

        mNavigationDrawerHelper.callIntent(position, this);
    }
}
