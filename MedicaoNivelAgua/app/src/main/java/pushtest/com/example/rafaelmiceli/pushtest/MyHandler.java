package pushtest.com.example.rafaelmiceli.pushtest;

import com.microsoft.windowsazure.notifications.NotificationsHandler;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by rafael.miceli on 09/12/2014.
 */
public class MyHandler extends NotificationsHandler {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    Context ctx;
    private Integer _criticalWaterLevel = 20;

    @Override
    public void onReceive(Context context, Bundle bundle) {
        ctx = context;
        String azureMessage = bundle.getString("msg");

        if (isCriticalWaterLevel(azureMessage))
            sendNotification(azureMessage);

        updateCharts(azureMessage);

        Toast toast = Toast.makeText(ctx, azureMessage, Toast.LENGTH_LONG);
        toast.show();
    }

    private boolean isCriticalWaterLevel(String azureMessage) {
        //Nós medimos o nível de criticidade de nível de água de acordo com quantos centimetros cairam
        //do nível total do reservatório de água.

        return Integer.parseInt(azureMessage) >= _criticalWaterLevel;

    }

    private void updateCharts(String azureMessage) {
    }


    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
                new Intent(ctx, MyActivity.class), 0);

        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Nível de água muito baixo!")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                        .setSound(notificationSound);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }
}

