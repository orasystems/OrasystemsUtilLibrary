package com.orasystems.libs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import com.orasystems.libs.utils.async.AsyncUtils;
import com.orasystems.libs.utils.async.GenericAsyncTask;

import android.app.Activity;

/**
 * UTILITARIO PARA DOWNLOAD DE ARQUIVOS
 * 
 * @author alane echer
 *
 */
@EBean
public class DownloaderUtils {

	@RootContext
	public Activity activity;

	@Bean(AsyncUtils.class)
	public GenericAsyncTask asyncTaskHorizontal;

	/**
	 * CALLBACK PARA O INICIO DO DOWNLOAD E A PUBLICAÇÃO DO PROGRESSO
	 */
	private CallBackDownload callBackDownload;

	private String urlDownload;

	private String diretorioArquivoSalvar;

	/**
	 * BAIXA O ARQUIVO E SALVA NO DIRETORIO ESPECIFICADO
	 * 
	 * @throws Exception
	 */
	public void download() throws Exception {
		try {
			URL url = new URL(getUrlDownload());

			// Cria e configura a conexão
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setUseCaches(false);

			// Conecta
			urlConnection.connect();

			File refArquivo = new File(getDiretorioArquivoSalvar());

			// Stream para o arquivo local
			FileOutputStream arquivoLocal = new FileOutputStream(refArquivo);

			// Stream para o arquivo remoto
			InputStream arquivoRemoto = urlConnection.getInputStream();

			// Controla o número de bytes do arquivo e quanto já foi baixado
			int tamanhoDoArquivo = urlConnection.getContentLength();
			int bytesRecebidos = 0;

			// Buffer de recepção de dados
			byte[] buffer = new byte[1024];
			int bytesNoBuffer;

			if (getCallBackDownload() != null)
				getCallBackDownload().onDownloadStart(tamanhoDoArquivo, activity);
			else
				asyncTaskHorizontal.showProgressBar(activity, "Aguarde...", "Baixando arquivo...", tamanhoDoArquivo, null, false, false, true);

			// Lê o arquivo remoto
			while ((bytesNoBuffer = arquivoRemoto.read(buffer)) > 0) {
				// Grava no arquivo local
				arquivoLocal.write(buffer, 0, bytesNoBuffer);

				// Soma os bytes recebidos
				bytesRecebidos += bytesNoBuffer;

				// Atualiza a barra de progresso
				if (getCallBackDownload() != null)
					getCallBackDownload().onDownloadProgress(bytesRecebidos);
				else
					asyncTaskHorizontal.publishProgress(bytesRecebidos, "Baixando arquivo...");
			}

			// Fecha as streams
			arquivoLocal.close();
			arquivoRemoto.close();

			if (getCallBackDownload() == null)
				asyncTaskHorizontal.hideProgressBar();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			asyncTaskHorizontal.hideProgressBar();
		}
	}

	public static interface CallBackDownload {
		public void onDownloadStart(int tamanhoArquivo, Activity activity);

		public void onDownloadProgress(int bytesRecebidos);
	}

	private CallBackDownload getCallBackDownload() {
		return callBackDownload;
	}

	public DownloaderUtils setCallBackDownload(CallBackDownload callBackDownload) {
		this.callBackDownload = callBackDownload;
		return this;
	}

	private String getUrlDownload() {
		return urlDownload;
	}

	public DownloaderUtils setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
		return this;
	}

	private String getDiretorioArquivoSalvar() {
		return diretorioArquivoSalvar;
	}

	public DownloaderUtils setDiretorioArquivoSalvar(String diretorioArquivoSalvar) {
		this.diretorioArquivoSalvar = diretorioArquivoSalvar;
		return this;
	}

}
