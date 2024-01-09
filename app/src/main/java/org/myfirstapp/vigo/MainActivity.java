package org.myfirstapp.vigo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView digitalClock;
    private FrameLayout frameLayout;
    private ToggleButton toggleButton;
    private RelativeLayout container;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digitalClock = findViewById(R.id.digitalClock);
        frameLayout = findViewById(R.id.frameLayout);
        toggleButton = findViewById(R.id.toggleButton);
        container = findViewById(R.id.container);
        Button btnAboutApp = findViewById(R.id.btnAboutApp);

        updateBackground(toggleButton.isChecked());

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateBackground(isChecked);
            }
        });

        updateClock();

        btnAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to start AboutAppActivity
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateBackground(boolean isDayMode) {
        // Remove the delay
        if (isDayMode) {
            container.setBackgroundResource(R.drawable.daytime);
        } else {
            container.setBackgroundResource(R.drawable.nightime);
        }
    }


    private void updateClock() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(calendar.getTime());

        digitalClock.setText(currentTime);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClock();
            }
        }, 1000);
    }
}
