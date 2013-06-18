package net.zhuoweizhang.glass.stfu;

import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.*;

import com.google.glass.sound.*;

//this code is a stripped-down version of SoundManager.java from a decompiled Glass Home apk
public class MainActivity extends Activity
{

	private static final ComponentName SOUND_MANAGER_SERVICE = new ComponentName("com.google.glass.sound", "com.google.glass.sound.SoundManagerService");

	private ServiceConnection connection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder service) {
			soundManagerService = ISoundManagerService.Stub.asInterface(service);
			doTheMuting();
		}

		public void onServiceDisconnected (ComponentName name) {
		}
	};

	private ISoundManagerService soundManagerService;
		
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent var1 = new Intent();
		var1.setComponent(SOUND_MANAGER_SERVICE);
		this.bindService(var1, this.connection, Context.BIND_AUTO_CREATE);

	}

	private void doTheMuting() {
		boolean newStatus = false;
		try {
			newStatus = !soundManagerService.isMuted();
			soundManagerService.setMuted(newStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		unbindService(connection);
		Toast.makeText(this, newStatus? R.string.has_muted: R.string.has_unmuted, Toast.LENGTH_SHORT).show();
		finish();
	}
}
