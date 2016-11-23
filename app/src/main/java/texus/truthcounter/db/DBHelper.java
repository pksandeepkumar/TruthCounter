package texus.truthcounter.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import texus.truthcounter.datamodels.TruthInfo;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "TruthCounter.db";
	public static final int DATABASE_VERSION = 1;
	public static final String ROW_ID="_id";
	
	public static final String TABLE_TRUTH_COUNTER="table_truth_counter";
	
	public static final String VALUE="value";
	public static final String DESCRIPTION="description";
	public static final String DATE="date";
    
	private static final String[] CREATE_TABLES = {"CREATE TABLE  "+ TABLE_TRUTH_COUNTER + " ( "+
														ROW_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"+
														VALUE + "  VARCHAR( 1 ) DEFAULT '0',"+
	                                                    DESCRIPTION + " TEXT not null,"+
	                                                    DATE + " DATETIME( 20 ) " + ");"};	
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		try{
			Log.d("DB","DBoncreate:");
			for(int i=0; i<CREATE_TABLES.length;i++ )
				
				database.execSQL(CREATE_TABLES[i]);
				Log.d("DB","DBoncreate:" + CREATE_TABLES[0]);
		}catch(SQLException e){
			Log.d("DB","DBoncreate:"+e.getMessage());
			return;
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.d(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		try{
			database.execSQL("DROP TABLE " + TABLE_TRUTH_COUNTER +  "; " );
		}catch(SQLException e){
			Log.d("DB","drop table error:"+e.getMessage());
		}
		onCreate(database);
	}
	
	public void createDB(Context context) {
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		dbRead.close();
		db.close();
		
	}
	
	public static void saveTruthInfo( Context context, TruthInfo truthInfo ) {
		
		DBHelper db = new DBHelper(context);
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + DBHelper.TABLE_TRUTH_COUNTER + " (" 
				+ VALUE + "," 
				+ DESCRIPTION + ","
				+ DATE + ") values ( '" 
				+ truthInfo.value + "','"
				+ truthInfo.description + "','"
				+ truthInfo.date + "');";
		sql.execSQL(query);
		sql.close();
		db.close();
	}
	
	
	public static double getParseDouble( String doubleString) {
		
		try {
			return Double.parseDouble(doubleString);
		} catch ( NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public static int getAllGoodCount(Context context) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getAllBadCount(Context context) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'false'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
					+ " WHERE " +  DBHelper.VALUE + " = 'false'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	
	public static int getYearlyGoodCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartYearly(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndYearly(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getYearlyBadCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'false' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartYearly(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndYearly(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getMonthlyGoodCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartMonthly(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndMonthly(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getMonthlyBadCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'false' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartMonthly(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndMonthly(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getDailyGoodCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartDay(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndDay(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getDailyBadCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'false' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartDay(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndDay(date))+ "'";
		Log.d("DB MANAGER" ,"QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getWeeklyGoodCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartWeek(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndWeek(date))+ "'";
		Log.d("DB MANAGER" ,"WEEKLY QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static int getWeeklyBadCount(Context context, Date date) {
		
		int count = 0;
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT COUNT(" + DBHelper.ROW_ID +") FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'true'";
		query = "SELECT " + DBHelper.ROW_ID +" FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  DBHelper.VALUE + " = 'false' AND " + DATE + " >= '" 
				+ getCurrentTimeInFormat(findStartWeek(date)) + "' AND " 
				+  DATE + " <= '"  
				+ getCurrentTimeInFormat(findEndWeek(date))+ "'";
		Log.d("DB MANAGER" ,"WEEKLY QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		count = c.getCount();
		c.close();
		dbRead.close();
		db.close();
		return count;
	}
	
	public static ArrayList<TruthInfo> getAllGoodOrBadWithoutDescription(Context context) {
		
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT " + DBHelper.ROW_ID +"," 
		+ DATE + "," + VALUE + " FROM " +  TABLE_TRUTH_COUNTER 
				+ " ORDER BY " +  DATE + " DESC";
		Log.d("DB MANAGER" ,"WEEKLY QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		ArrayList<TruthInfo> truthInfos = new ArrayList<TruthInfo>();
		while (c.moveToNext()) {
			TruthInfo truthInfo = new TruthInfo();
			truthInfo.date = c.getString(c.getColumnIndex(DATE));
			truthInfo.id = c.getString(c.getColumnIndex(ROW_ID));
			truthInfo.value = Boolean.parseBoolean(c.getString((c.getColumnIndex(VALUE))));
			truthInfos.add(truthInfo);
		}
		c.close();
		dbRead.close();
		db.close();
		return truthInfos;
	}
	
	public static TruthInfo getAGoodOrBadWithDescription(Context context, String id) {
		
		DBHelper db = new DBHelper(context);
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "SELECT " + DBHelper.DESCRIPTION +"," 
		+ DATE + "," + VALUE + " FROM " +  TABLE_TRUTH_COUNTER 
				+ " WHERE " +  ROW_ID + " = " + id;
		Log.d("DB MANAGER" ,"WEEKLY QUERY:" + query);
		Cursor c = dbRead.rawQuery( query, null);
		TruthInfo truthInfo = new TruthInfo();
		if (c.moveToNext()) {
			truthInfo.date = c.getString(c.getColumnIndex(DATE));
			truthInfo.description = c.getString(c.getColumnIndex(DESCRIPTION));
			truthInfo.value = Boolean.parseBoolean(c.getString((c.getColumnIndex(VALUE))));
		} else {
			truthInfo = null;
		}
		c.close();
		dbRead.close();
		db.close();
		return truthInfo;
	}
	
	
	
	public static Date findEndYearly( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_WEEK_IN_MONTH
				, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
	public static Date findStartYearly( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
	public static Date findEndMonthly( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK_IN_MONTH
				, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
	public static Date findStartMonthly( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
	public static Date findEndDay( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
	public static Date findStartDay( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 1);
		Date dayEnd = c.getTime();
		return dayEnd;
	}
	
//	public static Date findEndWeek( Date date) {
//		return date;
////		Calendar c = Calendar.getInstance();
////		c.setTime(date);
////		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
////		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
////		c.add(Calendar.DAY_OF_MONTH, 6); 
////		Date weekEnd = c.getTime();
////		return weekEnd;
//		
//	}
//	
//	public static Date findStartWeek( Date date) {
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
//		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
//		Date weekStart = c.getTime();
//		return weekStart;
//		
//	}
	
	public static Date findEndWeek( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		c.add(Calendar.DAY_OF_MONTH, 6); 
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		
		Date weekEnd = c.getTime();
		return weekEnd;
		
	}
	
	public static Date findStartWeek( Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		Date weekStart = c.getTime();
		return weekStart;
		
	}
	
	public static String getCurrentTimeInFormat(Date date) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
		Date now = date;
		String strDate = sdfDate.format(now);
		return strDate;
	}
	
	
	
	
}