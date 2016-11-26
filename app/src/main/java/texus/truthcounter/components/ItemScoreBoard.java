package texus.truthcounter.components;


import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import texus.truthcounter.BaseActivity;
import texus.truthcounter.PieGraphActivity;
import texus.truthcounter.R;

/**
 * <p>Score board</p>
 *
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 */
public class ItemScoreBoard extends RelativeLayout                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               {

	Context context ;
	
	TextView tvBadDaily;
	TextView tvGoodDaily;
	
	TextView tvBadWeekly;
	TextView tvGoodWeekly;
	
	TextView tvBadMonthly;
	TextView tvGoodMonthly;
	
	TextView tvBadYearly;
	TextView tvGoodYearly;
	
	TextView tvBadTotal;
	TextView tvGoodTotal;
	
//	ImageView imDaily;
//	ImageView imWeekly;
//	ImageView imMonthly;
//	ImageView imYearly;
//	ImageView imTotal;



	ControlPieGraph pieChartDaily;
	ControlPieGraph pieWeekly;
	ControlPieGraph pieChartMonthly;
	ControlPieGraph pieChartYearly;
	ControlPieGraph pieChartTotal;

	public ItemScoreBoard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ItemScoreBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ItemScoreBoard(Context context) {
		super(context);
		init(context);
	}

	
	private void init(Context context) {
		
		this.context = context;
		LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View child =  inflater.inflate(R.layout.element_score_board,this);
		
		tvBadDaily = (TextView) child.findViewById(R.id.badDaily);
		tvGoodDaily = (TextView) child.findViewById(R.id.goodDaily);
		
		tvBadWeekly = (TextView) child.findViewById(R.id.badWeekly);
		tvGoodWeekly = (TextView) child.findViewById(R.id.goodWeekly);
		
		tvBadMonthly = (TextView) child.findViewById(R.id.badMonthly);
		tvGoodMonthly = (TextView) child.findViewById(R.id.goodMonthly);
		
		tvBadYearly = (TextView) child.findViewById(R.id.badYearly);
		tvGoodYearly = (TextView) child.findViewById(R.id.goodYearly);
		
		tvBadTotal = (TextView) child.findViewById(R.id.badTotal);
		tvGoodTotal = (TextView) child.findViewById(R.id.goodTotal);


		pieChartDaily = (ControlPieGraph) child.findViewById(R.id.pieChartDaily);
		pieWeekly = (ControlPieGraph) child.findViewById(R.id.pieWeekly);
		pieChartMonthly = (ControlPieGraph) child.findViewById(R.id.pieChartMonthly);
		pieChartYearly = (ControlPieGraph) child.findViewById(R.id.pieChartYearly);
		pieChartTotal = (ControlPieGraph) child.findViewById(R.id.pieChartTotal);
		
//		imDaily = ( ImageView ) child.findViewById(R.id.imDaily);
//		imDaily.setTag(BaseActivity.GRAPH_DAILY);
//		imWeekly = ( ImageView ) child.findViewById(R.id.imWeekly);
//		imWeekly.setTag(BaseActivity.GRAPH_WEEKLY);
//		imMonthly = ( ImageView ) child.findViewById(R.id.imMonthly);
//		imMonthly.setTag(BaseActivity.GRAPH_MONTHLY);
//		imYearly = ( ImageView ) child.findViewById(R.id.imYearly);
//		imYearly.setTag(BaseActivity.GRAPH_YEARLY);
//		imTotal = ( ImageView ) child.findViewById(R.id.imTotal);
//		imTotal.setTag(BaseActivity.GRAPH_TOTAL);
		
		OnClickListener listener =  new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int type = (Integer) arg0.getTag();
				Intent i = new Intent(arg0.getContext(), PieGraphActivity.class);
				i.putExtra(BaseActivity.PARAM_GRAP_TYPE,type);
				arg0.getContext().startActivity(i);
			}
		};
//		imDaily.setOnClickListener(listener);
//		imWeekly.setOnClickListener(listener);
//		imMonthly.setOnClickListener(listener);
//		imYearly.setOnClickListener(listener);
//		imTotal.setOnClickListener(listener);
		
	}

	public void
	
	
	public void setGoodAndBadDaily( int good, int bad) {
		tvBadDaily.setText("" + bad);
		tvGoodDaily.setText("" + good);
		pieChartDaily.setValues(good, bad);
		pieChartDaily.vClick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
	
	public void setGoodAndBadWeekly( int good, int bad) {
		tvBadWeekly.setText("" + bad);
		tvGoodWeekly.setText("" + good);
		pieWeekly.setValues(good, bad);
	}
	
	public void setGoodAndBadMonthly( int good, int bad) {
		tvBadMonthly.setText("" + bad);
		tvGoodMonthly.setText("" + good);
		pieChartMonthly.setValues(good, bad);
	}
	
	public void setGoodAndBadYearly( int good, int bad) {
		tvBadYearly.setText("" + bad);
		tvGoodYearly.setText("" + good);
		pieChartYearly.setValues(good, bad);
	}
	
	public void setGoodAndBadTotal( int good, int bad) {
		tvBadTotal.setText("" + bad);
		tvGoodTotal.setText("" + good);
		pieChartTotal.setValues(good, bad);
	}
	
	
	

}

