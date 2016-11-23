package com.texus.truthcounter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.texus.truthcounter.adapter.TruthListAdapeter;
import com.texus.truthcounter.components.ItemScoreBoard;
import com.texus.truthcounter.components.ItemYourStatus;
import com.texus.truthcounter.datamodels.TruthInfo;
import com.texus.truthcounter.db.DBHelper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyBookActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_book_activity);
		
		ListView  listView = ( ListView ) this.findViewById(R.id.listMyBook);
		SetListTask task = new SetListTask(listView, this);
		task.execute();
		
		
	}
	
	public class SetListTask extends AsyncTask<Void, Void, Void> {
		
		ArrayList<TruthInfo> truthInfos = new ArrayList<TruthInfo>();
		Context context;
 		ListView listView;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		public SetListTask(ListView listView, Context context) {
			this.listView = listView;
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
				TruthListAdapeter adapter = new TruthListAdapeter(context, truthInfos);
				if(listView != null) {
					listView.setAdapter(adapter);
				}
				
			}
			
		}
		
	}
	

}
