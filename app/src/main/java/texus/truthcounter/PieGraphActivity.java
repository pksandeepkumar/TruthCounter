package texus.truthcounter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import texus.truthcounter.db.DBHelper;

public class PieGraphActivity extends BaseActivity {
	
	int goodCount;
	int badCount;
	
	int type;

	private static int[] COLORS = new int[] {
	    Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.RED, Color.DKGRAY, Color.BLACK};

	private Button btnChangeDate;
	private TextView tvTitleForGraph;
	
 
	private int year;
	private int month;
	private int day;
	
	static final int DATE_DIALOG_ID = 999;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_activity);

		setBackOnToolbar();
		btnChangeDate = (Button) this.findViewById(R.id.btnDatePicker);
		tvTitleForGraph  = (TextView) this.findViewById(R.id.tvTitleForGraph);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    type = extras.getInt(BaseActivity.PARAM_GRAP_TYPE);
		}
		Date  date= new Date();
		populateGraph(date);
		
		setCurrentDateOnView();
		addListenerOnButton();
		
	}
	
	
	// display current date
		public void setCurrentDateOnView() {
	 
//			dpResult = (DatePicker) findViewById(R.id.dpResult);
	 
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into textview
			btnChangeDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));
	 
			// set current date into datepicker
//			dpResult.init(year, month, day, null);
	 
		}
	 
		public void addListenerOnButton() {
	 
//			btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
	 
			btnChangeDate.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View v) {
	 
					showDialog(DATE_DIALOG_ID);
	 
				}
	 
			});
	 
		}
	 
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_ID:
			   // set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			}
			return null;
		}
	 
		private DatePickerDialog.OnDateSetListener datePickerListener 
	                = new DatePickerDialog.OnDateSetListener() {
	 
			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;
	 
				// set selected date into textview
				btnChangeDate.setText(new StringBuilder().append(month + 1)
				   .append("-").append(day).append("-").append(year)
				   .append(" "));
				
				Calendar cal  = Calendar.getInstance();
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH, day);
				cal.set(Calendar.YEAR, year);
				
				populateGraph(cal.getTime());
	 
				// set selected date into datepicker also
//				dpResult.init(year, month, day, null);
	 
			}
		};
	
	
	
	
	private void populateGraph(Date date) {
		if(type == GRAPH_DAILY) {
			 badCount = DBHelper.getDailyBadCount(this, date);
			 goodCount = DBHelper.getDailyGoodCount(this, date);
			 tvTitleForGraph.setText("Daily");
		} else
			if(type == GRAPH_WEEKLY) {
				goodCount = DBHelper.getWeeklyGoodCount(this, date);
				badCount = DBHelper.getWeeklyBadCount(this, date);
				tvTitleForGraph.setText("Weekly");
			}
			else
				if(type == GRAPH_MONTHLY) {
					goodCount = DBHelper.getMonthlyGoodCount(this, date);
					badCount = DBHelper.getMonthlyBadCount(this, date);
					tvTitleForGraph.setText("Monthly");
				}
				else
					if(type == GRAPH_YEARLY) {
						goodCount = DBHelper.getYearlyGoodCount(this, date);
						badCount = DBHelper.getYearlyBadCount(this, date);
						tvTitleForGraph.setText("Yearly");
					}
					else
						if(type == GRAPH_TOTAL) {
							goodCount = DBHelper.getAllGoodCount(this);
							badCount = DBHelper.getAllBadCount(this);
							tvTitleForGraph.setText("All Time");
						}
		
		LinearLayout layoutGraph = ( LinearLayout ) this.findViewById(R.id.layoutForGraph); 
		
		if(goodCount ==0 && badCount == 0) {
			layoutGraph.removeAllViews();
			return;
					
		}

	}

}
