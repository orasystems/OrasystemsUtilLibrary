package com.orasystems.libs.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogUtils {
	public static void exibeMsg(String string,Activity activity) {
		new AlertDialog.Builder(activity)
			.setTitle("Info")
			.setMessage(string)
			.setPositiveButton("Ok", null)
			.create()
			.show();
	}
	
	public static void exibeMsgFinish(String string,final Activity activity, final boolean finish) {
		new AlertDialog.Builder(activity)
			.setTitle("Info")
			.setMessage(string)
			.setPositiveButton("Ok", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(finish)activity.finish();
				}
			})
			.create()
			.show();
	}
	
	public static void exibeMsg(String string,final Activity activity, OnClickListener onClickListener) {
		new AlertDialog.Builder(activity)
			.setTitle("Info")
			.setMessage(string)
			.setPositiveButton("Ok", onClickListener)
			.create()
			.show();
	}
	
	public static Builder getDialogBuilder(Activity activity){
		return new AlertDialog.Builder(activity);
	}
	
}
