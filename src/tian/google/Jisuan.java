package tian.google;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Jisuan extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	public final String NAME = "PRICE";
	Bitmap bitmap;
	ImageView iv;
	Resources resource;
	EditText etnumber;
	Button  btn2;
	SharedPreferences preference;
	int second, third;
	float firstprice, secondprice, thirdprice;
	int inputnum = 0;
	float inputfee = 0;
	DrawMyMap drawmapInstance;
	long lastTouchTime = 0;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		init();
		drawmapInstance();
		iv.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event)
			{
				long curTime = System.currentTimeMillis();
				// 此处作用是规定两次touch相应时间>250ms
				if (curTime - lastTouchTime > 250) {
					float x =  event.getX();
					Log.v("yes", "" + x);
					int w = Jisuan.this.getWindowManager().getDefaultDisplay().getWidth();
					if (x >= 20 && x <= w - 40) {
						x =(int) ((x-10)/(w-60)*(third+30));
						float fee = fees((int)x);
						Bitmap b = drawmapInstance.drawLine(bitmap, x, fee);
						iv.setImageBitmap(b);
						lastTouchTime = curTime;
					}
				}
				return true;
			}
		});
		
	}
	
	public void init()
	{
		resource = this.getResources();
		setContentView(R.layout.layout1);
		iv = (ImageView)findViewById(R.id.ly1_iv);
		etnumber = (EditText) findViewById(R.id.ly1_et_num);
		preference = getSharedPreferences(NAME, MODE_PRIVATE);
		firstprice = preference.getFloat("firstprice", (float) 0.55);
		second = preference.getInt("second", 120);
		secondprice = preference.getFloat("secondprice", (float) 0.55);
		third = preference.getInt("third", 150);
		thirdprice = preference.getFloat("thirdprice", (float) 0.55);
//		setText(tv, firstprice, second, secondprice, third, thirdprice);
	}
	
//	public void setText(TextView tv, float firstprice, int second, float secondprice, int third, float thirdprice)
//	{
//		tv.setText(resource.getString(R.string.jiben) + second + resource.getString(R.string.dianjia) + firstprice + resource.getString(R.string.danwei) + "\n" + resource.getString(R.string.erji)
//				+ second + "-" + third + resource.getString(R.string.dianjia) + secondprice + resource.getString(R.string.danwei) + "\n" + resource.getString(R.string.sanji) + third
//				+ resource.getString(R.string.dianjia) + thirdprice + resource.getString(R.string.danwei) + "\n");
//	}
	
	public float fees(int num)
	{
		float fee = 0;
		if (num <= second) {
			fee = firstprice * num;
		}
		else if (num <= third) {
			fee = firstprice * second + (num - second) * secondprice;
		}
		else {
			fee = firstprice * second + (third - second) * secondprice + (num - third) * thirdprice;
		}
		return DialogUtil.floatFormat(fee);
	}
	
	public float num(float fee)
	{
		float num = 0;
		if (second * firstprice > fee) {
			num = (float) fee / firstprice;
		}
		else if (second * firstprice + (third - second) * secondprice > fee) {
			num = (float) second + (fee - firstprice * second) / secondprice;
		}
		else {
			num = third + (fee - firstprice * second - secondprice * (third - second)) / thirdprice;
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
		num = Float.parseFloat(df.format(num));
		return DialogUtil.floatFormat(num);
	}
	
	public void onClick(View v)
	{
		switch (v.getId())
		{
//		case R.id.ly1_text:
//			AlertDialog.Builder builder = new AlertDialog.Builder(Jisuan.this);
//			builder.setTitle("电价设置");
//			TableLayout price = (TableLayout) getLayoutInflater().inflate(R.layout.price, null);
//			builder.setView(price);
//			final EditText et1 = (EditText) price.findViewById(R.id.e1);
//			final EditText et2 = (EditText) price.findViewById(R.id.e2);
//			final EditText et3 = (EditText) price.findViewById(R.id.e3);
//			final EditText et4 = (EditText) price.findViewById(R.id.e4);
//			final EditText et5 = (EditText) price.findViewById(R.id.e5);
//			et1.setText("" + second);
//			et2.setText("" + third);
//			et3.setText("" + firstprice);
//			et4.setText("" + secondprice);
//			et5.setText("" + thirdprice);
//			builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
//				public void onClick(DialogInterface dialog, int which)
//				{
//					if (et1.getText().toString().replace(" ", "").equals("") || et2.getText().toString().replace(" ", "").equals("") || et3.getText().toString().replace(" ", "").equals("")
//							|| et4.getText().toString().replace(" ", "").equals("") || et5.getText().toString().replace(" ", "").equals("")) {
//						DialogUtil.showDialog(Jisuan.this, "您有未输入的选项", "", false);
//					}
//					else {
//						int s = Integer.parseInt(et1.getText().toString());
//						int t = Integer.parseInt(et2.getText().toString());
//						float fp = Float.parseFloat(et3.getText().toString());
//						float sp = Float.parseFloat(et4.getText().toString());
//						float tp = Float.parseFloat(et5.getText().toString());
//						if (s == 0 || t == 0 || fp == 0.0 || sp == 0.0 || tp == 0.0) {
//							
//						}
//						else if (t < s) {
//							DialogUtil.showDialog(Jisuan.this, "您的输入有错", "第二阶梯最大可用电量不能小于第一阶梯最大可用电量", false);
//						}
//						else {
//							second = s;
//							third = t;
//							firstprice = fp;
//							secondprice = sp;
//							thirdprice = tp;
//							SharedPreferences.Editor editor = getSharedPreferences(NAME, MODE_WORLD_WRITEABLE).edit();
//							editor.putInt("second", second);
//							editor.putInt("third", third);
//							editor.putFloat("firstprice", firstprice);
//							editor.putFloat("secondprice", secondprice);
//							editor.putFloat("thirdprice", thirdprice);
//							editor.commit();
//							tv1.setText("");
//							tv1.setText("");
////							setText(tv, firstprice, second, secondprice, third, thirdprice);
//							drawmapInstance();
//						}
//					}
//				}
//			});
//			builder.setNegativeButton("取消", null);
//			builder.create().show();
			
//			break;
		case R.id.ly1_button1:
			String strnum = etnumber.getText().toString().replaceAll(" ", "");
			if (strnum.equals("")) {
				DialogUtil.showDialog(Jisuan.this, "", "您未输入所要计算的电量", false);
			}
			else {
				inputnum = Integer.parseInt(etnumber.getText().toString());
				float num_fee = fees(inputnum);
				if (inputnum >= 0 && inputnum <= third + 30) {
					Bitmap b = drawmapInstance.drawLineNum(bitmap, inputnum, num_fee);
					iv.setImageBitmap(b);
				}
//				tv1.setText("您输入的用电量为:" + inputnum + "度\n根据当前电价计算的电费为:" + num_fee + "元");
			}
			break;
//		case R.id.ly1_button2:
//			String strfee = etfees.getText().toString().replaceAll(" ", "");
//			if (strfee.equals("")) {
//				DialogUtil.showDialog(Jisuan.this, "", "您未输入所要计算的电费", false);
//			}
//			else {
//				inputfee = Float.parseFloat(strfee);
//				float fee_num = num(inputfee);
//				if (fee_num >= 0 && fee_num <= third + 30) {
//					Bitmap c = drawmapInstance.drawLineNum(bitmap, fee_num, inputfee);
//					iv.setImageBitmap(c);
//				}
//				tv1.setText("您输入的金额为:" + inputfee + "元\n根据当前电价可使用的电量为:" + fee_num + "度");
//			}
//			break;
		}
	}
	
	public void drawmapInstance()
	{
		int width = this.getWindowManager().getDefaultDisplay().getWidth();
		int height = 120;
		bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		drawmapInstance = new DrawMyMap(bitmap, second, third, firstprice, secondprice, thirdprice);
		bitmap = drawmapInstance.drawRect();
		iv.setImageBitmap(bitmap);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.item1:
			DialogUtil.showDialog(Jisuan.this, "关于计算器", "如果您喜欢,请告诉您的朋友\n如果你有什么意见或批评，请联系我:gooogle.tian@gmail.com\n谢谢您！期待您的评价！", false);
			break;
		}
		return true;
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mi = new MenuInflater(this);
		mi.inflate(R.menu.mymenu, menu);
		return true;
	}
}
