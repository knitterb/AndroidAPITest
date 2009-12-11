package org.blandsite.apitest;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class APITestIntentService extends WakefulIntentService {
	public APITestIntentService() {
		super("APITestIntentService");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("apitest", "onHandlerIntent");
		super.onHandleIntent(intent);
	}
}