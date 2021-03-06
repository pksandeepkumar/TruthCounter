package texus.truthcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import texus.truthcounter.datamodels.TruthInfo;
import texus.truthcounter.db.DBHelper;

public class AddGoodOrBadActivity extends BaseActivity {
	
	int value;
	EditText etDescription;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_truth);

		setBackNavigation();
		
		Button btenGoodOrBad = ( Button ) this.findViewById(R.id.btnGoodOrBad);
		etDescription = (EditText) this.findViewById(R.id.etDescription);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value = extras.getInt(PARAM_ADD_TYPE);
		    if(value == GOOD)  {
		    	btenGoodOrBad.setBackgroundResource(R.drawable.ic_good);
		    }
		}

		if(value == GOOD)  {
			etDescription.setText(SavedPreferance.getDraftGood(this));
		} else {
			etDescription.setText(SavedPreferance.getDraftBad(this));
		}
		
		
	}



	public void doDraft( View view) {
		if(value == GOOD)  {
			SavedPreferance.setDraftGood(this, etDescription.getText().toString());
		} else {
			SavedPreferance.setDraftBad(this, etDescription.getText().toString());
		}
		Toast.makeText(this,"Draft saved", Toast.LENGTH_LONG).show();
		finish();
	}
	
	public void addGoodOrBad( View view) {
		if(value == GOOD)  {
			addGoodOrBad(true);
			SavedPreferance.setDraftGood(this, "");
	    } else {
	    	addGoodOrBad(false);
			SavedPreferance.setDraftBad(this, "");
	    }
		
	}
	
	public void addGoodOrBad( boolean goodOrBad) {
		TruthInfo truthInfo = new TruthInfo();
		truthInfo.date = getCurrentTime();
		truthInfo.description = getElemenateAllSOLInjections(
				etDescription.getText().toString().trim());
		truthInfo.value = goodOrBad;
		
		DBHelper dbHelper = new DBHelper(this);
		dbHelper.createDB(this);
		DBHelper.saveTruthInfo(this, truthInfo);
		
		Intent i = new Intent();
		setResult(RESULT_OK, i);
		finish();

		
	}
	
	public String getCurrentTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
	
	

}
