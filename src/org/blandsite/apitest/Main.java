package org.blandsite.apitest;

import java.util.Calendar;

import org.blandsite.apitest.Main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //createAlarm();
        //createIntentAlarm();
    }
    
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.ButtonIntentService:
			createIntentAlarm();
			break;
		case R.id.ButtonService:
			createAlarm();
			break;
		}
	}

	private void createIntentAlarm() {
		Log.i("apitest", "Setting up intent broadcast for 5 seconds");
		Intent intent = new Intent(Main.this, APITestIntentBroadcastReceiver.class);            
        PendingIntent sender = PendingIntent.getBroadcast(Main.this, 0, intent, 0);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, sender);

	}

	private void createAlarm() {
		Log.i("apitest", "Setting up broadcast for 5 seconds");
		Intent intent = new Intent(Main.this, APITestBroadcastReceiver.class);            
        PendingIntent sender = PendingIntent.getBroadcast(Main.this, 0, intent, 0);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, sender);

	}
	

}