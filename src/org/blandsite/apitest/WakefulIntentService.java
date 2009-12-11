package org.blandsite.apitest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class WakefulIntentService extends IntentService {
	public static final String LOCK_NAME_STATIC = "apitest.Static";
	public static final String LOCK_NAME_LOCAL = "apitest.Local";
	private static PowerManager.WakeLock lockStatic = null;
	private PowerManager.WakeLock lockLocal = null;

	public static void acquireStaticLock(Context context) {
		PowerManager.WakeLock l=getLock(context);
		Log.i("apitest", "Aquiring intent lock "+l);
		l.acquire();
	}

	synchronized private static PowerManager.WakeLock getLock(Context context) {
		Log.i("apitest", "getLock intent called");
		if (lockStatic == null) {
			Log.i("apitest", "creating intent lock");
			PowerManager mgr = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
					LOCK_NAME_STATIC);
			lockStatic.setReferenceCounted(true);
		}
		return (lockStatic);
	}

	public WakefulIntentService(String name) {
		super(name);
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
		Log.i("apitest", "Releasing intent lock "+l);
		l.release();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		releaseLocalLock();
	}
	
	protected void releaseLocalLock() {
		lockLocal.release();
	}
}