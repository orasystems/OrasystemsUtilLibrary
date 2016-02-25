package com.orasystems.libs.utils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

@EBean
public class InterfaceUtils {

	@RootContext
	Activity activity;
	/**
	 * EXIBE TOAST NA TELA
	 * @param string STRING A SER EXIBIDA
	 */
	@UiThread
	public void exibeToast(String string) {
		Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
	}
	
	@UiThread
	public void exibeMsg(String msg){
		DialogUtils.exibeMsg(msg, activity);
	}
	
	@UiThread
	public void exibeMsg(String msg,OnClickListener onClickListener){
		DialogUtils.exibeMsg(msg, activity,onClickListener);
	}
	
	@UiThread
	public void exibeMsgFinish(String msg,boolean finish){
		DialogUtils.exibeMsgFinish(msg, activity,finish);
	}
	
	@UiThread
	public AlertDialog.Builder getDialog(){
		return DialogUtils.getDialogBuilder(activity);
	}
}
