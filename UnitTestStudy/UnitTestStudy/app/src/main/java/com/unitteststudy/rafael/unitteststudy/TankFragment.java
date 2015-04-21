package com.unitteststudy.rafael.unitteststudy;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rafael on 16/04/2015.
 */
public class TankFragment extends Fragment {

    public final static String TANK_NAME = "Reservatorio";
    public final static String TANK_VALUE = "0";
    private String mTankName;
    private int mTankValue;
    private TextView mTankNameTextView;
    private TextView mTankValueTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_tank, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mTankName = arguments.getString(TANK_NAME);
            mTankValue = arguments.getInt(TANK_VALUE);

            initializeControls(theView);

            displayValues(mTankName, mTankValue);
        }

        return theView;
    }

    private void initializeControls(View theView) {
        mTankNameTextView = (TextView) theView.findViewById(R.id.textViewTankName);
        mTankValueTextView = (TextView) theView.findViewById(R.id.textViewTankValue);
    }

    private void displayValues(final String tankName, int tankValue) {

        mTankNameTextView.setText(tankName);
        mTankValueTextView.setText(tankValue);
    }
}
