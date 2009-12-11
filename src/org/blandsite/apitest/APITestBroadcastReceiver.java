package org.blandsite.apitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class APITestBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("apitest", "Received broadcast");
		WakefulService.acquireStaticLock(context);
		context.startService(new Intent(context, APITestService.class));
	}

}
