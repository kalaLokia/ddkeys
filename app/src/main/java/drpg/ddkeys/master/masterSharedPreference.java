package drpg.ddkeys.master;

import android.content.SharedPreferences;

import static drpg.ddkeys.master.master_init.ADV_COUNT;
import static drpg.ddkeys.master.master_init.CHOP_COUNT;
import static drpg.ddkeys.master.master_init.FISH_COUNT;
import static drpg.ddkeys.master.master_init.FORAGE_COUNT;
import static drpg.ddkeys.master.master_init.MINE_COUNT;
import static drpg.ddkeys.master.master_init.autoHealCount;
import static drpg.ddkeys.master.master_init.autoPhealCount;
import static drpg.ddkeys.master.master_init.autohealing;
import static drpg.ddkeys.master.master_init.autophealing;
import static drpg.ddkeys.master.master_init.ccL_left1;
import static drpg.ddkeys.master.master_init.ccL_left2;
import static drpg.ddkeys.master.master_init.ccL_left3;
import static drpg.ddkeys.master.master_init.ccL_left4;
import static drpg.ddkeys.master.master_init.ccL_left5;
import static drpg.ddkeys.master.master_init.ccL_left6;
import static drpg.ddkeys.master.master_init.ccL_right1;
import static drpg.ddkeys.master.master_init.ccL_right2;
import static drpg.ddkeys.master.master_init.ccL_right3;
import static drpg.ddkeys.master.master_init.ccL_right4;
import static drpg.ddkeys.master.master_init.ccL_right5;
import static drpg.ddkeys.master.master_init.ccL_right6;
import static drpg.ddkeys.master.master_init.cc_left1;
import static drpg.ddkeys.master.master_init.cc_left2;
import static drpg.ddkeys.master.master_init.cc_left3;
import static drpg.ddkeys.master.master_init.cc_left4;
import static drpg.ddkeys.master.master_init.cc_left5;
import static drpg.ddkeys.master.master_init.cc_left6;
import static drpg.ddkeys.master.master_init.cc_right1;
import static drpg.ddkeys.master.master_init.cc_right2;
import static drpg.ddkeys.master.master_init.cc_right3;
import static drpg.ddkeys.master.master_init.cc_right4;
import static drpg.ddkeys.master.master_init.cc_right5;
import static drpg.ddkeys.master.master_init.cc_right6;
import static drpg.ddkeys.master.master_init.cckeys_left;
import static drpg.ddkeys.master.master_init.cckeys_right;
import static drpg.ddkeys.master.master_init.colorCode;
import static drpg.ddkeys.master.master_init.currentloc;
import static drpg.ddkeys.master.master_init.endtime;
import static drpg.ddkeys.master.master_init.healcount;
import static drpg.ddkeys.master.master_init.hiddenop1;
import static drpg.ddkeys.master.master_init.isSearching;
import static drpg.ddkeys.master.master_init.isTravelling;
import static drpg.ddkeys.master.master_init.isparty;
import static drpg.ddkeys.master.master_init.mobcount;
import static drpg.ddkeys.master.master_init.moptions1;
import static drpg.ddkeys.master.master_init.moptions2;
import static drpg.ddkeys.master.master_init.moptions3;
import static drpg.ddkeys.master.master_init.moptions4;
import static drpg.ddkeys.master.master_init.moptions5;
import static drpg.ddkeys.master.master_init.moptions6;
import static drpg.ddkeys.master.master_init.phealcount;
import static drpg.ddkeys.master.master_init.prefix;
import static drpg.ddkeys.master.master_init.previousloc;
import static drpg.ddkeys.master.master_init.setendtime;
import static drpg.ddkeys.master.master_init.stdTheme;
import static drpg.ddkeys.master.master_init.themeChoice;
import static drpg.ddkeys.master.master_init.vibarteAdv;
import static drpg.ddkeys.master.master_init.xpRing;
import static drpg.ddkeys.master.master_init.xp_or_ring;
import static drpg.ddkeys.master.master_init.xprate;

public class masterSharedPreference {

    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    //getting saved values from device storage
    public static void init_savedvalues() {
        ADV_COUNT = sp.getLong("adv_count", 0);
        MINE_COUNT = sp.getLong("mine_count", 0);
        CHOP_COUNT = sp.getLong("chop_count", 0);
        FORAGE_COUNT = sp.getLong("forage_count", 0);
        FISH_COUNT = sp.getLong("fish_count", 0);
        mobcount = sp.getLong("mob_count", 0);
        currentloc = sp.getInt("current_loc", 1);
        previousloc = sp.getInt("previous_loc", 1);
        healcount = sp.getInt("heal_count", 0);
        phealcount = sp.getInt("pheal_count", 0);
        autoHealCount = sp.getInt("healCount", 30);
        autoPhealCount = sp.getInt("phealCount", 15);

        prefix = sp.getString("prefix", "DiscordRPG ");

        xprate = sp.getBoolean("xprate", true);
        isparty = sp.getBoolean("advMode", false);
        isTravelling = sp.getBoolean("is_traveling", false);
        isSearching = sp.getBoolean("is_searching", false);
        autophealing = sp.getBoolean("auto_pheal", false);
        autohealing = sp.getBoolean("auto_heal", false);
        stdTheme = sp.getBoolean("std_theme", true);
        themeChoice = sp.getInt("theme_choice", 0);
        //more options tab related
        moptions1 = sp.getBoolean("moption1", true);
        moptions2 = sp.getBoolean("moption2", true);
        moptions3 = sp.getBoolean("moption3", true);
        moptions4 = sp.getBoolean("moption4", true);
        moptions5 = sp.getBoolean("moption5", true);
        moptions6 = sp.getBoolean("moption6", false);
        xp_or_ring = sp.getBoolean("xp_or_ring", true);
        xpRing = sp.getBoolean("xp_ring", true);
        //custom command keys
        cckeys_left = sp.getString("cc_left", "discordrpg inv");
        cckeys_right = sp.getString("cc_right", "discordrpg guild");
        cc_left1 = sp.getString("cc_left1", "");
        cc_left2 = sp.getString("cc_left2", "");
        cc_left3 = sp.getString("cc_left3", "");
        cc_left4 = sp.getString("cc_left4", "");
        cc_left5 = sp.getString("cc_left5", "");
        cc_left6 = sp.getString("cc_left6", "");
        cc_right1 = sp.getString("cc_right1", "");
        cc_right2 = sp.getString("cc_right2", "");
        cc_right3 = sp.getString("cc_right3", "");
        cc_right4 = sp.getString("cc_right4", "");
        cc_right5 = sp.getString("cc_right5", "");
        cc_right6 = sp.getString("cc_right6", "");
        //Label name for custom keys
        ccL_left1 = sp.getString("ccL_left1", "");
        ccL_left2 = sp.getString("ccL_left2", "");
        ccL_left3 = sp.getString("ccL_left3", "");
        ccL_left4 = sp.getString("ccL_left4", "");
        ccL_left5 = sp.getString("ccL_left5", "");
        ccL_left6 = sp.getString("ccL_left6", "");
        ccL_right1 = sp.getString("ccL_right1", "");
        ccL_right2 = sp.getString("ccL_right2", "");
        ccL_right3 = sp.getString("ccL_right3", "");
        ccL_right4 = sp.getString("ccL_right4", "");
        ccL_right5 = sp.getString("ccL_right5", "");
        ccL_right6 = sp.getString("ccL_right6", "");

        colorCode = sp.getString("adv_color", "#66cccc");


        hiddenop1 = sp.getBoolean("userHeal", false);  //disable HEAL button or not
        vibarteAdv = sp.getBoolean("vibrateAdv", false); //Vibration for adv CD

    }

    public static void timerStatusSave(long timenow, boolean statenow) {
        editor = sp.edit();
        editor.putLong("setendtime", timenow);
        editor.putBoolean("endtime", statenow);
        editor.apply();
    }

    public static void timerStatusGet() {
        setendtime = sp.getLong("setendtime", System.currentTimeMillis());
        endtime = sp.getBoolean("endtime", true);
    }

    public static void saveBoolean(String string, Boolean value) {
        editor = sp.edit();
        editor.putBoolean(string, value);
        editor.apply();
    }

    public static void saveInteger(String string, Integer value) {
        editor = sp.edit();
        editor.putInt(string, value);
        editor.apply();
    }

    public static void saveString(String string, String value) {
        editor = sp.edit();
        editor.putString(string, value);
        editor.apply();
    }

    public static void saveLong(String string, Long value) {
        editor = sp.edit();
        editor.putLong(string, value);
        editor.apply();
    }

}
