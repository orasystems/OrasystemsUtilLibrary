package com.orasystems.libs.utils.async;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;

@EBean
public class AsyncUtils implements GenericAsyncTask{
 
	@Override
	@UiThread
	public void publishProgress(int progress,String message) {
		progressDialog.setProgress(progress);
		if(message != null)progressDialog.setMessage(message);
	}
	@Override
	@UiThread
	public void hideProgressBar(){
		if(progressDialog != null)progressDialog.dismiss();
	}
	private ProgressDialog progressDialog;
	@Override
	@UiThread
	public void showProgressBar(Activity activity,String title,String message,int max,Drawable icon,boolean setCancelable,boolean setIndeterminate,boolean progressHorizontal){
		progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(setCancelable);
        progressDialog.setIndeterminate(setIndeterminate);
        if(progressHorizontal)progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(max);
        progressDialog.setTitle(title);
        progressDialog.setIcon(icon);
        progressDialog.setMessage(message);
        progressDialog.show();
	}
	
	@Override
	public ProgressDialog getProgressDialog(){
		return progressDialog;
	}
	
	@UiThread
	@Override
	public void atualizaProgressDialog(GenericAsyncTask asyncTask,String message,int progress){
		if(message != null)asyncTask.getProgressDialog().setMessage(message);
		if(progress > 0)asyncTask.getProgressDialog().setProgress(progress);
	}


}
