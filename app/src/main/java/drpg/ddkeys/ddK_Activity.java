package drpg.ddkeys;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import drpg.ddkeys.app.about;
import drpg.ddkeys.app.faqKeys;
import drpg.ddkeys.app.homeKeys;
import drpg.ddkeys.app.preferenceKeys;
import drpg.ddkeys.app.settings;

import static drpg.ddkeys.ddKeys_init.ddkNbuilder;
import static drpg.ddkeys.ddKeys_init.ddkNmanager;
import static drpg.ddkeys.master.masterSharedPreference.init_savedvalues;
import static drpg.ddkeys.master.masterSharedPreference.sp;
import static drpg.ddkeys.master.master_init.APP_CONTEXT;


public class ddK_Activity extends AppCompatActivity {

    public static boolean openpref = false;
    private ActionBarDrawerToggle keysToggle;
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private Fragment ddKeys_fragment = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddk);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#2b2f3a")); //background color
        APP_CONTEXT = getApplicationContext();
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //SharedPreference values
        sp = getSharedPreferences("ddkeys", ddKeys_Master.MODE_PRIVATE);
        init_savedvalues();

        Toolbar keysToolbar;
        Fragment firstFrag;

        keysToolbar = findViewById(R.id.ddk_actionbar);
        setSupportActionBar(keysToolbar);

        DrawerLayout keysDrawer = findViewById(R.id.ddkeyDrawer);
        keysToggle = new ActionBarDrawerToggle(this, keysDrawer, R.string.open, R.string.close);

        keysDrawer.addDrawerListener(keysToggle);
        navigationView = findViewById(R.id.nView);
        navigationView.setBackgroundColor(Color.parseColor("#2b2f3a")); //color or navigation drawer
        keysToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (openpref) {
            firstFrag = new preferenceKeys();
            navigationView.getMenu().getItem(1).setChecked(true);
        } else {
            firstFrag = new homeKeys();
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragContent, firstFrag).commit();
        openpref = false; //registering back to false so when opening app, fragment homeKeys should open

        setupDrawerContent(navigationView);


        keysDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Called when a drawer's position changes.

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //Called when a drawer has settled in a completely open state.
                //The drawer is interactive at this point.
                // If you have 2 drawers (left and right) you can distinguish
                // them by using id of the drawerView. int id = drawerView.getId();
                // id will be your layout's id: for example R.id.left_drawer
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Called when a drawer has settled in a completely closed state.
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
                hideKeyboard(ddK_Activity.this);

            }
        });

        ddkNbuilder = new NotificationCompat.Builder(ddK_Activity.this, ddKeys_init.CHANNEL_ID);
        ddkNmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nchannel =
                    new NotificationChannel(ddKeys_init.CHANNEL_ID, ddKeys_init.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            nchannel.setDescription(ddKeys_init.CHANNEL_DESCRIPTION);
            nchannel.enableLights(true);
            nchannel.setLightColor(Color.BLUE);
            ddkNmanager.createNotificationChannel(nchannel);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideKeyboard(this);
        return keysToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void selectItemDrawer(MenuItem menuItem) {

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.homeKeys:
                fragmentClass = homeKeys.class;
                break;
            case R.id.preferenceKeys:
                fragmentClass = preferenceKeys.class;
                break;
            case R.id.settings:
                fragmentClass = settings.class;
                break;
            case R.id.aboutKeys:
                fragmentClass = about.class;
                break;
            case R.id.faqKeys:
                fragmentClass = faqKeys.class;
                break;

            default:
                fragmentClass = homeKeys.class;
        }
        try {
            ddKeys_fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragContent, ddKeys_fragment).commit();
        uncheckMenu();
        menuItem.setChecked(true);
        //keysDrawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    private void uncheckMenu() {
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    public void toastOnPress() {
        Toast.makeText(getBaseContext(), "Your ddKeys has been updated.", Toast.LENGTH_LONG).show();
    }

    public void toastOnPressError() {
        int rand = (int) (1 + Math.random() * 5);
        switch (rand) {
            case 1:
                Toast.makeText(getBaseContext(), "Sorry..! I don't get you.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getBaseContext(), "Oof..! Am confused", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getBaseContext(), "Something went wrong, Try again.", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getBaseContext(), "What? Please try again ", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getBaseContext(), "I couldn't update ddKeys ", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getBaseContext(), "Something is wrong on this, Check again. ", Toast.LENGTH_SHORT).show();
        }

    }

    public void showIMS() {
        InputMethodManager ims = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ims.showInputMethodPicker();
    }

}
