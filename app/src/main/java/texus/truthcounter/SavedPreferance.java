package texus.truthcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SavedPreferance {
	
	
	private static final String PROGRESS_FUU_WIDTH = "progress_full_width";
	private static final String DRAFT_GOOD = "draft_good";
	private static final String DRAFT_BAD = "draft_bad";

	public static void setDraftGood(Context context, String draftGood) {
		Editor edit = getPreferance(context).edit();
		edit.putString(DRAFT_GOOD, draftGood);
		edit.commit();
	}

	public static String getDraftGood(Context context) {
		return getPreferance(context).getString(DRAFT_GOOD, "");
	}

	public static void setDraftBad(Context context, String draftBad) {
		Editor edit = getPreferance(context).edit();
		edit.putString(DRAFT_BAD, draftBad);
		edit.commit();
	}

	public static String getDraftBad(Context context) {
		return getPreferance(context).getString(DRAFT_BAD, "");
	}
	
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
