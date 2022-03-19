package com.example.breathalyzerapp.Views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.breathalyzerapp.R;

import android.os.Bundle;
import android.widget.TextView;

public class UndrunkActivity extends AppCompatActivity {
    protected Toolbar mainToolbar;
    private double currentBAC;
    private double timeEstimate;
    private TextView currentBACTextView;
    private TextView timeEstimateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undrunk);

        currentBAC = getIntent().getDoubleExtra("initialBAC", 0.00);
        timeEstimate = getIntent().getDoubleExtra("timeEstimate", 0.0);

        currentBACTextView = (TextView) findViewById(R.id.currentReadingTextView);
        timeEstimateTextView = (TextView) findViewById(R.id.timeTextView);

        currentBACTextView.setText("Current BAC: "+currentBAC);
        timeEstimateTextView.setText("Hours until below 0.08: "+timeEstimate);

        mainToolbar = findViewById(R.id.main_toolbar);

        setupUI();
    }

    void setupUI() {

        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}