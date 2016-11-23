package texus.truthcounter.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import texus.truthcounter.R;
import texus.truthcounter.datamodels.TruthInfo;
import texus.truthcounter.db.DBHelper;
import texus.truthcounter.utility.DateManager;


public class TruthListAdapeter extends BaseAdapter {
	
	Context context;
	ArrayList<TruthInfo> truthInfos;
	
	public TruthListAdapeter(Context context,ArrayList<TruthInfo> truthInfo) {
		this.context = context;
		this.truthInfos = truthInfo;
	}

	@Override
	public int getCount() {
		return truthInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View child =  inflater.inflate(R.layout.element_list_item,null);
		TruthInfo truthInfo = null;
		try {
			truthInfo = truthInfos.get(position);
		} catch  (Exception e) {
			e.printStackTrace();
		}
		
		
		TextView  tvDate = ( TextView ) child.findViewById(R.id.textViewDate);
		TextView  tvDescription = ( TextView ) child.findViewById(R.id.textViewDescription);
		ImageView  imImage = ( ImageView ) child.findViewById(R.id.imageViewGoodOrBad);
		
		if(truthInfo != null) {
			
			String dateString = new DateManager(truthInfo.date).getDayNameDD_MM_YYY__HHMMAM();
			tvDate.setText(dateString);
			if(truthInfo.value) {
				imImage.setImageResource(R.drawable.ic_good);
			} else {
				imImage.setImageResource(R.drawable.ic_bad);
			}
			truthInfo = DBHelper.getAGoodOrBadWithDescription(context, truthInfo.id);
			if(truthInfo != null && tvDescription != null) {
				tvDescription.setText(truthInfo.description);
			}
//			SetDescriptionTask task = new SetDescriptionTask(tvDescription, truthInfo.id);
//			task.execute();
		}
		
		return child;
	}
	
public class SetDescriptionTask extends AsyncTask<Void, Void, Void> {
		
		TextView textView;
		TruthInfo truthInfo;
		String id;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		public SetDescriptionTask(TextView textView, String id) {
			this.textView = textView;
			this.id = id;
		}
		   
		@Override
		protected Void doInBackground(Void... params) {
			truthInfo = DBHelper.getAGoodOrBadWithDescription(context, id);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if(this.truthInfo != null && textView != null) {
				textView.setText(this.truthInfo.description);
			}
			
		}
		
	}

}

