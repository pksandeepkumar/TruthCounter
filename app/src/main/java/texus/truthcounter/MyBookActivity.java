package texus.truthcounter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import texus.truthcounter.adapter.MyBookRecycleAdapter;
import texus.truthcounter.datamodels.TruthInfo;
import texus.truthcounter.db.DBHelper;

public class MyBookActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_book);

		setBackNavigation();

		RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
		SetListTask task = new SetListTask(recyclerView, this);
		task.execute();
		
		
	}
	
	public class SetListTask extends AsyncTask<Void, Void, Void> {
		
		ArrayList<TruthInfo> truthInfos = new ArrayList<TruthInfo>();
		Context context;
		RecyclerView recyclerView;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		public SetListTask(RecyclerView recyclerView, Context context) {
			this.recyclerView = recyclerView;
			this.context = context;
		}
		   
		@Override
		protected Void doInBackground(Void... params) {
			truthInfos = DBHelper.getAllGoodOrBadWithoutDescription(context);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if(truthInfos.size() > 0 ) {
				MyBookRecycleAdapter adapter = new MyBookRecycleAdapter(context, truthInfos);
				if(recyclerView != null) {
					RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
					recyclerView.setLayoutManager(mLayoutManager);
					recyclerView.setItemAnimator(new DefaultItemAnimator());
					recyclerView.setAdapter(adapter);
				}
				
			}
			
		}
		
	}
	

}
