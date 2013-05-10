package tian.google;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Bitmap.Config;

public class DrawMyMap
{
	private Bitmap bitmap;
	int second;
	int third;
	float fp;
	float sp;
	float tp;
	float totle;
	
	public DrawMyMap(Bitmap map, int second, int third, float fp, float sp, float tp)
	{
		this.bitmap = map;
		this.second = second;
		this.third = third;
		this.fp = fp;
		this.sp = sp;
		this.tp = tp;
	}
	
	public Bitmap drawRect()
	{
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int end = third + 30;
		float f1 = fp * second;
		float f2 = f1 + (third - second) * sp;
		float f3 = f2 + (end - third) * tp;
		totle = f3;
		float unitw = (float) (width - 60) / end;
		float x0 = 20, y0 = height - 10;
		float x1 = unitw * second + x0, y1 = y0 - f1 / f3 * y0;
		float x2 = unitw * third + x0, y2 = y0 - f2 / f3 * y0;
		float x3 = width - 40, y3 = 0;
		Canvas canvas = new Canvas(bitmap);
		Paint paintBackline = new Paint();
		canvas.drawColor(Color.WHITE);
		
		Paint paintv = new Paint();
		PathEffect pathEffect = new DashPathEffect(new float[] { 10, 10, 10, 10 }, 0);
		paintv.setPathEffect(pathEffect);
		
		Paint paintgreen = new Paint();
		paintgreen.setColor(Color.GREEN);
		paintgreen.setAntiAlias(true);
		
		Paint paintOrange = new Paint();
		paintOrange.setColor(Color.BLUE);
		paintOrange.setAntiAlias(true);
		
		Paint paintRed = new Paint();
		paintRed.setColor(Color.RED);
		paintRed.setAntiAlias(true);
		// 四条横着的背景线
		canvas.drawLine(x0, y0, x3, y0, paintBackline);
		canvas.drawLine(x0, y1, x3, y1, paintBackline);
		canvas.drawLine(x0, y2, x3, y2, paintBackline);
		canvas.drawLine(x0, y3, x3, y3, paintBackline);
		// 四条竖着的背景线和阶梯电价线
		canvas.drawLine(x0, 0, x0, y0, paintBackline);
		canvas.drawLine(x1, 0, x1, y0, paintv);
		canvas.drawLine(x2, 0, x2, y0, paintv);
		canvas.drawLine(x3, 0, x3, y0, paintBackline);
		// 三条连着的线
		canvas.drawLine(x0, y0, x1, y1, paintgreen);
		canvas.drawLine(x1, y1, x2, y2, paintOrange);
		canvas.drawLine(x2, y2, x3, y3, paintRed);
		// 价格数字
		canvas.drawText("" + f3, width - 40, 10, paintBackline);
		canvas.drawText("" + f2, width - 40, y2 + 10, paintBackline);
		canvas.drawText("" + f1, width - 40, y1 + 10, paintBackline);
		canvas.drawText("0", width - 40, y0, paintBackline);
		// 电量数字
		canvas.drawText("0", x0, y0 + 10, paintBackline);
		canvas.drawText("" + second, x1, y0 + 10, paintBackline);
		canvas.drawText("" + third, x2, y0 + 10, paintBackline);
		canvas.drawText("" + end, x3, y0 + 10, paintBackline);
		// 元,度
		canvas.drawText("元", 8, 10, paintBackline);
		canvas.drawText("度", x3 + 25, y0 + 8, paintBackline);
		return bitmap;
	}
	
	public Bitmap drawLine(Bitmap bmap, float num, float fee)
	{
		int height = bitmap.getHeight();
		float position = num / (third + 30) * (bitmap.getWidth() - 60) + 20;
		float hei = fee/(bitmap.getHeight())*(height);
		Point p = getPoint(num,fee);
		Bitmap chachebitmap = Bitmap.createBitmap(bmap.getWidth(), bmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(chachebitmap);
		Paint paint = new Paint();
		canvas.drawBitmap(bitmap, 0, 0, paint);
		paint.setColor(Color.BLUE);
//		canvas.drawLine(position,height- hei, position, height, paint);
//		canvas.drawText("" + num, position, bitmap.getHeight(), paint);
//		canvas.drawLine(position, height-hei, bitmap.getWidth() - 60, height-hei, paint);
//		canvas.drawText("" + fee, bitmap.getWidth(), hei, paint);
		
		canvas.drawLine(p.getX(),p.getY(), p.getX(), height-10, paint);
		canvas.drawText("" + num, p.getX(), height, paint);
		canvas.drawLine(p.getX(),p.getY(), bitmap.getWidth() - 40, p.getY(), paint);
		canvas.drawText("" + fee, bitmap.getWidth()-40, p.getY(), paint);
		
		return chachebitmap;
	}
	
	public Bitmap drawLineNum(Bitmap bmap, float num, float fee)
	{
		return drawLine(bmap, num, fee);
	}
	
	public Point getPoint(float num,float fee){
		float x = num / (third + 30) * (bitmap.getWidth() - 60) + 20;
		int height = bitmap.getHeight()-10;
		float y = height-fee/(totle)*(height);
		
		return new Point(x,y);
	}
	
}
