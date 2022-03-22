package com.example.breathalyzerapp.Views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.breathalyzerapp.R;
import com.jjoe64.graphview.GraphView;  // source - https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class UndrunkActivity extends AppCompatActivity {
    protected Toolbar mainToolbar;
    private double currentBAC;
    private double timeEstimate;
    private TextView currentBACTextView;
    private TextView timeEstimateTextView;
    private GraphView plot;
    private Calendar time;
    private Calendar safeToDriveAt;
    private String safeTimeStr;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_undrunk);
        time = Calendar.getInstance();
        safeToDriveAt = Calendar.getInstance();

        currentBAC = getIntent().getDoubleExtra("initialBAC", 0.00);
        timeEstimate = getIntent().getDoubleExtra("timeEstimate", 0.0);

        mainToolbar = findViewById(R.id.main_toolbar);

        setupUI();
    }

    void setupUI() {

        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupPlot();
        setupTextViews();
    }

    void setupPlot() {
        plot = findViewById(R.id.bacPlot);
        int xInitial = (int)time.get(Calendar.HOUR_OF_DAY);
        int xFinal = (int) ((int)time.get(Calendar.HOUR_OF_DAY) + timeEstimate);                        // TODO this is not good

        LineGraphSeries<DataPoint> data = new LineGraphSeries<DataPoint>(new DataPoint[] {
            new DataPoint(xInitial, currentBAC),
            new DataPoint(xFinal, 0.08)
        });

        LineGraphSeries<DataPoint> legalLine = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(xInitial, 0.08),
                new DataPoint(xFinal, 0.08)
        });
        legalLine.setColor(Color.GREEN);

        plot.setTitle("Your Blood Alcohol Level");
        plot.getGridLabelRenderer().setVerticalAxisTitle("BAC (g/100ml)");
        plot.getGridLabelRenderer().setHorizontalAxisTitle("Current Time (24hrs)");
        plot.addSeries(data);
        plot.addSeries(legalLine);
        // source how to set lims - https://stackoverflow.com/questions/33813039/graphview-change-x-and-y-axis-ranges
        plot.getViewport().setMinX(xInitial+0.1);
        plot.getViewport().setMaxX(xFinal+0.1);
        plot.getViewport().setMinY(0);
        plot.getViewport().setMaxY(currentBAC+0.1);
        plot.getViewport().setYAxisBoundsManual(true);
        plot.getViewport().setXAxisBoundsManual(true);

    }

    void setupTextViews() {
        currentBACTextView = (TextView) findViewById(R.id.currentReadingTextView);
        timeEstimateTextView = (TextView) findViewById(R.id.timeTextView);


        currentBACTextView.setText("Your current BAC is\n\t"+currentBAC+" g/100ml");

        safeToDriveAt.add(Calendar.HOUR_OF_DAY, (int)timeEstimate);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        safeTimeStr = format.format(safeToDriveAt.getTime());

        if (currentBAC > 0.2) {
            currentBACTextView.setTextColor(Color.RED);
            timeEstimateTextView.setText("You can drive at "+safeTimeStr+"h");
        } else if (currentBAC > 0.08) {
            currentBACTextView.setTextColor(Color.rgb(255, 215, 0));
            timeEstimateTextView.setText("You can drive at "+safeTimeStr+"h");
        } else {
            timeEstimateTextView.setText("You can drive!");
            timeEstimateTextView.setTextColor(Color.GREEN);
        }
    }
}