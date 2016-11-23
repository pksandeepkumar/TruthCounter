package com.texus.truthcounter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DescriptionActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		
		WebView webView = (WebView) this.findViewById(R.id.webView1);
		webView.loadUrl("file:///android_asset/about_truth_counter.html");
	}

}
