package pushtest.com.example.rafaelmiceli.pushtest;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonQueryCallback;

import org.json.JSONObject;

import java.util.ArrayList;


public class MyActivity extends FragmentActivity implements View.OnClickListener {

    protected BarChart mChart;
    private Context mContext = this;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mChart = (BarChart) findViewById(R.id.chart1);
        btnTest = (Button) findViewById(R.id.btnTest);

        btnTest.setOnClickListener(this);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(1);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        // sets the text size of the values inside the chart
        mChart.setValueTextSize(10f);

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(tf);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setLabelCount(8);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(tf);
        rightAxis.setLabelCount(8);

        mChart.setValueFormatter(new MyValueFormatter());

        mChart.setValueTypeface(tf);

        Integer value = 188; //getLatestWaterDistance();

        setData(value);

        Legend l = mChart.getLegend();
        l.setEnabled(false);

    }

    private void setData(float range) {

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Reservatório");

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(range, 0));

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);

        mChart.setData(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            AuthService.getInstance(this).logout(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Integer getLatestWaterDistance() {

        AuthService.getInstance(this).getLatestLevelFromAzure(new TableJsonQueryCallback() {
            @Override
            public void onCompleted(JsonElement jsonElement, int i, Exception e, ServiceFilterResponse serviceFilterResponse) {
                try {
                    if (e != null) {
                        Log.e("ErrorActivity", "Error Azure Activity - " + e.getMessage());
                        return;
                    }

                    JsonArray results = jsonElement.getAsJsonArray();

                    for (JsonElement item : results){

                        String value = item.getAsJsonObject().getAsJsonPrimitive("Nivel").getAsString();

                        Toast.makeText(mContext, value,
                                Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception exception) {
                    Log.e("ErrorActivity", "Error Azure Activity - " + exception.getMessage());
                }
            }
        });

        return 188;
    }

    @Override
    public void onClick(View v) {
        Integer value = getLatestWaterDistance();

        setData(value);

        mChart.invalidate();
    }
}
