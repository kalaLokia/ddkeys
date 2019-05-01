package drpg.ddkeys.master;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import drpg.ddkeys.R;
import drpg.ddkeys.ddKeysView;

import static drpg.ddkeys.data.Locations.myPlace;
import static drpg.ddkeys.master.masterSharedPreference.timerStatusSave;
import static drpg.ddkeys.master.master_init.KEYBOARD_CONTEXT;
import static drpg.ddkeys.master.master_init.advLBL;
import static drpg.ddkeys.master.master_init.advVibrate;
import static drpg.ddkeys.master.master_init.adv_angle;
import static drpg.ddkeys.master.master_init.currentloc;
import static drpg.ddkeys.master.master_init.customTimer;
import static drpg.ddkeys.master.master_init.isAdvTimer;
import static drpg.ddkeys.master.master_init.isSearching;
import static drpg.ddkeys.master.master_init.isSideTimer;
import static drpg.ddkeys.master.master_init.isTalkTimer;
import static drpg.ddkeys.master.master_init.isTravelling;
import static drpg.ddkeys.master.master_init.isparty;
import static drpg.ddkeys.master.master_init.keyboard;
import static drpg.ddkeys.master.master_init.kv;
import static drpg.ddkeys.master.master_init.moptions1;
import static drpg.ddkeys.master.master_init.moptions2;
import static drpg.ddkeys.master.master_init.setendtime;
import static drpg.ddkeys.master.master_init.sidesTimeOFF;
import static drpg.ddkeys.master.master_init.timeleft;
import static drpg.ddkeys.master.master_init.travelkeyboard;
import static drpg.ddkeys.master.master_init.vibarteAdv;


public class masterCooldownTimer {


    public static CountDownTimer advtimer;
    public static CountDownTimer traveltimer;
    public static CountDownTimer searchtimer;
    public static CountDownTimer sidetimer;

    //sidesTimer (notification)
    public static void sidesTime(long leftTime) {
        sidetimer = new CountDownTimer(leftTime, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ddKeysView.sTime = millisUntilFinished / 1000 + "";
                isSideTimer = true;
                if (kv != null && moptions1 && !isAdvTimer) {
                    kv.postInvalidate();
                }
            }

            @Override
            public void onFinish() {
                ddKeysView.sTime = "do!";
                sidesTimeOFF();
                timerStatusSave(System.currentTimeMillis(), true);
                new masterNotificationManager(100);
            }
        }.start();
    }

    //sidesTimer :MAIN
    public static void startTimer() {
        setendtime = System.currentTimeMillis() + 300000;
        timerStatusSave(setendtime, false);
        timeleft = setendtime - System.currentTimeMillis();
        sidesTime(timeleft);
    }

    //Custom Cooldown Timer for ADV and custom commands
    public static void ccdt() {
        new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
                customTimer = true;
            }

            public void onFinish() {
                customTimer = false;
            }
        }.start();
    }

    //talkTimer 10 seconds
    public static void talkTimer(final int lab, final String npc) {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                isTalkTimer = true;
                travelkeyboard.getKeys().get(lab).label = millisUntilFinished / (1000) + "";
                kv.invalidateKey(lab);
            }

            public void onFinish() {
                travelkeyboard.getKeys().get(lab).label = npc;
                kv.invalidateAllKeys();
                isTalkTimer = false;
            }
        }.start();
    }

    //searchTimer 10 minute
    public static void searchTiming() {
        searchtimer = new CountDownTimer(600000, 60000) {

            public void onTick(long millisUntilFinished) {
                if (moptions2) {
                    travelkeyboard.getKeys().get(7).icon = ContextCompat.getDrawable(KEYBOARD_CONTEXT, R.drawable.ic_no_search);
                    kv.invalidateKey(7);
                }
                isSearching = true;
            }

            public void onFinish() {
                if (moptions2) {
                    travelkeyboard.getKeys().get(7).icon = ContextCompat.getDrawable(KEYBOARD_CONTEXT, R.drawable.ic_search);
                    kv.invalidateKey(7);
                }
                Toast.makeText(KEYBOARD_CONTEXT, "You can search now", Toast.LENGTH_LONG).show();
                isSearching = false;

            }
        }.start();
    }

    //travelTimer (notification)
    public static void travelTiming(long travelTimer) {

        traveltimer = new CountDownTimer(travelTimer, 1000) {

            public void onTick(long millisUntilFinished) {
                isTravelling = true;
                keyboard.getKeys().get(8).label = millisUntilFinished / (1000) + "";
                kv.invalidateKey(6);
            }

            public void onFinish() {
                isTravelling = false;
                //myPlace(currentloc);
                myPlace(currentloc);
                kv.invalidateAllKeys();
                new masterNotificationManager(200);
            }
        }.start();
    }

    //advTimer (adventure and party)
    public static void advtiming(int counter) {
        advtimer = new CountDownTimer(counter, 250) {

            public void onTick(long millisUntilFinished) {

                isAdvTimer = true;
                keyboard.getKeys().get(0).label = "wait";
                kv.invalidateKey(0);
                if (!isparty) {
                    adv_angle = adv_angle + 5;
                } else {
                    adv_angle = adv_angle + 3.5f;
                }
                kv.postInvalidate();
            }

            public void onFinish() {
                isAdvTimer = false;
                if (!isparty) {
                    adv_angle = 5;
                } else {
                    adv_angle = 3.5f;
                }
                keyboard.getKeys().get(0).label = advLBL + "";
                Toast.makeText(KEYBOARD_CONTEXT, "ADVENTURE", Toast.LENGTH_LONG).show();
                kv.invalidateKey(0);
                if (vibarteAdv) {
                    advVibrate();
                }
            }
        }.start();
    }

}
