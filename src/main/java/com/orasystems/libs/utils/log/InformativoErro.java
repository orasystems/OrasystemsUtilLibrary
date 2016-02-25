package com.orasystems.libs.utils.log;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by orasystems on 19/02/16.
 */
public class InformativoErro implements Serializable {

    private Date horaEnvio;

    private String nomeEmpresa;

    private int codigoErro;

    private String sistema;

    private Long id;

    private String modulo;

    private String mensagemErro;

    private String cnpjEmpresa;

    private Date dataEnvio;

    private String ipMaquina;

    private String usuarioMaquina;

    private String versaoAtual;

    public InformativoErro() {

    }

    public Date getHoraEnvio() {
        return this.horaEnvio;
    }

    public void setHoraEnvio(Date horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public int getCodigoErro() {
        return this.codigoErro;
    }

    public void setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModulo() {
        return this.modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getMensagemErro() {
        return this.mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getCnpjEmpresa() {
        return this.cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Date getDataEnvio() {
        return this.dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getIpMaquina() {
        return ipMaquina;
    }

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public String getUsuarioMaquina() {
        return usuarioMaquina;
    }

    public void setUsuarioMaquina(String usuarioMaquina) {
        this.usuarioMaquina = usuarioMaquina;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

}

