package com.orasystems.libs.utils;

import java.util.ArrayList;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Html;

/**
 * Utilitário para chamar Intents no Android
 * @author alan echer
 *
 */
@EBean
public class EmailIntentUtils {
	
	@RootContext
	public Activity activity;
	
	private String[] emailDestinatario;
	
	private String emailAssunto;
	
	private String htmlEmail;
	
	private ArrayList<Parcelable> listaDeAnexos;
	
	private String chooserTitle;
	
	private int REQUEST_CODE;
	
	
	/**
	 * CRIA INTENT PARA ENVIO DE EMAIL
	 * @author alan echer
	 */
	public void enviaEmail(){
		Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
		
		i.setType("message/rfc822");

		if(getEmailDestinatario() != null)i.putExtra(Intent.EXTRA_EMAIL, getEmailDestinatario());

		if(getEmailAssunto() != null)i.putExtra(Intent.EXTRA_SUBJECT,getEmailAssunto());

		if(getHtmlEmail() != null)i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(getHtmlEmail()));

		if(getListaDeAnexos() != null)i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, getListaDeAnexos());
		
		i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		if(getREQUEST_CODE() > 0)
			activity.startActivityForResult(Intent.createChooser(i, getChooserTitle()), getREQUEST_CODE());
		else
			activity.startActivity(Intent.createChooser(i, getChooserTitle()));
	}

	private String[] getEmailDestinatario() {
		return emailDestinatario;
	}

	public EmailIntentUtils setEmailDestinatario(String[] emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
		return this;
	}

	private String getEmailAssunto() {
		return emailAssunto;
	}

	public EmailIntentUtils setEmailAssunto(String emailAssunto) {
		this.emailAssunto = emailAssunto;
		return this;
	}

	private String getHtmlEmail() {
		return htmlEmail;
	}

	public EmailIntentUtils setHtmlEmail(String htmlEmail) {
		this.htmlEmail = htmlEmail;
		return this;
	}

	private ArrayList<Parcelable> getListaDeAnexos() {
		return listaDeAnexos;
	}

	public EmailIntentUtils setListaDeAnexos(ArrayList<Parcelable> listaDeAnexos) {
		this.listaDeAnexos = listaDeAnexos;
		return this;
	}

	private String getChooserTitle() {
		return chooserTitle;
	}

	public EmailIntentUtils setChooserTitle(String chooserTitle) {
		this.chooserTitle = chooserTitle;
		return this;
	}

	private int getREQUEST_CODE() {
		return REQUEST_CODE;
	}

	public EmailIntentUtils setREQUEST_CODE(int rEQUEST_CODE) {
		REQUEST_CODE = rEQUEST_CODE;
		return this;
	}
	
	

}
