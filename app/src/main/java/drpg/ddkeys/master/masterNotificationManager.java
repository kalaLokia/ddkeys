package drpg.ddkeys.master;

import android.app.Notification;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import drpg.ddkeys.R;
import drpg.ddkeys.data.Connections;
import drpg.ddkeys.data.Locations;

import static drpg.ddkeys.ddKeys_init.ddkNbuilder;
import static drpg.ddkeys.ddKeys_init.ddkNmanager;
import static drpg.ddkeys.master.master_init.ADV_COUNT;
import static drpg.ddkeys.master.master_init.CHOP_COUNT;
import static drpg.ddkeys.master.master_init.FISH_COUNT;
import static drpg.ddkeys.master.master_init.FORAGE_COUNT;
import static drpg.ddkeys.master.master_init.KEYBOARD_CONTEXT;
import static drpg.ddkeys.master.master_init.MINE_COUNT;
import static drpg.ddkeys.master.master_init.NOTIFICATION_ID;
import static drpg.ddkeys.master.master_init.PACKAGE_NAME;
import static drpg.ddkeys.master.master_init.TRAVEL_NOTIFICATION_ID;
import static drpg.ddkeys.master.master_init.discordopens;
import static drpg.ddkeys.master.master_init.kingdom;
import static drpg.ddkeys.master.master_init.nBuilder;
import static drpg.ddkeys.master.master_init.nManager;
import static drpg.ddkeys.master.master_init.place_descr;


public class masterNotificationManager {

    public masterNotificationManager(int nType) {

        switch (nType) {
            case 100:
                nBuilder.setSmallIcon(R.drawable.ic_logo)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentTitle("SIDES..!!")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(KEYBOARD_CONTEXT.getResources().getString(R.string.sideslist)))
                        .setContentText("Do mine, chop, forage & fish")
                        .setSound(Uri.parse("android.resource://" + PACKAGE_NAME + "/" + R.raw.sidef));
                if (discordopens != null) {
                    nBuilder.setContentIntent(discordopens);
                }
                nManager.notify(NOTIFICATION_ID, nBuilder.build());
                break;
            case 200:
                nBuilder.setSmallIcon(R.drawable.ic_logo)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentTitle(Locations.locations[Connections.getCurrent()])
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Kingdom " + kingdom + place_descr + ""))
                        .setContentText("in kingdom " + kingdom + "")
                        .setDefaults(Notification.DEFAULT_SOUND);
                if (discordopens != null) {
                    nBuilder.setContentIntent(discordopens);
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    nBuilder.setTimeoutAfter(30000);
                } else {
                    Handler h = new Handler();
                    long timeoutAftersec = 30000;
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nManager.cancel(TRAVEL_NOTIFICATION_ID);
                        }
                    }, timeoutAftersec);
                }
                nManager.notify(TRAVEL_NOTIFICATION_ID, nBuilder.build());
                break;
            case 777:
                ddkNbuilder.setSmallIcon(R.drawable.ic_logo)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentTitle(ADV_COUNT + "X-" + MINE_COUNT + "M-" + CHOP_COUNT + "C-" + FORAGE_COUNT + "F-" + FISH_COUNT + "F")
                        .setDefaults(Notification.DEFAULT_ALL);
                ddkNmanager.notify(777, ddkNbuilder.build());
                break;
            default:
                nBuilder.setContentTitle("ddKeys ")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Designed and Developed by speCtre#5685"))
                        .setContentText("Version : Blaze 4.4.37");
                nManager.notify(NOTIFICATION_ID, nBuilder.build());
                // TODO: Task notification
        }

    }


}
