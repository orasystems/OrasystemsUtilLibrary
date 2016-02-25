package com.orasystems.libs.utils.log;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.orasystems.libs.utils.files.FileManager;
import com.orasystems.libs.utils.internet.Conexao;
import com.orasystems.libs.utils.log.manager.LogManager;
import com.orasystems.libs.utils.log.model.LogDB;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@EBean
public class LogUtils {

    public static final String tag = "LogUtils";
    @Bean
    public LogManager logManager;
    public String pathSD;
    public String directory;

    public void writeLogErrorWSOSMobile(final String tag, final String metodo, Exception e, final InformativoErro informativoErro, final CallBackLog callBackLog, final Context context) {
        writeLogError(tag, metodo, stackTraceToString(e), informativoErro.getMensagemErro());
        if (Conexao.conectadoOuConectando(context)) {
            new AlertDialog.Builder(context).setTitle("Atenção")
                    .setCancelable(false)
                    .setMessage("Ocorreu um erro ao realizar o processo, deseja reportar o problema?")
                    .setPositiveButton("Reportar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final EditText editText = new EditText(context);
                            editText.setHint("Descreva aqui o processo que estava realizando quando ocorreu o erro (Opcional)");

                            new AlertDialog.Builder(context).setTitle("Mensagem").setView(editText).setPositiveButton("Reportar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String msgUsuario = editText.getText().toString();

                                    if (msgUsuario != null & msgUsuario.length() > 0) {
                                        informativoErro.setMensagemErro(informativoErro.getMensagemErro() + ". Mensagem usuário: " + msgUsuario);
                                    }

                                    informativoErro.setMensagemErro(String.format("Erro no método: %s (tag: %s). %s", metodo, tag, informativoErro.getMensagemErro()));

                                    Toast.makeText(context, "Reportando problema...", Toast.LENGTH_LONG).show();
                                    enviaLog(informativoErro, callBackLog);
                                }
                            }).setNegativeButton("Fechar", null).create().show();
                        }
                    })
                    .setNegativeButton("Fechar", null)
                    .create().show();
        } else {
            new AlertDialog.Builder(context).setTitle("Atenção")
                    .setMessage("Não foi possível realizar o processo! Info: " + e.getMessage())
                    .setPositiveButton("Ok", null)
                    .setCancelable(false)
                    .create().show();
        }
    }

    @Background
    public void enviaLog(InformativoErro informativoErro, CallBackLog callBackLog) {
        callBackLog.enviarLog(informativoErro);
    }

    public LogUtils build(String pathSD, String directory) {
        this.pathSD = pathSD;
        this.directory = directory;
        return this;
    }

    public void writeLogInfo(String tag, String metodo, String string, String message) {
        Log.i(tag, string);
        LogDB l = new LogDB();
        //l.setLog(string);
        l.setLog("");
        l.setMessage(message);
        l.setTipo(LogDB.TIPO_INFO);
        l.setData(new Date(System.currentTimeMillis()));
        l.setMetodo(metodo);
        try {
            logManager.save(l);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeLogError(String tag, String metodo, String string, String message) {
        Log.e(tag, string);
        LogDB l = new LogDB();
        //l.setLog(string);
        l.setLog("");
        l.setTipo(LogDB.TIPO_ERROR);
        l.setMessage(message);
        l.setData(new Date(System.currentTimeMillis()));
        l.setMetodo(metodo);
        try {
            logManager.save(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLogWarn(String tag, String metodo, String string, String message) {
        Log.w(tag, string);
        LogDB l = new LogDB();
        //l.setLog(string);
        l.setLog("");
        l.setTipo(LogDB.TIPO_WARN);
        l.setMessage(message);
        l.setData(new Date(System.currentTimeMillis()));
        l.setMetodo(metodo);
        try {
            logManager.save(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLogInfo(String tag, String string) {
        Log.i(tag, string);
    }

    public void showLogError(String tag, String string) {
        Log.e(tag, string);
    }

    public void showLogWarn(String tag, String string) {
        Log.w(tag, string);
    }

    public String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @SuppressWarnings("unused")
    private boolean permissao() {
        if (!FileManager.isExternalStorageReadable()) {
            Log.i(tag, "Sem permissão de leitura no cartão SD");
            return false;
        }
        if (!FileManager.isExternalStorageWritable()) {
            Log.i(tag, "Sem permissão de escrita no cartão SD");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unused")
    private boolean diretorio() {
        if (!FileManager.diretorioExiste(pathSD)) {
            if (!FileManager.criaDiretorio(pathSD, directory)) {
                Log.i(tag, "Erro ao criar diretório");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

}
