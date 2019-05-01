package drpg.ddkeys.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import drpg.ddkeys.R;

import static android.content.ContentValues.TAG;

public class testKeys extends AppCompatActivity {
    private EditText testKeys;
    private TextView showtxt;
    private String entered = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_keys);
        Toolbar keysToolbar = findViewById(R.id.ddk_actionbar);
        setSupportActionBar(keysToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#2b2f3a"));
        getSupportActionBar().setTitle("Test ddKeys");

        keysToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ImageButton cleartext = findViewById(R.id.clear_text);
        showtxt = findViewById(R.id.textshow);

        testKeys = findViewById(R.id.testEdittext);
        testKeys.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i(TAG, "ENTER PRESSED");
                    entered = testKeys.getText().toString();
                    //showtxt.setText(entered);
                    showtxt.append("\n" + entered);
                    testKeys.setText("");
                    showtxt.setMovementMethod(new ScrollingMovementMethod());
                }
                return false;
            }
        });

        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entered = "";
                showtxt.setText("");
            }
        });


    }
}
