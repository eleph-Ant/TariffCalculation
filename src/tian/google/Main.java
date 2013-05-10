package tian.google;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;

public class Main extends TabActivity implements OnCheckedChangeListener
{
	
	private TabHost tabHost;
	private Intent intentJisuan;// Blog
	private Intent intentShezhi;// News
	private Intent intentLishi;// search
	private Intent intentShengdian;// RSS
	
	private RadioButton rbJisuan;
	private RadioButton rbShezhi;
	private RadioButton rbLishi;
	private RadioButton rbShengdian;
	
	public String whichTab = "";// 当前选中Tab
	
	Resources res;// 资源
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		res = this.getResources();
		
		intentJisuan = new Intent(this, Jisuan.class);
		intentShezhi = new Intent(this, Shezhi.class);
		intentLishi = new Intent(this, Lishi.class);
		intentShengdian = new Intent(this, Shengdian.class);
		
		InitialRadios();
		InitialTab();
	}
	
	/**
	 * 初始化单选按钮
	 */
	private void InitialRadios()
	{
		rbJisuan = (RadioButton) findViewById(R.id.TabBlog);
		rbJisuan.setOnCheckedChangeListener(this);
		rbShezhi = (RadioButton) findViewById(R.id.TabNews);
		rbShezhi.setOnCheckedChangeListener(this);
		rbLishi = (RadioButton) findViewById(R.id.TabRss);
		rbLishi.setOnCheckedChangeListener(this);
		rbShengdian = (RadioButton) findViewById(R.id.TabSearch);
		rbShengdian.setOnCheckedChangeListener(this);
	}
	
	/**
	 * 初始化Tab
	 */
	private void InitialTab()
	{
		tabHost = this.getTabHost();
		
		// tabHost.addTab(buildTabSpec("search", R.string.main_search,
		// R.drawable.icon, intentSearch));//fix tabHost bug:set the first tab
		// as default tab
		tabHost.addTab(buildTabSpec("blog", R.string.jisuan, R.drawable.icon,
				intentJisuan));
		tabHost.addTab(buildTabSpec("news", R.string.shezhi, R.drawable.icon,
				intentShezhi));
		tabHost.addTab(buildTabSpec("rss", R.string.lishi, R.drawable.icon,
				intentShengdian));
		tabHost.addTab(buildTabSpec("search", R.string.shengdian,
				R.drawable.icon, intentLishi));
		rbJisuan.setChecked(true);
	}
	
	/**
	 * 公用初始化Tab
	 */
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content)
	{
		return tabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}
	
	/**
	 * 设置当前Tab被选中后的Activity
	 * 
	 * @param buttonView
	 * @param isChecked
	 */
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if (!isChecked) { return; }
		switch (buttonView.getId())
		{
		case R.id.TabBlog:
			whichTab = "blog";
			tabHost.setCurrentTabByTag("blog");
			break;
		case R.id.TabNews:
			whichTab = "news";
			tabHost.setCurrentTabByTag("news");
			break;
		case R.id.TabRss:
			whichTab = "rss";
			tabHost.setCurrentTabByTag("rss");
			break;
		case R.id.TabSearch:
			whichTab = "search";
			tabHost.setCurrentTabByTag("search");
			break;
		}
	}
	
}
