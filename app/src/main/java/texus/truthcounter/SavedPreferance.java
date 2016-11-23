package texus.truthcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SavedPreferance {
	
	
	private static final String PROGRESS_FUU_WIDTH = "progress_full_width";
	
	
	public static void setWidth(Context context, int width) {
		Editor edit = getPreferance(context).edit();
		edit.putInt(PROGRESS_FUU_WIDTH, width);
		edit.commit();
	}
	
	public static int getWidth(Context context) {
		return getPreferance(context).getInt(PROGRESS_FUU_WIDTH, 0);	
	}
	
	
	private static SharedPreferences getPreferance(Context context) {
		SharedPreferences preferences = android.preference.PreferenceManager
				.getDefaultSharedPreferences(context);
		return preferences;
		
	}
}
