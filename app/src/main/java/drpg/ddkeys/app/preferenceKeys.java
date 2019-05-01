package drpg.ddkeys.app;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import drpg.ddkeys.R;
import drpg.ddkeys.ddK_Activity;
import drpg.ddkeys.master.masterNotificationManager;

import static drpg.ddkeys.master.masterSharedPreference.init_savedvalues;
import static drpg.ddkeys.master.masterSharedPreference.saveBoolean;
import static drpg.ddkeys.master.masterSharedPreference.saveInteger;
import static drpg.ddkeys.master.masterSharedPreference.saveLong;
import static drpg.ddkeys.master.masterSharedPreference.saveString;
import static drpg.ddkeys.master.masterSharedPreference.sp;
import static drpg.ddkeys.master.master_init.APP_CONTEXT;
import static drpg.ddkeys.master.master_init.autoHealCount;
import static drpg.ddkeys.master.master_init.autoPhealCount;
import static drpg.ddkeys.master.master_init.autohealing;
import static drpg.ddkeys.master.master_init.autophealing;
import static drpg.ddkeys.master.master_init.hiddenop1;
import static drpg.ddkeys.master.master_init.mobcount;
import static drpg.ddkeys.master.master_init.moptions1;
import static drpg.ddkeys.master.master_init.moptions2;
import static drpg.ddkeys.master.master_init.moptions3;
import static drpg.ddkeys.master.master_init.moptions4;
import static drpg.ddkeys.master.master_init.moptions5;
import static drpg.ddkeys.master.master_init.moptions6;
import static drpg.ddkeys.master.master_init.prefix;
import static drpg.ddkeys.master.master_init.stdTheme;
import static drpg.ddkeys.master.master_init.themeChoice;
import static drpg.ddkeys.master.master_init.vibarteAdv;
import static drpg.ddkeys.master.master_init.xpRing;
import static drpg.ddkeys.master.master_init.xp_or_ring;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link preferenceKeys.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link preferenceKeys#newInstance} factory method to
 * create an instance of this fragment.
 */
public class preferenceKeys extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public preferenceKeys() {
        // Required empty public constructor
    }

    public static preferenceKeys newInstance(String param1, String param2) {
        preferenceKeys fragment = new preferenceKeys();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View prefView = inflater.inflate(R.layout.fragment_preference_keys, container, false);
        //change title
        ((ddK_Activity) getActivity())
                .setActionBarTitle("Preference");
        TabLayout tabLayout;
        ViewPager viewPager;
        KeysPagerAdapter keysPagerAdapter;

        init_savedvalues();
        keysPagerAdapter = new KeysPagerAdapter(getChildFragmentManager());

        tabLayout = prefView.findViewById(R.id.tabLay);
        tabLayout.setTabTextColors(Color.parseColor("#f2f2f2"), Color.parseColor("#b2b2ff"));
/*
        tabLayout.addTab(tabLayout.newTab().setText("Change Prefix"));
        tabLayout.addTab(tabLayout.newTab().setText("Custom Command"));
        tabLayout.addTab(tabLayout.newTab().setText("Heal Indicator"));
*/
        viewPager = prefView.findViewById(R.id.vPage);

        MyFragment1 fragment1 = new MyFragment1();
        keysPagerAdapter.addFragment(fragment1, getResources().getString(R.string.new_prefix));
        MyFragment2 fragment2 = new MyFragment2();
        keysPagerAdapter.addFragment(fragment2, getResources().getString(R.string.custom_commands));
        MyFragment3 fragment3 = new MyFragment3();
        keysPagerAdapter.addFragment(fragment3, getResources().getString(R.string.health_indicator));
        MyFragment4 fragment4 = new MyFragment4();
        keysPagerAdapter.addFragment(fragment4, getResources().getString(R.string.more_options));
        viewPager.setAdapter(keysPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Hide the keyboard.
                ddK_Activity.hideKeyboard(getActivity());
            }
        });

        return prefView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static class MyFragment1 extends Fragment {  // Change prefix fragment

        private EditText pfKey1;
        private ImageButton bPrefix;
        private String prefixPF;

        private boolean colorHex(String hexcolor) {
            Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6})$");
            Matcher matcher = pattern.matcher(hexcolor);
            return (matcher.matches());
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.subfragment_change_prefix, container, false);

            pfKey1 = rootView.findViewById(R.id.pfKey_1);
            bPrefix = rootView.findViewById(R.id.btn_prefix);
            pfKey1.setHint(prefix);

            bPrefix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefixPF = pfKey1.getText().toString();
                    if (prefixPF.equals("") || prefixPF.contains("reset YES")) {
                        ((ddK_Activity) getActivity()).toastOnPressError();
                    } else if (prefixPF.equals("iwanttoDie")) {
                        hiddenop1 = true;
                        saveBoolean("userHeal", true);
                        Toast.makeText(getContext(), "HEAL key Disabled, You are going to die !", Toast.LENGTH_SHORT).show();
                        pfKey1.setText("");
                    } else if (prefixPF.equals("iwanttoLive")) {
                        hiddenop1 = false;
                        saveBoolean("userHeal", false);
                        Toast.makeText(getContext(), "HEAL key Enabled, Heal and Stay Up ;-)", Toast.LENGTH_SHORT).show();
                        pfKey1.setText("");
                    } else if (prefixPF.equals("vibrateMe")) {
                        vibarteAdv = !vibarteAdv;
                        saveBoolean("vibrateAdv", vibarteAdv);
                        Toast.makeText(getContext(), "Vibration settings changed", Toast.LENGTH_SHORT).show();
                        pfKey1.setText("");
                    } else if (prefixPF.equals("changeTheme")) {
                        stdTheme = !stdTheme;
                        saveBoolean("std_theme", stdTheme);
                        Toast.makeText(getContext(), "ddKeys theme changed", Toast.LENGTH_SHORT).show();
                        pfKey1.setText("");
                    } else if (prefixPF.contains("hEx")) {
                        String regex = "hEx";
                        prefixPF = prefixPF.replace(regex, "");
                        if (colorHex(prefixPF)) {
                            saveString("adv_color", prefixPF);
                            Toast.makeText(getContext(), "ADV progress color changed to " + prefixPF, Toast.LENGTH_SHORT).show();
                            pfKey1.setText("");
                        } else {
                            Toast.makeText(getContext(), "Given hex code is not valid (eg: #b2b2ff)", Toast.LENGTH_SHORT).show();
                        }
                    } else if (prefixPF.contains("thEme")) {
                        prefixPF = prefixPF.replaceAll("thEme", "");
                        try {
                            themeChoice = Integer.parseInt(prefixPF.trim());
                            Toast.makeText(getContext(), "Theme background image changed [item " + themeChoice + "]", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException err) {
                            Toast.makeText(getContext(), "That is a bad choice, try like this: thEme5", Toast.LENGTH_SHORT).show();
                            themeChoice = 0;
                        }
                        saveInteger("theme_choice", themeChoice);

                    } else {
                        saveString("prefix", prefixPF);
                        ((ddK_Activity) getActivity()).toastOnPress();
                        pfKey1.setText("");
                        pfKey1.setHint(prefixPF);
                    }
                }
            });

            return rootView;
        }
    }

    public static class MyFragment2 extends Fragment implements AdapterView.OnItemSelectedListener {   //Custom commands fragment

        private EditText pfKey4;
        private ImageButton bCcmd1;
        private String ccKeya;
        private Spinner spinner;
        private Spinner spinner_type;
        private Spinner spinner_pos;

        private int position = 0;
        private int type = 0;
        private int item = 0;
        private String sharedpref_name = "cc_left";

        private String sp_name() {
            return (type == 0 ? "cc_" : "ccL_") + (position == 0 ? "left" : "right") + (item > 0 ? "" + item : "");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.subfragment_custom_command, container, false);

            pfKey4 = rootView.findViewById(R.id.pfKey_4);
            bCcmd1 = rootView.findViewById(R.id.btn_ccmd1);

            spinner = rootView.findViewById(R.id.cck_spinner);
            spinner_type = rootView.findViewById(R.id.cck_spinner_type);
            spinner_pos = rootView.findViewById(R.id.cck_spinner_pos);

            pfKey4.setHint(sp.getString(sharedpref_name, "not_found"));

            spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_ATOP);
            spinner_pos.getBackground().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_ATOP);
            spinner_type.getBackground().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_ATOP);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(APP_CONTEXT, R.array.cckeys_item, R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(APP_CONTEXT, R.array.cckeys_type, R.layout.simple_spinner_item);
            adapter_type.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spinner_type.setAdapter(adapter_type);
            spinner_type.setOnItemSelectedListener(this);

            ArrayAdapter<CharSequence> adapter_pos = ArrayAdapter.createFromResource(APP_CONTEXT, R.array.cckeys_pos, R.layout.simple_spinner_item);
            adapter_pos.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spinner_pos.setAdapter(adapter_pos);
            spinner_pos.setOnItemSelectedListener(this);

            bCcmd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ccKeya = pfKey4.getText().toString();
                    if (ccKeya.equals("") || ccKeya.contains("reset YES") || sharedpref_name.equals("ERR")) {
                        ((ddK_Activity) getActivity()).toastOnPressError();
                    } else if (ccKeya.equals("myStats")) {
                        new masterNotificationManager(777);
                        pfKey4.setText("");
                    } else {
                        saveString(sharedpref_name, ccKeya);
                        ((ddK_Activity) getActivity()).toastOnPress();
                        pfKey4.setText("");
                        pfKey4.setHint(ccKeya);
                    }
                }
            });

            return rootView;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            type = spinner_type.getSelectedItemPosition();
            position = spinner_pos.getSelectedItemPosition();
            item = spinner.getSelectedItemPosition();
            if (type == 1 && item == 0) {
                sharedpref_name = "ERR";
                pfKey4.setHint("You can't modify this");
            } else {
                sharedpref_name = sp_name();
                pfKey4.setHint(sp.getString(sharedpref_name, "not_found"));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public static class MyFragment3 extends Fragment {

        private EditText pfKey2;
        private EditText pfKey3;
        private Switch sw_heal;
        private Switch sw_pheal;
        private ImageButton bHeal;
        private ImageButton bPheal;
        private String healcountPF;
        private String phealcountPF;
        private LinearLayout lay_heal;
        private LinearLayout lay_pheal;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.subfragment_health_monitor, container, false);

            lay_heal = rootView.findViewById(R.id.layout_heal);
            lay_pheal = rootView.findViewById(R.id.layout_pheal);

            pfKey2 = rootView.findViewById(R.id.pfKey_2);
            pfKey3 = rootView.findViewById(R.id.pfKey_3);
            bHeal = rootView.findViewById(R.id.btn_heal);
            bPheal = rootView.findViewById(R.id.btn_pheal);
            sw_heal = rootView.findViewById(R.id.switch_heal);
            sw_pheal = rootView.findViewById(R.id.switch_pheal);

            pfKey2.setHint(autoHealCount + "");
            pfKey3.setHint(autoPhealCount + "");

            //Check the state of Health monitor and do accordance
            if (autohealing) {
                lay_heal.setVisibility(View.VISIBLE);
                sw_heal.setChecked(true);
            } else {
                lay_heal.setVisibility(View.GONE);
                sw_heal.setChecked(false);
            }
            if (autophealing) {
                lay_pheal.setVisibility(View.VISIBLE);
                sw_pheal.setChecked(true);
            } else {
                lay_pheal.setVisibility(View.GONE);
                sw_pheal.setChecked(false);
            }

//Set the health monitor status on SWITCH toggle and saves
            sw_heal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        lay_heal.setVisibility(View.VISIBLE);
                        saveBoolean("auto_heal", true);
                    } else {
                        lay_heal.setVisibility(View.GONE);
                        saveBoolean("auto_heal", false);
                    }
                }
            });
            sw_pheal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        lay_pheal.setVisibility(View.VISIBLE);
                        saveBoolean("auto_pheal", true);
                    } else {
                        lay_pheal.setVisibility(View.GONE);
                        saveBoolean("auto_pheal", false);
                    }
                }
            });

            //Save user input in edittext when button pressed
            bHeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    healcountPF = pfKey2.getText().toString();
                    if (healcountPF.equals("")) {
                        ((ddK_Activity) getActivity()).toastOnPressError();
                    } else {
                        saveInteger("healCount", Integer.valueOf(healcountPF));
                        ((ddK_Activity) getActivity()).toastOnPress();
                        pfKey2.setText("");
                        pfKey2.setHint(healcountPF);
                    }
                }
            });
            bPheal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phealcountPF = pfKey3.getText().toString();
                    if (phealcountPF.equals("")) {
                        ((ddK_Activity) getActivity()).toastOnPressError();
                    } else {
                        saveInteger("phealCount", Integer.valueOf(phealcountPF));
                        ((ddK_Activity) getActivity()).toastOnPress();
                        pfKey3.setText("");
                        pfKey3.setHint(phealcountPF);
                    }
                }
            });


            return rootView;
        }
    }

    public static class MyFragment4 extends Fragment {

        private Switch mo_sw1;
        private Switch mo_sw2;
        private Switch mo_sw3;
        private Switch mo_sw4;
        private Switch mo_sw5;
        private Switch mo_sw6;

        private TextView motext1;
        private TextView motext2;
        private TextView motext3;
        private TextView motext4;
        private TextView motext5;
        private TextView motext6;

        private TextView reset_advcount;
        private RadioButton choose_xp;
        private RadioButton choose_rings;
        private RadioGroup boostnring;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.subfragment_more_options, container, false);

            mo_sw1 = rootView.findViewById(R.id.mo_switch1);
            mo_sw2 = rootView.findViewById(R.id.mo_switch2);
            mo_sw3 = rootView.findViewById(R.id.mo_switch3);
            mo_sw4 = rootView.findViewById(R.id.mo_switch4);
            mo_sw5 = rootView.findViewById(R.id.mo_switch5);
            mo_sw6 = rootView.findViewById(R.id.mo_switch6);

            reset_advcount = rootView.findViewById(R.id.reset_advcount);
            motext1 = rootView.findViewById(R.id.mo_txt1);
            motext2 = rootView.findViewById(R.id.mo_txt2);
            motext3 = rootView.findViewById(R.id.mo_txt3);
            motext4 = rootView.findViewById(R.id.mo_txt4);
            motext5 = rootView.findViewById(R.id.mo_txt5);
            motext6 = rootView.findViewById(R.id.mo_txt6);

            choose_xp = rootView.findViewById(R.id.choose_xpboost);
            choose_rings = rootView.findViewById(R.id.choose_rings);
            boostnring = rootView.findViewById(R.id.boostnrings);

            if (moptions1) {
                mo_sw1.setChecked(true);
                motext1.setText(R.string.motext1t);
            }
            if (moptions2) {
                mo_sw2.setChecked(true);
                motext2.setText(R.string.motext2t);
            }
            if (moptions3) {
                mo_sw3.setChecked(true);
                motext3.setText(R.string.motext3t);
                boostnring.setVisibility(View.VISIBLE);
                if (xp_or_ring) {
                    choose_xp.setChecked(true);
                } else {
                    choose_rings.setChecked(true);
                }
            }
            if (moptions4) {
                mo_sw4.setChecked(true);
                motext4.setText(R.string.motext4t);
            }
            if (moptions5) {
                mo_sw5.setChecked(true);
                motext5.setText(R.string.motext5t);
            }
            if (moptions6) {
                mo_sw6.setChecked(true);
                motext6.setText(R.string.motext6t);
                reset_advcount.setVisibility(View.VISIBLE);
            }

            mo_sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption1", true);
                        motext1.setText(R.string.motext1t);
                        Toast.makeText(getContext(), "suggection: use Aldebaran bot for better results", Toast.LENGTH_LONG).show();

                    } else {
                        saveBoolean("moption1", false);
                        motext1.setText(R.string.motext1f);
                    }
                }
            });

            mo_sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption2", true);
                        motext2.setText(R.string.motext2t);
                    } else {
                        saveBoolean("moption2", false);
                        motext2.setText(R.string.motext2f);
                    }
                }
            });

            mo_sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption3", true);
                        motext3.setText(R.string.motext3t);
                        boostnring.setVisibility(View.VISIBLE);
                        if (xp_or_ring) {
                            choose_xp.setChecked(true);
                        } else {
                            choose_rings.setChecked(true);
                        }
                    } else {
                        saveBoolean("moption3", false);
                        motext3.setText(R.string.motext3f);
                        boostnring.setVisibility(View.GONE);
                    }
                }
            });

            mo_sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption4", true);
                        motext4.setText(R.string.motext4t);
                    } else {
                        saveBoolean("moption4", false);
                        motext4.setText(R.string.motext4f);
                    }
                }
            });

            mo_sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption5", true);
                        motext5.setText(R.string.motext5t);
                    } else {
                        saveBoolean("moption5", false);
                        motext5.setText(R.string.motext5f);
                    }
                }
            });

            mo_sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        saveBoolean("moption6", true);
                        motext6.setText(R.string.motext6t);
                        reset_advcount.setVisibility(View.VISIBLE);
                    } else {
                        saveBoolean("moption6", false);
                        motext6.setText(R.string.motext6f);
                        reset_advcount.setVisibility(View.GONE);
                    }
                }
            });

            reset_advcount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mobcount = 0;
                    saveLong("mob_count", mobcount);
                    Toast.makeText(getContext(), "Adventure count has been resetted", Toast.LENGTH_SHORT).show();
                }
            });

            boostnring.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    switch (id) {
                        case R.id.choose_xpboost:
                            xp_or_ring = true;
                            saveBoolean("xp_or_ring", xp_or_ring);
                            break;

                        case R.id.choose_rings:
                            xp_or_ring = false;
                            saveBoolean("xp_or_ring", xp_or_ring);
                            break;
                        default:
                            Toast.makeText(getContext(), "Something wrong went", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            return rootView;
        }


        public void onRadioButtonClicked(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.choose_xpboost:
                    if (checked) {
                        xpRing = true;
                        saveBoolean("xp_ring", xpRing);
                    }
                    break;
                case R.id.choose_rings:
                    if (checked) {
                        xpRing = false;
                        saveBoolean("xp_ring", xpRing);
                    }
                    break;
                default:
                    xpRing = true;
                    saveBoolean("xp_ring", xpRing);
            }
        }
    }

    class KeysPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        KeysPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }


}
