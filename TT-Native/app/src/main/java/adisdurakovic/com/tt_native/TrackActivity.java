package adisdurakovic.com.tt_native;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class TrackActivity extends ActionBarActivity {

    private Button pauseButton, stopButton, currentTime;
    private LinearLayout pbWrapper, stbWrapper, ctWrapper;
    private Handler customHandler = new Handler();
    int secs = 0;
    boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        currentTime = (Button) findViewById(R.id.currentTime);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);

        stbWrapper = (LinearLayout) findViewById(R.id.stbWrapper);
        pbWrapper = (LinearLayout) findViewById(R.id.pbWrapper);
        ctWrapper = (LinearLayout) findViewById(R.id.ctWrapper);

        customHandler.postDelayed(updateTimerThread, 1000);

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPaused) {
                    customHandler.removeCallbacks(updateTimerThread);
                    pauseButton.setText("RESUME");
                    isPaused = true;
                    Log.d("PB", "paused");
                } else {
                    customHandler.postDelayed(updateTimerThread, 1000);
                    pauseButton.setText("PAUSE");
                    isPaused = false;
                    Log.d("PB", "resumed");
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customHandler.removeCallbacks(updateTimerThread);
                currentTime.setText("00:00:00");
                Intent submitActivity = new Intent(getApplicationContext(), SubmitActivity.class);
                startActivity(submitActivity);
            }
        });

    }




    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            secs++;

            int minutes = secs / 60;
            int hours = secs/3600;
            int seconds = secs % 60;
            currentTime.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            customHandler.postDelayed(this, 1000);
        }

    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
