package org.blandsite.apitest;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class WakefulService extends Service {
	
	public static final String LOCK_NAME_STATIC = "apitest.Static";
	public static final String LOCK_NAME_LOCAL = "apitest.Local";
	private static PowerManager.WakeLock lockStatic = null;
	private PowerManager.WakeLock lockLocal = null;

	public static void acquireStaticLock(Context context) {
		PowerManager.WakeLock l=getLock(context);
		Log.i("apitest", "Aquiring lock "+l);
		l.acquire();
	}

	synchronized private static PowerManager.WakeLock getLock(Context context) {
		Log.i("apitest", "getLock called");
		if (lockStatic == null) {
			Log.i("apitest", "creating lock");
			PowerManager mgr = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
					LOCK_NAME_STATIC);
			lockStatic.setReferenceCounted(true);
		}
		return (lockStatic);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		PowerManager mgr = (PowerManager) getSystemService(Context.POWER_SERVICE);
		lockLocal = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
				LOCK_NAME_LOCAL);
		lockLocal.setReferenceCounted(true);
	}

	@Override
	public void onStart(Intent intent, final int startId) {
		lockLocal.acquire();
		super.onStart(intent, startId);

		PowerManager.WakeLock l=getLock(this);
		Log.i("apitest", "Releasing lock "+l);
		l.release();
	}

	protected void releaseLocalLock() {
		lockLocal.release();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}