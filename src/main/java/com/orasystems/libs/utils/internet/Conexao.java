package com.orasystems.libs.utils.internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Conexao extends BroadcastReceiver
{
	private String tag = "Conexao";
	/**
	 * @autor echer
	 * verifica atividade da network se esta conectado
	 * @return true para conectado false para desconectado
	 */
	public static boolean conectadoOuConectando(Context context){
		try{
			ConnectivityManager cManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			return cManager.getActiveNetworkInfo().isConnectedOrConnecting();
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(tag, "Action: " + intent.getAction());
    	if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
		    @SuppressWarnings("deprecation")
			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		    String typeName = info.getTypeName();
		    String subtypeName = info.getSubtypeName();
		    boolean available = info.isAvailable();
		    Log.i(tag, "Network Type: " + typeName 
				+ ", subtype: " + subtypeName
				+ ", available: " + available);
    	}
	}
	
}