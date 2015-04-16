package com.unitteststudy.rafael.unitteststudy;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rafael on 16/04/2015.
 */
public class TankFragment extends Fragment {

    public final static String TANK_NAME = "Reservatorio";
    public final static String TANK_VALUE = "0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_tank, container, false);

        //Resources resources = theView.getResources();
//        mServiceMoneyPreference = resources.getStringArray(R.array.service_money_pref);
//        mServiceTimePreference = resources.getStringArray(R.array.service_time_pref);
//
//        Bundle arguments = getArguments();
//        if (arguments != null) {
//            mServiceTitle = arguments.getString(SERVICE_TITLE);
//            mTopCardResourceId = arguments.getInt(TOP_CARD);
//            mIdo = arguments.getBoolean(SERVICE_IDO);
//            mPrice = arguments.getDouble(SERVICE_PRICE);
//            mTime = arguments.getInt(SERVICE_TIME);
//
//            initializeControls(theView);
//
//            displayValues(mServiceTitle, mTopCardResourceId, mIdo, mPrice, mTime);
//        }

        return theView;
    }
}
