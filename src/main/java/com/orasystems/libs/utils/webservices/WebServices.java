package com.orasystems.libs.utils.webservices;

import android.content.ContentValues;

import com.thoughtworks.xstream.XStream;

import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.net.SocketTimeoutException;

public class WebServices {
	/**
	 * Namespace para as chamadas dos WebMethods
	 */
	public static final String DATA_STRING = "string";
	public static final String DATA_BYTE = "byte";
	
	public static final String FORMATO_DATA_GSON = "yyyy-MM-dd hh:mm:ss";
	
	private String url;
	private String nameSpace;

	
	private SoapSerializationEnvelope executaMetodo(String nomeDoMetodo, ContentValues parametros, String dataType) throws Exception,SocketTimeoutException{
		return JAXBUtils.ExecutarMetodo(nomeDoMetodo, parametros, dataType, getNameSpace(), getUrl());
	}
	
	public SoapSerializationEnvelope executaMetodoWS(String nomeMetodo,String xmlParameter,String xml) throws Exception,SocketTimeoutException{
		ContentValues parametros = new ContentValues();
		parametros.put(xmlParameter, xml);
		return executaMetodo(nomeMetodo, parametros, JAXBUtils.DATA_STRING);
	}
	
	public XStream getXStream(boolean ignoreIncosistentElements){
		XStream xstream = JAXBUtils.getXStreamXPP3Driver(ignoreIncosistentElements);
		xstream.autodetectAnnotations(true);
		return xstream;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	
}