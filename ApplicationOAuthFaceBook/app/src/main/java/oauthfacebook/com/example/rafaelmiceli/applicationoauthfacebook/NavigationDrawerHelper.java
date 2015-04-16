package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Rafael on 01/11/2014.
 */
public class NavigationDrawerHelper {


    public static final int AGENDA = 0;
    public static final int SERVICES = 1;
    public static final int MY_PROFILE = 2;
    public static final int LOGOUT = 3;

    static NavigationDrawerHelper mNavigationDrawerHelperInstance;

    DrawerLayout mDrawerLayout;
    ListView mDrawerListView;
    private int mCheckedOption = MY_PROFILE;
    private ActionBarDrawerToggle mDrawerToggle;

    private NavigationDrawerHelper()    {
    }

    public static NavigationDrawerHelper initializeInstance() {
        if (mNavigationDrawerHelperInstance == null) {
            mNavigationDrawerHelperInstance = new NavigationDrawerHelper();
        }

        return mNavigationDrawerHelperInstance;
    }

    public  void init(Activity theActivity, ListView.OnItemClickListener listener) {
        mDrawerLayout = (DrawerLayout) theActivity.findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) theActivity.findViewById(R.id.left_drawer);

        String[] navigationDrawerOptions =
                theActivity.getResources().getStringArray(R.array.navigation_drawer_options);
        ArrayAdapter<String> navigationDrawerAdapter =
                new ArrayAdapter<String>(theActivity, R.layout.drawer_option_item, navigationDrawerOptions);
        mDrawerListView.setAdapter(navigationDrawerAdapter);
        mDrawerListView.setOnItemClickListener(listener);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerListView.setItemChecked(mCheckedOption, true);
        setupActionBar(theActivity);

    }

    private void setupActionBar(Activity theActivity){
        final Activity activity = theActivity;
        ActionBar actionBar = theActivity.getActionBar();

        //actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                theActivity,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.open_drawer_message,
                R.string.close_drawer_message
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }
        };

    }

    public void handleSelect(int option) {
        mCheckedOption = option;
        mDrawerListView.setItemChecked(mCheckedOption, true);
        mDrawerLayout.closeDrawer(mDrawerListView);
    }

    public void callIntent(int option, Context context) {
        if (option == LOGOUT) {
            Intent intent = new Intent(context, Logout.class);
            context.startActivity(intent);
        }

        if (option == SERVICES) {
            Intent intent = new Intent(context, Services.class);
            context.startActivity(intent);
        }

        if (option == MY_PROFILE) {
            Intent intent = new Intent(context, MyProfile.class);
            context.startActivity(intent);
        }

        if (option == AGENDA) {
            Intent intent = new Intent(context, Agenda.class);
            context.startActivity(intent);
        }

    }

    public void handleOnPrepareOptionsMenu(Menu menu) {
        boolean itemVisible = ! mDrawerLayout.isDrawerOpen(mDrawerListView);

        for (int index = 0; index < menu.size(); index++) {
            MenuItem item = menu.getItem(index);
            item.setEnabled(itemVisible);
        }
    }

    public void handleOnOptionsItemSelected(MenuItem item) {
        mDrawerToggle.onOptionsItemSelected(item);
    }

    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void setSelection(int option) {
        mDrawerListView.setItemChecked(option, true);
    }
}
