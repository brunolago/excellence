package com.excellence.controlefalta.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Documento {
	private Integer identidade;
	private String orgaoEmissor;
	private Date dataEmissao;
	private Integer titulo;
	private String zona;
	private String secao;
	private Integer ctps;
	private Integer serie;
	private String ufCtps;
	private Integer cpf;
	private Integer cnh;
	private Integer reservista;
	private Integer pis;
	public Integer getIdentidade() {
		return identidade;
	}
	public void setIdentidade(Integer identidade) {
		this.identidade = identidade;
	}
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor.toUpperCase();
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Integer getTitulo() {
		return titulo;
	}
	public void setTitulo(Integer titulo) {
		this.titulo = titulo;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getSecao() {
		return secao;
	}
	public void setSecao(String secao) {
		this.secao = secao;
	}
	public Integer getCtps() {
		return ctps;
	}
	public void setCtps(Integer ctps) {
		this.ctps = ctps;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public String getUfCtps() {
		return ufCtps;
	}
	public void setUfCtps(String ufCtps) {
		this.ufCtps = ufCtps.toUpperCase();
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	public Integer getCnh() {
		return cnh;
	}
	public void setCnh(Integer cnh) {
		this.cnh = cnh;
	}
	public Integer getReservista() {
		return reservista;
	}
	public void setReservista(Integer reservista) {
		this.reservista = reservista;
	}
	public Integer getPis() {
		return pis;
	}
	public void setPis(Integer pis) {
		this.pis = pis;
	}
	
	
}
