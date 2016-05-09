package io.github.dkocian.intentserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Random;

/**
 * Created by pln6477 on 5/9/16.
 */
public class RandomNumberIntentService extends IntentService {
    private static final int UPPER_BOUND = 100;
    public static final String GET_RANDOM_NUMBER = "GET_RANDOM_NUMBER";
    public static final String RANDOM_NUMBER = "RANDOM_NUMBER";
    private final Random mGenerator = new Random();

    public RandomNumberIntentService() {
        super(RandomNumberIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(GET_RANDOM_NUMBER)) {
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(GET_RANDOM_NUMBER);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(RANDOM_NUMBER, getRandomNumber());
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
        }
    }

    // Method for clients
    public int getRandomNumber() {
        return mGenerator.nextInt(UPPER_BOUND);
    }
}
