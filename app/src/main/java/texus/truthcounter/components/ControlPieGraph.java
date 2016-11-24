package texus.truthcounter.components;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import texus.truthcounter.R;
import texus.truthcounter.datamodels.PieChartData;

/**
 * <p>Score board</p>
 *
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 */
public class ControlPieGraph extends RelativeLayout                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               {

	Context mContext ;
	private PieChart mPieChart;


	public ControlPieGraph(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ControlPieGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ControlPieGraph(Context context) {
		super(context);
		init(context);
	}


	private void init(Context context) {

		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View child =  inflater.inflate(R.layout.control_pie_graph,this);

		mPieChart = (PieChart) child.findViewById(R.id.pieChart);
		setValues(0,0);
	}


	public void setValues(int good, int bad) {
		if( good == 0 && bad == 0) {
			setBlankGraph();
		}
		ArrayList<PieChartData> list = new ArrayList<PieChartData>();
		list.add( new PieChartData("", good, PieChartData.GREEN));
		list.add( new PieChartData("", bad, PieChartData.COLOR_RED));
		drawChartWithoutValue(mPieChart, "", list, true);

	}

	public void setBlankGraph() {
		ArrayList<PieChartData> list = new ArrayList<PieChartData>();
		list.add( new PieChartData("", 100, PieChartData.INVALID_GREY));
		list.add( new PieChartData("", 0, PieChartData.INVALID_GREY));
		drawChartWithoutValue(mPieChart, "", list, true);
	}

	private void drawChartWithoutValue(PieChart chart,String chartName,
									   ArrayList<PieChartData> objects,
									   boolean animate) {
		if( chart == null || objects == null) return;
		ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
		ArrayList<Integer> colors = new ArrayList<Integer>();
		for( PieChartData object : objects) {
			entries.add(new PieEntry(object.value, object.label));
			colors.add(object.color);
		}
		PieDataSet dataSet = new PieDataSet(entries, chartName);
		dataSet.setSliceSpace(1f);
		dataSet.setSelectionShift(0f);
		dataSet.setColors(colors);

		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(0f);
		data.setValueTextColor(Color.TRANSPARENT);


		//Set Chart description
		Description description = new Description();
		description.setText("");
		chart.setDescription(description);




		chart.setData(data);
		chart.invalidate();

		if(animate) {
			chart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
		}

		Legend legend = chart.getLegend();

		legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		legend.setOrientation(Legend.LegendOrientation.VERTICAL);
		legend.setDrawInside(false);
		legend.setTextSize(0f);

		legend.setXEntrySpace(0f);
		legend.setYEntrySpace(0f);
		legend.setYOffset(0f);
		legend.setFormSize(0f);
	}
	

}

