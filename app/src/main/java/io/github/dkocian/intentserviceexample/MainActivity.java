package io.github.dkocian.intentserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int randomNumber = intent.getIntExtra(RandomNumberIntentService.RANDOM_NUMBER, 0);
            Toast.makeText(MainActivity.this, String.valueOf(randomNumber), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(RandomNumberIntentService.GET_RANDOM_NUMBER);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public void getRandomNumber(View view) {
        Intent randomNumberIntentService = new Intent(this, RandomNumberIntentService.class);
        randomNumberIntentService.setAction(RandomNumberIntentService.GET_RANDOM_NUMBER);
        startService(randomNumberIntentService);
    }

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}
