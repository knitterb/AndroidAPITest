package org.blandsite.apitest;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class APITestService extends WakefulService {
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("apitest", "onStart");
		super.onStart(intent, startId);
	}
}