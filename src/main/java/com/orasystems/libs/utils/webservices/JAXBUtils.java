package com.orasystems.libs.utils.webservices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.os.Environment;
import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.WstxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

@SuppressWarnings("deprecation")
public class JAXBUtils {
	public static final String DATA_STRING = "string";
	public static final String DATA_BYTE = "byte";
	
	public static final String cabXmlUTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	public static final String cabXmlISO8859_1 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>";

	public static SoapSerializationEnvelope ExecutarMetodo(String nomeDoMetodo,
			ContentValues parametros, String dataType, String NAMESPACE,
			String _url) throws Exception,SocketTimeoutException{
		
		HttpTransportSE androidHttpTransport = null;
		SoapSerializationEnvelope envelope = null;
		List<HeaderProperty> headers = null;
		
		try {
			SoapObject request = new SoapObject(NAMESPACE, nomeDoMetodo);
			if (parametros != null) {
				for (Map.Entry<String, Object> parametro : parametros
						.valueSet()) {
					PropertyInfo pi = new PropertyInfo();
					pi.setName(parametro.getKey());
					if (dataType.equals(DATA_STRING))
						pi.setValue(parametro.getValue().toString());
					if (dataType.equals(DATA_BYTE))
						pi.setValue(parametro.getValue());
					if (dataType.equals(DATA_STRING))
						pi.setType(String.class);
					if (dataType.equals(DATA_BYTE))
						pi.setType(MarshalBase64.BYTE_ARRAY_CLASS);
					request.addProperty(pi);
				}
			}
			
			envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			if (dataType.equals(DATA_BYTE)) {
				new MarshalBase64().register(envelope); // serialization
				envelope.encodingStyle = SoapEnvelope.ENC;
			}
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport = new HttpTransportSE(_url,40000);

			Log.i("soap:", envelope.bodyOut + "");
			
			headers = new ArrayList<HeaderProperty>();
            headers.add(new HeaderProperty("Connection", "close"));
            headers.add(new HeaderProperty("Connection", "keep-alive"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
		
		try{
			androidHttpTransport.call(String.format("\"%s%s\"", NAMESPACE, nomeDoMetodo),envelope,headers);
	
			return envelope;
		}catch(SocketTimeoutException t){
			t.printStackTrace();
			throw new SocketTimeoutException("Sua conexão com a internet está instavel, por favor tente realizar o processo novamente!");
		}catch (SocketException  e) {
			e.printStackTrace();
			throw new SocketTimeoutException("Sua conexão com a internet está instavel, por favor tente realizar o processo novamente!");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			throw new SocketTimeoutException("Exception: "+e.getMessage());
		}
	}
	
	private static synchronized void GravarLog(String texto,String pasta) throws Exception{
        final String NomeDoArquivo = "Log.txt";

        File sd = Environment.getExternalStorageDirectory();
        if (sd.canWrite()){
            try{
                FileWriter fstream = new FileWriter(String.format("%s/"+pasta+"/%s", sd.getAbsolutePath(), NomeDoArquivo), true);
                fstream.write(String.format("%s: %s\n", FormatarDataHora(new Date()), texto));
                fstream.close();
            }catch (Exception ignored){
            }
        }
    }
	public static String FormatarDataHora(Date data){
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s/%s/%s %s:%s:%s", valores.getAsString("DD"), valores.getAsString("MM"), valores.getAsString("AA"),
            valores.getAsString("HH"), valores.getAsString("MI"), valores.getAsString("SS"));
    }
	
	public static void ComponentesDaDataFmt(Date data, ContentValues valores){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        valores.put("AA", String.format("%04d", cal.get(Calendar.YEAR)));
        valores.put("MM", String.format("%02d", cal.get(Calendar.MONTH) + 1));
        valores.put("DD", String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
        valores.put("HH", String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));
        valores.put("MI", String.format("%02d", cal.get(Calendar.MINUTE)));
        valores.put("SS", String.format("%02d", cal.get(Calendar.SECOND)));
        valores.put("MS", String.format("%02d", cal.get(Calendar.MILLISECOND)));
    }

	public static XStream getXStreamDomDriver(boolean ignoreInconsistentElements) {
		return getXStream(new DomDriver("UTF-8",new XmlFriendlyReplacer("__", "_")),ignoreInconsistentElements);
	}
	
	public static XStream getXStreamStaxDriver(boolean ignoreInconsistentElements) {
		return getXStream(new StaxDriver(new XmlFriendlyReplacer("__", "_")),ignoreInconsistentElements);
	}
	
	public static XStream getXStreamWSTXDriver(boolean ignoreInconsistentElements) {
		return getXStream(new WstxDriver(new XmlFriendlyReplacer("__", "_")),ignoreInconsistentElements);
	}
	
	public static XStream getXStreamXPP3Driver(boolean ignoreInconsistentElements) {
		return getXStream(new XppDomDriver(new XmlFriendlyReplacer("__", "_")),ignoreInconsistentElements);
	}

	private static XStream getXStream(HierarchicalStreamDriver driver,boolean ignoreInconsistentElements) {
		if (!ignoreInconsistentElements) {
			return new XStream(driver);
		} else {
			return new XStream(driver) {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return new MapperWrapper(next) {
						@SuppressWarnings("rawtypes")
						@Override
						public boolean shouldSerializeMember(Class definedIn,
								String fieldName) {
							if (definedIn == Object.class) {
								return false;
							}
							return super.shouldSerializeMember(definedIn,
									fieldName);
						}
					};
				}
			};
		}
	}
}
