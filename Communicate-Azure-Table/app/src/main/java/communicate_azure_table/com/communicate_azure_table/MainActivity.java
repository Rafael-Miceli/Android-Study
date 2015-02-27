package communicate_azure_table.com.communicate_azure_table;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceJsonTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonQueryCallback;

import java.net.MalformedURLException;


public class MainActivity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private Context mContext;
    private MobileServiceJsonTable mTableAccounts;
    private MobileServiceJsonTable mTableBadAuth;
    private MobileServiceJsonTable mTableAuthData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        try {
            mClient = new MobileServiceClient("Azure-MobileService-Address", "Azure-MobileService-ConnectionString", mContext);
            mTableAccounts = mClient.getTable("accounts");
            mTableBadAuth = mClient.getTable("BadAuth");
            mTableAuthData = mClient.getTable("ClienteTeste1");

            getLatestWaterDistance();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getLatestLevelFromAzure(final TableJsonQueryCallback callback){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mTableAuthData.execute(mTableAuthData.parameter("table", mTableAuthData.getTableName()) ,callback);
                } catch (Exception exception) {
                    Log.e("ErrorAuthService", "Error Azure AuthService - " + exception.getMessage());
                }
                return null;
            }
        }.execute();

    }

    public void getLatestWaterDistance() {

        getLatestLevelFromAzure(new TableJsonQueryCallback() {
            @Override
            public void onCompleted(JsonElement jsonElement, int i, Exception e, ServiceFilterResponse serviceFilterResponse) {
                try {
                    if (e != null) {
                        Log.e("ErrorActivity", "Error Azure Activity - " + e.getMessage());
                        return;
                    }

                    JsonArray results = jsonElement.getAsJsonArray();

                    for (JsonElement item : results) {

                        String value = item.getAsJsonObject().getAsJsonPrimitive("Nivel").getAsString();

                        Toast.makeText(mContext, value,
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception exception) {
                    Log.e("ErrorActivity", "Error Azure Activity - " + exception.getMessage());
                }
            }
        });
    }
}
