package tian.google;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 *
 * @author Administrator
 * @creation 2013-2-25
 */
public class Calc extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.calc);
		((TextView)findViewById(R.id.title)).setText(R.string.fun_calc);
	}
}