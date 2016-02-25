package com.orasystems.libs.utils.log;

/**
 * Created by orasystems on 22/02/16.
 */
public class MensagemRetorno {

    private int codigo;
    private String mensagem;

    public MensagemRetorno() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}