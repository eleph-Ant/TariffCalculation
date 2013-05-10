package tian.google;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * 
 * @author Administrator
 * @creation 2013-2-23
 */
public class MainActivity extends Activity implements OnClickListener
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboard_layout);
		((Button) findViewById(R.id.calc)).setOnClickListener(this);
		((Button) findViewById(R.id.history)).setOnClickListener(this);
		((Button) findViewById(R.id.set)).setOnClickListener(this);
		((Button) findViewById(R.id.tips)).setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.calc:
			intent.setClass(MainActivity.this, Calc.class);
			break;
		case R.id.history:
			intent.setClass(MainActivity.this, History.class);
			break;
		case R.id.set:
			intent.setClass(MainActivity.this, Set.class);
			break;
		case R.id.tips:
			intent.setClass(MainActivity.this, Tips.class);
			break;
		}
		startActivity(intent);
	}
}
