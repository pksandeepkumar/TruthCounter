package texus.truthcounter;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
	
	public static final int GRAPH_DAILY = 0;
	public static final int GRAPH_WEEKLY = 1;
	public static final int GRAPH_MONTHLY = 2;
	public static final int GRAPH_YEARLY = 3;
	public static final int GRAPH_TOTAL = 4;
	
	public static final String PARAM_GRAP_TYPE = "graph_type";
	public static final String PARAM_ADD_TYPE = "add_type";
	
	public static final int GOOD = 0;
	public static final int BAD = 1;
	
	public static final int REQUEST_CODE_FOR_ADD = 1;
	
	
	
	public void goBack( View view) {
		finish();
	}
	
	public void startPage(Class cls) {
		Intent  intent = new Intent(this, cls);
		startActivity(intent);
	}
	
	public static String getElemenateAllSOLInjections(String string) {
		string = string.replace('\'', ']');
		return string;
	}
	
	public static String getReverseSOLInjections(String string) {
		string = string.replace(']', '\'');
		return string;
	}


	public void setBackOnToolbar() {
		if(getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);

		}
		return;
	}



	public void setBackNavigation() {
		Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
		if(mActionBarToolbar == null) return;
		setSupportActionBar(mActionBarToolbar);
		if(getSupportActionBar() == null) return;
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}



	public void setIconOnToolbar() {
		ActionBar actionBar = getSupportActionBar();
		if( actionBar ==  null) return;
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
	}

}
