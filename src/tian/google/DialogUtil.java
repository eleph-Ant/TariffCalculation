package tian.google;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * 
 * @author TianHua
 *
 */
public class DialogUtil {
	public final static String sure = "OK";

	public static void showDialog(final Context context, String title,
			String msg, boolean closeSelf) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(title).setMessage(msg);
		if (closeSelf) {
			builder.setPositiveButton(sure, new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					((Activity) context).finish();
				}
			});
		} else {
			builder.setPositiveButton(sure, null);
		}
		builder.create().show();
	}
	
	public static String hex2HexString(byte[] b) {
		int len = b.length;
		int[] x = new int[len];
		String[] y = new String[len];
		StringBuilder str = new StringBuilder();
		int j = 0;
		for (; j < len; j++) {
			x[j] = b[j] & 0xff;
			y[j] = Integer.toHexString(x[j]);
			while (y[j].length() < 2) {
				y[j] = "0" + y[j];
			}
			str.append(y[j]);
			str.append(" ");
		}
		return new String(str);
	}
	
	public static float floatFormat(float a){
		java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
		return Float.parseFloat(df.format(a));
	}
}
