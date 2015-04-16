package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Rafael on 16/11/2014.
 */
public class ServicePageAdapter extends FragmentPagerAdapter {

    String[] mServiceTitles;
	String[] mServiceMoneyPreference;
	String[] mServiceTimePreference;
    Context mContext;

    public ServicePageAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
        Resources resources = mContext.getResources();
        mServiceTitles = resources.getStringArray(R.array.service_titles);
        mServiceMoneyPreference = resources.getStringArray(R.array.service_money_pref);
        mServiceTimePreference = resources.getStringArray(R.array.service_time_pref);
    }

    @Override
    public Fragment getItem(int item) {
        Bundle arguments = new Bundle();
        arguments.putString(ServiceFragment.SERVICE_TITLE, mServiceTitles[item]);
        arguments.putInt(ServiceFragment.TOP_CARD, translateTopCardIndex(item));
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        arguments.putInt(ServiceFragment.SERVICE_TIME, prefs.getInt(mServiceTimePreference[item], 60));
        arguments.putDouble(ServiceFragment.SERVICE_PRICE, Double.valueOf(String.valueOf(prefs.getString(mServiceMoneyPreference[item], "20"))));
        arguments.putBoolean(ServiceFragment.SERVICE_IDO, true);


        ServiceFragment serviceFragment = new ServiceFragment();
        serviceFragment.setArguments(arguments);
        return serviceFragment;
    }

    private int translateTopCardIndex(int itemImage) {
        int resourceId;

        switch (itemImage) {
            case 0:
                resourceId = R.drawable.ps_android_logo;
                break;
            default:
                resourceId = R.drawable.ps_android_logo;
                break;
        }

        return resourceId;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mServiceTitles[position];
    }

    @Override
    public int getCount() {
        return mServiceTitles.length;
    }
}
