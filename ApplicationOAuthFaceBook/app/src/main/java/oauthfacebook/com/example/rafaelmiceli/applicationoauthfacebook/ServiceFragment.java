package oauthfacebook.com.example.rafaelmiceli.applicationoauthfacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rafael on 16/11/2014.
 */
public class ServiceFragment extends Fragment {

    public final static String SERVICE_TITLE = "service title";
    public final static String TOP_CARD = "top card";
    public final static String SERVICE_IDO = "service ido";
    public final static String SERVICE_PRICE = "service price";
    public final static String SERVICE_TIME = "service time";

    String mServiceTitle;
    int mTopCardResourceId;
    boolean mIdo;
    double mPrice;
    int mTime;

    String[] mServiceMoneyPreference;
    String[] mServiceTimePreference;

    //fragment controls
    TextView serviceTitleTextView;
    ImageView topCardImageView;
    CheckBox iDoServiceCheckBox;
    EditText priceEditText;
    EditText timeEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_services, container, false);

        Resources resources = theView.getResources();
        mServiceMoneyPreference = resources.getStringArray(R.array.service_money_pref);
        mServiceTimePreference = resources.getStringArray(R.array.service_time_pref);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mServiceTitle = arguments.getString(SERVICE_TITLE);
            mTopCardResourceId = arguments.getInt(TOP_CARD);
            mIdo = arguments.getBoolean(SERVICE_IDO);
            mPrice = arguments.getDouble(SERVICE_PRICE);
            mTime = arguments.getInt(SERVICE_TIME);

            initializeControls(theView);

            displayValues(mServiceTitle, mTopCardResourceId, mIdo, mPrice, mTime);
        }

        return theView;
    }

    private void initializeControls(View theView) {
        serviceTitleTextView = (TextView) theView.findViewById(R.id.serviceTitle);
        topCardImageView = (ImageView) theView.findViewById(R.id.topCard);
        iDoServiceCheckBox = (CheckBox) theView.findViewById(R.id.checkBoxDoService);
        priceEditText = (EditText) theView.findViewById(R.id.editTextPrice);
        timeEditText = (EditText) theView.findViewById(R.id.editTextTime);

        setPriceTextChangedListener(theView);
        setTimeTextChangedListener(theView);
    }

    private void setPriceTextChangedListener(final View theView) {
        priceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(theView.getContext());

                if (!isEmpty(s)) {

                    if (mServiceTitle.equals("Mão")) {
                        prefs.edit().putString(mServiceMoneyPreference[0], s.toString()).commit();
                    }
                    if (mServiceTitle.equals("Pé e Mão")) {
                        prefs.edit().putString(mServiceMoneyPreference[1], s.toString()).commit();
                    }
                    if (mServiceTitle.equals("Francesinha")) {
                        prefs.edit().putString(mServiceMoneyPreference[2], s.toString()).commit();
                    }
                    if (mServiceTitle.equals("Desenhos")) {
                        prefs.edit().putString(mServiceMoneyPreference[3], s.toString()).commit();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setTimeTextChangedListener(final View theView) {

        timeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(theView.getContext());

                if (!isEmpty(s)) {

                    if (mServiceTitle.equals("Mão")) {
                        prefs.edit().putInt(mServiceTimePreference[0], Integer.valueOf(s.toString())).commit();
                    }
                    if (mServiceTitle.equals("Pé e Mão")) {
                        prefs.edit().putInt(mServiceTimePreference[1], Integer.valueOf(s.toString())).commit();
                    }
                    if (mServiceTitle.equals("Francesinha")) {
                        prefs.edit().putInt(mServiceTimePreference[2], Integer.valueOf(s.toString())).commit();
                    }
                    if (mServiceTitle.equals("Desenhos")) {
                        prefs.edit().putInt(mServiceTimePreference[3], Integer.valueOf(s.toString())).commit();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean isEmpty(CharSequence s) {
        return s.toString().equals("");
    }

    private void displayValues(final String serviceTitle, int topCardResourceId, boolean Ido, double price, int time) {

        serviceTitleTextView.setText(serviceTitle);
        topCardImageView.setImageResource(topCardResourceId);
        iDoServiceCheckBox.setChecked(Ido);

        priceEditText.setText(String.valueOf(price));
        timeEditText.setText(String.valueOf(time));
    }
}
