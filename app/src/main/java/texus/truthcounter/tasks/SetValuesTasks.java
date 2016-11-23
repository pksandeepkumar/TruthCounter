package texus.truthcounter.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;

import texus.truthcounter.components.ItemScoreBoard;
import texus.truthcounter.components.ItemYourStatus;
import texus.truthcounter.db.DBHelper;


public class SetValuesTasks extends AsyncTask<Void, Void, Void> {
	
	ItemScoreBoard itemScoreBoard;
	ItemYourStatus itemYourStatus;
	int allGoodCount = 0;
	int allBadCount = 0;
	
	int dailyGoodCount = 0;
	int dailyBadCount = 0;
	
	int weeklyGoodCount = 0;
	int weeklyBadCount = 0;
	
	int monthlyGoodCount = 0;
	int monthlyBadCount = 0;
	
	int yearlyGoodCount = 0;
	int yearlyBadCount = 0;
	Context context;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	public SetValuesTasks(Context context,ItemScoreBoard itemScoreBoard, ItemYourStatus itemYourStatus) {
		this.itemScoreBoard = itemScoreBoard;
		this.itemYourStatus = itemYourStatus;
		this.context = context;
		
	}
	   
	@Override
	protected Void doInBackground(Void... params) {
		Date date = new Date();
		allGoodCount = DBHelper.getAllGoodCount(context);
		allBadCount = DBHelper.getAllBadCount(context);
		
		dailyBadCount = DBHelper.getDailyBadCount(context, date);
		dailyGoodCount = DBHelper.getDailyGoodCount(context, date);
		
		weeklyBadCount = DBHelper.getWeeklyBadCount(context, date);
		weeklyGoodCount = DBHelper.getWeeklyGoodCount(context, date);
		
		monthlyBadCount = DBHelper.getMonthlyBadCount(context, date);
		monthlyGoodCount = DBHelper.getMonthlyGoodCount(context, date);
		
		yearlyBadCount = DBHelper.getYearlyBadCount(context, date);
		yearlyGoodCount = DBHelper.getYearlyGoodCount(context, date);
		
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		itemScoreBoard.setGoodAndBadTotal(allGoodCount, allBadCount);
		itemScoreBoard.setGoodAndBadDaily(dailyGoodCount, dailyBadCount);
		itemScoreBoard.setGoodAndBadWeekly(weeklyGoodCount, weeklyBadCount);
		itemScoreBoard.setGoodAndBadMonthly(monthlyGoodCount, monthlyBadCount);
		itemScoreBoard.setGoodAndBadYearly(yearlyGoodCount, yearlyBadCount);
		
		int percentage = getPercentage(allGoodCount, (allGoodCount + allBadCount));
		itemYourStatus.setValues(percentage);
//		Toast.makeText(context, "Percentage:" + percentage, Toast.LENGTH_SHORT).show();
		
		
	}
	
	private int getPercentage(int value, int totalValue) {
		
		if( value == 0 || totalValue == 0) {
			return 0;
		}
		int percentage = 0;
		percentage = (value * 100) / totalValue;
		return percentage;
		
	}
}
