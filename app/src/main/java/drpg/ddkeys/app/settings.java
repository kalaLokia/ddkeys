package drpg.ddkeys.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import drpg.ddkeys.R;
import drpg.ddkeys.ddK_Activity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settings extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Fragment things
    private final Intent gd = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);

    private OnFragmentInteractionListener mListener;

    public settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settings.
     */
    public static settings newInstance(String param1, String param2) {
        settings fragment = new settings();
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
        final View settingsView = inflater.inflate(R.layout.fragment_settings, container, false);
        //Change title
        ((ddK_Activity) getActivity())
                .setActionBarTitle("Settings");
        Button enablekeys;
        Button choosekeys;
        Button testkeys;


        //Initiate fragment things
        enablekeys = settingsView.findViewById(R.id.btn_enable);
        choosekeys = settingsView.findViewById(R.id.btn_choose);
        testkeys = settingsView.findViewById(R.id.btn_test);

        //Actions to perform
        testkeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent66 = new Intent(getActivity(), testKeys.class);
                startActivity(intent66);
            }
        });

        enablekeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(gd);
            }
        });

        choosekeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ddK_Activity) getActivity())
                        .showIMS();
            }
        });


        return settingsView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
