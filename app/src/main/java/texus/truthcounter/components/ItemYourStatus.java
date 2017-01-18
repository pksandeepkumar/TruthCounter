package texus.truthcounter.components;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import texus.truthcounter.R;
import texus.truthcounter.SavedPreferance;

/**
 * <p>Score board</p>
 *
 * @author Sandeep Kumar <pksandeepkumar@gmail.com>
 */
public class ItemYourStatus extends RelativeLayout                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               {

	Context mContext ;
	RelativeLayout rlLayout;
	LinearLayout progressLayout;
	

	public ItemYourStatus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ItemYourStatus(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ItemYourStatus(Context context) {
		super(context);
		init(context);
	}

	
	private void init(Context context) {
		
		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View child =  inflater.inflate(R.layout.element_your_status,this);
		
		rlLayout = (RelativeLayout) child.findViewById(R.id.progressRLlayout);
		progressLayout = (LinearLayout) child.findViewById(R.id.progressLayout);
		
//		if(SavedPreferance.getWidth(context) != 0) {
			ViewTreeObserver vto = rlLayout.getViewTreeObserver();
			vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					int width = rlLayout.getWidth();
					SavedPreferance.setWidth(mContext, width);
					
				}
			});
//		}
		
	}
	
	
	public void setValues(int percentage) {
		if(percentage != 0 ) {
			progressLayout.setBackgroundColor(getResources().getColor(R.color.bg_green_for_score_board));
			rlLayout.setBackgroundColor(getResources().getColor(R.color.bg_red_for_score_board));
		}
		int width = SavedPreferance.getWidth(mContext);
		if(width != 0) {
			RelativeLayout.LayoutParams lp = (LayoutParams) progressLayout.getLayoutParams();
//			Toast.makeText(mContext, "Width:"
//					+ width2, Toast.LENGTH_SHORT).show();
			lp.width = ( width * percentage ) / 100;
			progressLayout.setLayoutParams(lp);
		}
	}
	

}

