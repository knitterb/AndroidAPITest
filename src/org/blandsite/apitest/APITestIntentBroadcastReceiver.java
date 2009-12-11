package org.blandsite.apitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class APITestIntentBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("apitest", "Received intent broadcast");
		WakefulIntentService.acquireStaticLock(context);
		context.startService(new Intent(context, APITestIntentService.class));
	}

}
