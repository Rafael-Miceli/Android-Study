package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Services extends FragmentActivity implements ListView.OnItemClickListener {

    private NavigationDrawerHelper mNavigationDrawerHelper;

    ServicePageAdapter mServicePageAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        mNavigationDrawerHelper = NavigationDrawerHelper.initializeInstance();
        mNavigationDrawerHelper.init(this, this);

        mServicePageAdapter = new ServicePageAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mServicePageAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mNavigationDrawerHelper.handleSelect(position);

        mNavigationDrawerHelper.callIntent(position, this);
    }
}
