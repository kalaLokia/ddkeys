package drpg.ddkeys.master;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.RectF;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import java.util.Arrays;

public class master_init {

    public static final String TAG = "myTAG";
    public static final String CHANNEL_ID_KEYS = "ddkeys";
    public static final String CHANNEL_NAME_KEYS = "DRPG";
    public static final String CHANNEL_DESCRIPTION_KEYS = "Game Notifications";
    public static final int NOTIFICATION_ID = 300;
    public static final int TRAVEL_NOTIFICATION_ID = 401;
    public static String PACKAGE_NAME;
    public static Context KEYBOARD_CONTEXT;
    public static Context APP_CONTEXT;
    public static long ADV_COUNT = 0;
    public static long MINE_COUNT = 0;
    public static long CHOP_COUNT = 0;
    public static long FORAGE_COUNT = 0;
    public static long FISH_COUNT = 0;

    public static Vibrator vibrateme;
    public static KeyboardView kv;
    public static Keyboard keyboard;
    public static Keyboard travelkeyboard;

    public static String colorCode = "#66cccc";
    public static RectF advCurve;

    public static NotificationManager nManager;
    public static NotificationCompat.Builder nBuilder;

    public static PendingIntent discordopens;

    public static boolean stdTheme = true;
    public static boolean isAdvTimer = false;
    public static boolean isTalkTimer = false;
    public static boolean isTravelling = false;
    public static boolean isSearching = false;
    public static boolean isSideTimer = false;
    public static boolean customTimer = false;
    public static boolean endtime = true;
    public static boolean isSide1 = false;          //mined or not
    public static boolean isSide2 = false;          //chopped or not
    public static boolean isSide3 = false;          //foraged or not
    public static boolean isSide4 = false;          //fished or not
    public static boolean xprate = true;            //pet xprate 100 or 0
    public static boolean isparty = false;          //party mode on or not
    public static boolean autohealing = false;
    public static boolean autophealing = false;
    public static boolean moptions1 = false;        //sidesTimer should show or not
    public static boolean moptions2 = false;        //travelKeyboard enabled or disabled
    public static boolean moptions3 = false;        //long press on advKey on/off
    public static boolean moptions4 = false;        //long press on petKey on/off
    public static boolean moptions5 = false;        //long press on each sidesKeys on/off
    public static boolean moptions6 = false;        //adventure count show or not
    public static boolean hiddenop1 = false;        //disable/enable healKey
    public static boolean vibarteAdv = false;
    public static int healcount = 0;
    public static int phealcount = 0;
    public static int autoHealCount;  //user prefered
    public static int autoPhealCount; //user prefered
    public static int currentloc = 1;
    public static int previousloc = 1;
    public static int themeChoice = 0;
    public static float adv_angle = 5;
    public static boolean xp_or_ring = true;
    public static boolean xpRing = true;
    public static boolean cdTen = false;

    // public static int screenSize = 1;

    public static long mobcount = 0;
    public static long timeleft = 0;
    public static long setendtime = 0;

    public static int petLife;
    public static int myLife;


    public static String prefix = "DiscordRPG ";
    public static String advLBL = "ADV";
    public static String kingdom = "";
    public static String place_descr = "";

    //customKeys
    public static String cckeys_left;
    public static String cckeys_right;
    public static String cc_left1;
    public static String cc_left2;
    public static String cc_left3;
    public static String cc_left4;
    public static String cc_left5;
    public static String cc_left6;
    public static String cc_right1;
    public static String cc_right2;
    public static String cc_right3;
    public static String cc_right4;
    public static String cc_right5;
    public static String cc_right6;
    public static String ccL_left1 = "ERR";
    public static String ccL_left2 = "ERR";
    public static String ccL_left3 = "ERR";
    public static String ccL_left4 = "ERR";
    public static String ccL_left5 = "ERR";
    public static String ccL_left6 = "ERR";
    public static String ccL_right1 = "ERR";
    public static String ccL_right2 = "ERR";
    public static String ccL_right3 = "ERR";
    public static String ccL_right4 = "ERR";
    public static String ccL_right5 = "ERR";
    public static String ccL_right6 = "ERR";

    //keyboard update when sidesTimer is not running
    public static void sidesTimeOFF() {
        isSideTimer = isSide1 = isSide2 = isSide3 = isSide4 = false;
        keyboard.getKeys().get(2).label = "CHOP";
        keyboard.getKeys().get(4).label = "FORAGE";
        keyboard.getKeys().get(1).label = "MINE";
        keyboard.getKeys().get(5).label = "FISH";
        kv.invalidateAllKeys();
    }

    //keyboard update when sidesTimer running
    public static void sidesTimeON() {
        keyboard.getKeys().get(2).label = "chopped!";
        keyboard.getKeys().get(4).label = "foraged!";
        keyboard.getKeys().get(1).label = "mined!";
        keyboard.getKeys().get(5).label = "fished!";
    }

    //checking array for a match, return false if not found
    public static boolean contains(int[] arr, int item) {
        int index = Arrays.binarySearch(arr, item);
        return index >= 0;
    }

    public static void advVibrate() {
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrateme.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrateme.vibrate(250);
        }
    }
}
