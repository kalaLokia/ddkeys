package drpg.ddkeys;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import drpg.ddkeys.data.Connections;
import drpg.ddkeys.data.NPCs;
import drpg.ddkeys.data.TravelTime;
import drpg.ddkeys.master.masterNotificationManager;

import static android.content.ContentValues.TAG;
import static drpg.ddkeys.data.Locations.chop_restr;
import static drpg.ddkeys.data.Locations.fish_restr;
import static drpg.ddkeys.data.Locations.forage_restr;
import static drpg.ddkeys.data.Locations.locations;
import static drpg.ddkeys.data.Locations.mine_restr;
import static drpg.ddkeys.data.Locations.myPlace;
import static drpg.ddkeys.master.masterCooldownTimer.advtimer;
import static drpg.ddkeys.master.masterCooldownTimer.advtiming;
import static drpg.ddkeys.master.masterCooldownTimer.searchTiming;
import static drpg.ddkeys.master.masterCooldownTimer.searchtimer;
import static drpg.ddkeys.master.masterCooldownTimer.sidesTime;
import static drpg.ddkeys.master.masterCooldownTimer.sidetimer;
import static drpg.ddkeys.master.masterCooldownTimer.startTimer;
import static drpg.ddkeys.master.masterCooldownTimer.talkTimer;
import static drpg.ddkeys.master.masterCooldownTimer.travelTiming;
import static drpg.ddkeys.master.masterCooldownTimer.traveltimer;
import static drpg.ddkeys.master.masterSharedPreference.editor;
import static drpg.ddkeys.master.masterSharedPreference.init_savedvalues;
import static drpg.ddkeys.master.masterSharedPreference.saveBoolean;
import static drpg.ddkeys.master.masterSharedPreference.saveInteger;
import static drpg.ddkeys.master.masterSharedPreference.saveLong;
import static drpg.ddkeys.master.masterSharedPreference.sp;
import static drpg.ddkeys.master.masterSharedPreference.timerStatusGet;
import static drpg.ddkeys.master.masterSharedPreference.timerStatusSave;
import static drpg.ddkeys.master.master_init.ADV_COUNT;
import static drpg.ddkeys.master.master_init.CHANNEL_DESCRIPTION_KEYS;
import static drpg.ddkeys.master.master_init.CHANNEL_ID_KEYS;
import static drpg.ddkeys.master.master_init.CHANNEL_NAME_KEYS;
import static drpg.ddkeys.master.master_init.CHOP_COUNT;
import static drpg.ddkeys.master.master_init.FISH_COUNT;
import static drpg.ddkeys.master.master_init.FORAGE_COUNT;
import static drpg.ddkeys.master.master_init.KEYBOARD_CONTEXT;
import static drpg.ddkeys.master.master_init.MINE_COUNT;
import static drpg.ddkeys.master.master_init.NOTIFICATION_ID;
import static drpg.ddkeys.master.master_init.PACKAGE_NAME;
import static drpg.ddkeys.master.master_init.TRAVEL_NOTIFICATION_ID;
import static drpg.ddkeys.master.master_init.advLBL;
import static drpg.ddkeys.master.master_init.autoHealCount;
import static drpg.ddkeys.master.master_init.autoPhealCount;
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
import static drpg.ddkeys.master.master_init.cdTen;
import static drpg.ddkeys.master.master_init.contains;
import static drpg.ddkeys.master.master_init.currentloc;
import static drpg.ddkeys.master.master_init.customTimer;
import static drpg.ddkeys.master.master_init.discordopens;
import static drpg.ddkeys.master.master_init.endtime;
import static drpg.ddkeys.master.master_init.healcount;
import static drpg.ddkeys.master.master_init.hiddenop1;
import static drpg.ddkeys.master.master_init.isAdvTimer;
import static drpg.ddkeys.master.master_init.isSearching;
import static drpg.ddkeys.master.master_init.isSide1;
import static drpg.ddkeys.master.master_init.isSide2;
import static drpg.ddkeys.master.master_init.isSide3;
import static drpg.ddkeys.master.master_init.isSide4;
import static drpg.ddkeys.master.master_init.isSideTimer;
import static drpg.ddkeys.master.master_init.isTravelling;
import static drpg.ddkeys.master.master_init.isparty;
import static drpg.ddkeys.master.master_init.keyboard;
import static drpg.ddkeys.master.master_init.kv;
import static drpg.ddkeys.master.master_init.mobcount;
import static drpg.ddkeys.master.master_init.moptions2;
import static drpg.ddkeys.master.master_init.myLife;
import static drpg.ddkeys.master.master_init.nBuilder;
import static drpg.ddkeys.master.master_init.nManager;
import static drpg.ddkeys.master.master_init.petLife;
import static drpg.ddkeys.master.master_init.phealcount;
import static drpg.ddkeys.master.master_init.prefix;
import static drpg.ddkeys.master.master_init.previousloc;
import static drpg.ddkeys.master.master_init.setendtime;
import static drpg.ddkeys.master.master_init.sidesTimeOFF;
import static drpg.ddkeys.master.master_init.sidesTimeON;
import static drpg.ddkeys.master.master_init.stdTheme;
import static drpg.ddkeys.master.master_init.themeChoice;
import static drpg.ddkeys.master.master_init.timeleft;
import static drpg.ddkeys.master.master_init.travelkeyboard;
import static drpg.ddkeys.master.master_init.vibarteAdv;
import static drpg.ddkeys.master.master_init.vibrateme;
import static drpg.ddkeys.master.master_init.xpRing;
import static drpg.ddkeys.master.master_init.xp_or_ring;
import static drpg.ddkeys.master.master_init.xprate;


public class ddKeys_Master extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private final KeyEvent kd = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER);
    private final KeyEvent ku = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER);
    private InputConnection ic;
    private boolean isHealed = false;
    private boolean isPhealed = false;
    private boolean isStats = false;
    private boolean isPet = false;
    private int advcd = 14000;
    private Connections location_id = new Connections();
    private TravelTime location_time = new TravelTime();
    private InputMethodManager im;


    //primary
    private String adv = prefix + "adv";

    private boolean installed;

    private void isAppInstalled() {
        PackageManager pm = getPackageManager();
        installed = false;
        try {
            pm.getPackageInfo("com.discord", PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public void onInitializeInterface() {
        super.onInitializeInterface();

        //keyboard mode change
        keyboard = new Keyboard(this, R.xml.drpgkey);
        travelkeyboard = new Keyboard(this, R.xml.travelkeys);
    }

    @SuppressLint("InflateParams")
    @Override
    //speCtre version
    public View onCreateInputView() {
        KEYBOARD_CONTEXT = getApplicationContext();
        PACKAGE_NAME = getApplicationContext().getPackageName();
        sp = getSharedPreferences("ddkeys", ddKeys_Master.MODE_PRIVATE);
        init_savedvalues(); //from sharedpreferences
        if (stdTheme) {                                         //Keyboad layout file change
            kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        } else {
            themechooser();
        }
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);

        nBuilder = new NotificationCompat.Builder(ddKeys_Master.this, CHANNEL_ID_KEYS); //initialize notification compact builder
        nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);        //initialize notification manager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nchannel =
                    new NotificationChannel(CHANNEL_ID_KEYS, CHANNEL_NAME_KEYS, NotificationManager.IMPORTANCE_HIGH);

            nchannel.setDescription(CHANNEL_DESCRIPTION_KEYS);
            nchannel.enableLights(true);
            nchannel.setLightColor(Color.CYAN);
            nManager.createNotificationChannel(nchannel);
        }

        timerStatusGet();   //sidestimer related
        if (isSideTimer) {
            sidesTimeON();
        } else if (!endtime && !isSideTimer) {
            timeleft = setendtime - System.currentTimeMillis();
            if (timeleft < 1000) {
                sidesTimeOFF();
                timerStatusSave(0, true);
            } else {
                sidesTime(timeleft);
                sidesTimeON();
            }
        }
        advMode();          //checking and updating keys :adv or partyadv mode

        isAppInstalled();               //checking whether this package installed on the device or not
        if (installed) {
            Intent opendiscord = new Intent(getPackageManager().getLaunchIntentForPackage("com.discord"));
            discordopens = PendingIntent.getActivity(ddKeys_Master.this, 0, opendiscord, 0);
        }
        if (vibarteAdv) {
            vibrateme = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        }
        if (!moptions2) {
            keyboard.getKeys().get(8).label = "Search";
        }

        myPlace(currentloc);
        kv.invalidateAllKeys();
        return kv;
    }

    @Override
    public void onKey(final int primaryCode, int[] keyCodes) {
        ic = getCurrentInputConnection();

        switch (primaryCode) {
            case 333:
                final IBinder token = this.getWindow().getWindow().getAttributes().token;

                boolean switchime = im.shouldOfferSwitchingToNextInputMethod(token);
                if (switchime) {
                    try {
                        im.switchToNextInputMethod(token, false);
                    } catch (Throwable t) { // java.lang.NoSuchMethodError if API_level<11
                        Log.e(TAG, "cannot set the previous input method:");
                        t.printStackTrace();
                    }
                } else im.showInputMethodPicker();
                break;
            case 334:
                if (!moptions2) {
                    if (isSearching) {
                        return;
                    }
                    directSend(prefix + "search ");
                    searchTiming();
                } else {
                    kv.setKeyboard(travelkeyboard);
                }
                break;
            case 335:
                kv.setKeyboard(keyboard);
                break;
            case 336:
                ddK_Activity.openpref = true;
                Intent myapp = new Intent(this, ddK_Activity.class);
                myapp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myapp);
                break;

            case 887:
                if (cdTen) {
                    return;
                }
                if (xp_or_ring) {
                    directSend(prefix + "assign xpboost all");
                } else {
                    if (xpRing) {
                        directSend(prefix + "use ring of strength");
                        xpRing = !xpRing;
                        saveBoolean("xp_ring", xpRing);
                    } else {
                        directSend(prefix + "use ring of xp");
                        xpRing = !xpRing;
                        saveBoolean("xp_ring", xpRing);
                    }
                }
                new CountDownTimer(10000, 5000) {
                    @Override
                    public void onTick(long l) {
                        cdTen = true;
                    }

                    @Override
                    public void onFinish() {
                        cdTen = false;
                        if (!isAdvTimer)
                            kv.invalidate();
                    }
                }.start();
                break;
            case 888:
                if (customTimer)
                    return;
                advKey(advcd);
                /*Random random = new Random();                     //To generate random colored progress for adv CD
                int nextInt = random.nextInt(256 * 256 * 256);
                colorCode = String.format("#%06x", nextInt);*/
                break;
            case 889:
                if (isAdvTimer)
                    advtimer.cancel();
                isparty = !isparty;
                advMode();
                editor = sp.edit();
                editor.putBoolean("advMode", isparty);
                editor.apply();
                break;
            case 900:
                if (isStats)
                    return;
                directSend(prefix + "stats ");
                isStats = true;
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        keyboard.getKeys().get(10).label = "stats";
                        kv.invalidateKey(10);
                    }

                    public void onFinish() {
                        isStats = false;
                        keyboard.getKeys().get(10).label = "STATS";
                        kv.invalidateKey(10);
                    }
                }.start();

                break;
            case 901:
                if (isPet)
                    return;
                directSend(prefix + "pet ");
                isPet = true;
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        keyboard.getKeys().get(11).label = "pet";
                        kv.invalidateKey(11);
                    }

                    public void onFinish() {
                        isPet = false;
                        keyboard.getKeys().get(11).label = "PET";
                        kv.invalidateKey(11);
                    }
                }.start();
                break;
            case 980:                                           //Changes xprate
                String pxp;
                if (xprate) {
                    pxp = prefix + "pset xprate 100 ";
                    xprate = false;
                } else {
                    pxp = prefix + "pset xprate 0 ";
                    xprate = true;
                }
                directSend(pxp);
                saveBoolean("xprate", xprate);          //Saves current key state for xprate for pet|user
                break;
            case 500:
                directSend(prefix + "guild ");
                break;
            case 903:
                if (isHealed || hiddenop1) {
                    return;
                }
                String heal = "heal auto ";
                directSend(prefix + heal);
                isHealed = true;
                healcount = 0;
                editor = sp.edit();
                editor.putInt("heal_count", healcount);
                editor.apply();
                myLife = Math.round(40 / autoHealCount * healcount);

                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        keyboard.getKeys().get(12).label = "healed";
                        kv.invalidateKey(12);
                    }

                    public void onFinish() {
                        isHealed = false;
                        keyboard.getKeys().get(12).label = "HEAL";
                        kv.invalidateKey(12);
                    }
                }.start();
                break;
            case 904:
                if (isPhealed)
                    return;
                String pheal = "pheal auto ";
                directSend(prefix + pheal);
                isPhealed = true;
                phealcount = 0;
                petLife = Math.round(40 / autoPhealCount * phealcount);
                editor = sp.edit();
                editor.putInt("pheal_count", phealcount);
                editor.apply();
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        keyboard.getKeys().get(13).label = "phealed";
                        kv.invalidateKey(13);
                    }

                    public void onFinish() {
                        isPhealed = false;
                        keyboard.getKeys().get(13).label = "PHEAL";
                        kv.invalidateKey(13);
                    }
                }.start();
                break;
            case 905:
                if (contains(chop_restr, currentloc)) {
                    keyboard.getKeys().get(2).label = "can't chop";
                    return;
                }
                if (isTravelling) {
                    Toast.makeText(KEYBOARD_CONTEXT, "Travelling...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isSide1) {
                    directSend(prefix + "chop ");
                    isSide1 = true;
                    keyboard.getKeys().get(2).label = "chopped";
                    saveLong("chop_count", CHOP_COUNT += 1);
                }
                if (isSideTimer) {
                    return;
                }
                nManager.cancel(NOTIFICATION_ID);
                startTimer();
                break;
            case 906:
                if (contains(forage_restr, currentloc)) {
                    keyboard.getKeys().get(4).label = "can't forage";
                    return;
                }
                if (isTravelling) {
                    Toast.makeText(KEYBOARD_CONTEXT, "Travelling...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isSide2) {
                    directSend(prefix + "forage ");
                    isSide2 = true;
                    keyboard.getKeys().get(4).label = "foraged";
                    saveLong("forage_count", FORAGE_COUNT += 1);
                }
                if (isSideTimer || !endtime) {
                    return;
                }
                nManager.cancel(NOTIFICATION_ID);
                startTimer();
                break;
            case 907:
                if (contains(mine_restr, currentloc)) {
                    keyboard.getKeys().get(1).label = "can't mine";
                    return;
                }
                if (isTravelling) {
                    Toast.makeText(KEYBOARD_CONTEXT, "Travelling...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isSide3) {
                    directSend(prefix + "mine ");
                    isSide3 = true;
                    keyboard.getKeys().get(1).label = "mined";
                    saveLong("MINE_count", MINE_COUNT += 1);
                }
                if (isSideTimer) {
                    return;
                }
                nManager.cancel(NOTIFICATION_ID);
                startTimer();
                break;
            case 908:
                if (contains(fish_restr, currentloc)) {
                    keyboard.getKeys().get(5).label = "can't fish";
                    return;
                }
                if (isTravelling) {
                    Toast.makeText(KEYBOARD_CONTEXT, "Travelling...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isSide4) {
                    directSend(prefix + "fish ");
                    isSide4 = true;
                    keyboard.getKeys().get(5).label = "fished";
                    saveLong("fish_count", FISH_COUNT += 1);
                }
                if (isSideTimer) {
                    return;
                }
                nManager.cancel(NOTIFICATION_ID);
                startTimer();
                break;
            //Longpress command for sides without timer
            case 401:
                directSend(prefix + "mine ");
                break;
            case 402:
                directSend(prefix + "chop ");
                break;
            case 403:
                directSend(prefix + "forage ");
                break;
            case 404:
                directSend(prefix + "fish ");
                break;
            case 667:                       //Force reset sidesTimer
                if (sidetimer != null) {
                    sidetimer.cancel();
                }
                ddKeysView.sTime = "do!";
                sidesTimeOFF();
                timerStatusSave(System.currentTimeMillis(), true);
                break;
            case 909:                       //send #!search
                if (isTravelling)
                    return;
                if (isSearching)
                    return;
                directSend(prefix + "search ");
                searchTiming();
                break;
            case 622:                       //Forcce reset searchTimer
                if (isSearching) {
                    isSearching = false;
                    if (searchtimer != null) {
                        searchtimer.cancel();
                    }
                    saveBoolean("is_searching", isSearching);
                    travelkeyboard.getKeys().get(7).icon = ContextCompat.getDrawable(ddKeys_Master.this, R.drawable.ic_search);
                    Toast.makeText(getBaseContext(), "SEARCH key re-enabled", Toast.LENGTH_LONG).show();
                    kv.invalidateAllKeys();
                } else return;
                break;
            case 911:
                directSend(prefix + "inv ");
                break;
            case 999:
                directSend(prefix + "ping ");
                break;
            case 977:
                directSend(prefix + "skills ");
                break;
            case 978:
                directSend(prefix + "lead ");
                break;
            case 979:
                directSend(prefix + "lead kills ");
                break;
            case 981:
                directSend(prefix + "achievements ");
                break;
            case 982:
                directSend(prefix + "attribs ");
                break;
            case 983:
                directSend(prefix + "lead gold ");
                break;
            case 984:
                directSend(prefix + "lead deaths ");
                break;
            case 985:
                directSend(prefix + "lead lux ");
                break;
            case 945:
                directSend(prefix + "gblist ");
                break;
            case 946:
                directSend(prefix + "gmglist ");
                break;
            case 947:
                directSend(prefix + "gmlist ");
                break;
            case 998:
                directSend(prefix + "ping ");
                break;
            case 700:
                directSend(prefix + "location ");
                break;
            case 699:
                directSend(prefix + "map ");
                break;
            //travelKeyboard
            case 633:   //Force reset travelTimer
                if (isTravelling) {
                    isTravelling = false;
                    if (traveltimer != null) {
                        traveltimer.cancel();
                    }
                    nManager.cancel(TRAVEL_NOTIFICATION_ID);
                    Toast.makeText(getBaseContext(), "travelTimer reset", Toast.LENGTH_LONG).show();
                    myPlace(currentloc);
                    kv.invalidateAllKeys();
                } else return;
                break;
            case 777:   //switch back to previous location
                currentloc = previousloc;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Previous location set on ddKeys", Toast.LENGTH_LONG).show();
                saveInteger("current_loc", currentloc);
                break;
            //Send command to travel from one place to respective connected places
            case 701:    //NW
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getNw();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getNw()]);
                travelTiming(TravelTime.getNw());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 702:    //N
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getN();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getN()]);
                travelTiming(TravelTime.getN());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 703:    //NE
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getNe();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getNe()]);
                travelTiming(TravelTime.getNe());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 704:    //W
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getW();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getW()]);
                travelTiming(TravelTime.getW());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 705:    //E
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getE();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getE()]);
                travelTiming(TravelTime.getE());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 706:    //SW
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getSw();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getSw()]);
                travelTiming(TravelTime.getSw());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 707:    //S
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getS();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getS()]);
                travelTiming(TravelTime.getS());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            case 708:    //SE
                if (isTravelling)
                    return;
                currentloc = previousloc;
                currentloc = Connections.getSe();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                directSend(prefix + "travel " + locations[Connections.getSe()]);
                travelTiming(TravelTime.getSe());
                savelocation();
                kv.setKeyboard(keyboard);
                break;
            //Changes location in ddKeys without sending a command
            case 1701:    //NW
                currentloc = previousloc;
                currentloc = Connections.getNw();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1702:    //N
                currentloc = previousloc;
                currentloc = Connections.getN();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1703:    //NE
                currentloc = previousloc;
                currentloc = Connections.getNe();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1704:    //W
                currentloc = previousloc;
                currentloc = Connections.getW();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1705:    //E
                currentloc = previousloc;
                currentloc = Connections.getE();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1706:    //SW
                currentloc = previousloc;
                currentloc = Connections.getSw();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1707:    //S
                currentloc = previousloc;
                currentloc = Connections.getS();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            case 1708:    //SE
                currentloc = previousloc;
                currentloc = Connections.getSe();
                if (currentloc == 0) {
                    Toast.makeText(KEYBOARD_CONTEXT, "xx DEAD END xx", Toast.LENGTH_SHORT).show();
                    return;
                }
                myPlace(currentloc);
                savelocation();
                break;
            //Talk command with respective NPCs
            case 711:
                String npc1 = NPCs.getNpc1();
                if (isTravelling || npc1 == null)
                    return;
                directSend(prefix + "talk " + npc1);
                talkTimer(12, npc1);
                break;
            case 712:
                String npc2 = NPCs.getNpc2();
                if (isTravelling || npc2 == null)
                    return;
                directSend(prefix + "talk " + npc2);
                talkTimer(13, npc2);
                break;
            case 713:
                String npc3 = NPCs.getNpc3();
                if (isTravelling || npc3 == null)
                    return;
                directSend(prefix + "talk " + npc3);
                talkTimer(14, npc3);
                break;
            case 714:
                String npc4 = NPCs.getNpc4();
                if (isTravelling || npc4 == null)
                    return;
                directSend(prefix + "talk " + npc4);
                talkTimer(15, npc4);
                break;
            //Task related, currently on longpress tkey12
            case 720:
                directSend(prefix + "task check ");
                break;
            case 721:
                directSend(prefix + "task accept ");
                break;
            //Tagbot tags
            case 725:
                directSend("tagbot primeval");
                break;
            case 726:
                directSend("tagbot map");
                break;
            //Runes to get teleported
            case 780:
                directSend(prefix + "use skrune");
                previousloc = currentloc;
                currentloc = 1;
                myPlace(currentloc);
                new masterNotificationManager(200);
                Toast.makeText(getBaseContext(), "Teleported to Silverkeep", Toast.LENGTH_LONG).show();
                savelocation();
                break;
            case 781:
                directSend(prefix + "use cfrune");
                previousloc = currentloc;
                currentloc = 2;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to copperfall", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            case 782:
                directSend(prefix + "use rmrune");
                previousloc = currentloc;
                currentloc = 4;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to Rivermouth", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            case 783:
                directSend(prefix + "use merune");
                previousloc = currentloc;
                currentloc = 11;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to Miree", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            case 784:
                directSend(prefix + "use sarune");
                previousloc = currentloc;
                currentloc = 19;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to Sariila", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            case 785:
                directSend(prefix + "use frrune");
                previousloc = currentloc;
                currentloc = 15;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to Frostridge", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            case 786:
                directSend(prefix + "use rvgrune");
                previousloc = currentloc;
                currentloc = 36;
                myPlace(currentloc);
                Toast.makeText(getBaseContext(), "Teleported to Ravengate", Toast.LENGTH_LONG).show();
                new masterNotificationManager(200);
                savelocation();
                break;
            //Custom Commands
            case 201:
                directSend(cckeys_left);
                break;
            case 202:

                directSend(cckeys_right);
                break;
            case 211:
                directSend(cc_left1);
                break;
            case 212:
                directSend(cc_left2);
                break;
            case 213:
                directSend(cc_left3);
                break;
            case 214:
                directSend(cc_left4);
                break;
            case 215:
                directSend(cc_left5);
                break;
            case 216:
                directSend(cc_left6);
                break;
            case 221:
                directSend(cc_right1);
                break;
            case 222:
                directSend(cc_right2);
                break;
            case 223:
                directSend(cc_right3);
                break;
            case 224:
                directSend(cc_right4);
                break;
            case 225:
                directSend(cc_right5);
                break;
            case 226:
                directSend(cc_right6);
                break;

            default:
                char code = (char) primaryCode;
                ic.commitText(String.valueOf(code), 1);
        }
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void advKey(int counter) {
        directSend(prefix + adv);
        if (isAdvTimer)
            return;
        mobcount += 1;
        healcount += 1;
        phealcount += 1;
        ADV_COUNT += 1;
        petLife = Math.round(40 / autoPhealCount * phealcount);
        myLife = Math.round(40 / autoHealCount * healcount);
        editor = sp.edit();
        editor.putLong("mob_count", mobcount);
        editor.putLong("adv_count", ADV_COUNT);
        editor.putInt("heal_count", healcount);
        editor.putInt("pheal_count", phealcount);
        editor.apply();
        advtiming(counter);
    }

    private void directSend(String cmnd) {
        ic.commitText(cmnd, 1);
        ic.sendKeyEvent(kd);
        ic.sendKeyEvent(ku);
    }

    private void advMode() {
        if (isparty) {
            adv = "padv ";
            advcd = 20000;
            advLBL = "pADV";
        } else {
            adv = "adv ";
            advcd = 14000;
            advLBL = "ADV";
        }
        isAdvTimer = false;
        keyboard.getKeys().get(0).label = advLBL + "";
        kv.invalidateKey(0);
    }

    private void savelocation() {
        editor = sp.edit();
        editor.putInt("current_loc", currentloc);
        editor.putInt("previous_loc", previousloc);
        editor.apply();
    }

    private void themechooser() {
        switch (themeChoice) {
            case 0:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard, null);
                break;
            case 1:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_2, null);
                break;
            case 2:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_3, null);
                break;
            case 3:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_4, null);
                break;
            case 4:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_5, null);
                break;
            case 5:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_6, null);
                break;
            case 6:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard_7, null);
                break;
            default:
                kv = (KeyboardView) getLayoutInflater().inflate(R.layout.blaze_keyboard, null);
        }

    }
}