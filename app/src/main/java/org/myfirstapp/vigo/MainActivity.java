package org.myfirstapp.vigo;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView digitalClock;
    private CardView cardView;
    private ToggleButton toggleButton;
    private RelativeLayout container;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digitalClock = findViewById(R.id.digitalClock);
        cardView = findViewById(R.id.cardView);
        toggleButton = findViewById(R.id.toggleButton);
        container = findViewById(R.id.container);

        // Set initial background image based on the initial state of the toggle
        updateBackground(toggleButton.isChecked());

        // Update the time and background image when the toggle state changes
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateBackground(isChecked);
            }
        });

        // Update the time every second
        updateClock();
    }

    private void updateBackground(boolean isDayMode) {
        if (isDayMode) {
            container.setBackgroundResource(R.drawable.daytime); // Set the day mode background
        } else {
            container.setBackgroundResource(R.drawable.nightime); // Set the night mode background
        }
    }

    private void updateClock() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(calendar.getTime());

        // Update the TextView with the current time
        digitalClock.setText(currentTime);

        // Schedule the next update after 1 second
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClock();
            }
        }, 1000);
    }
}

