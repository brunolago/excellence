package com.excellence.controlefalta.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.service.CadastroCondominioService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCondominioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroCondominioService cadastroCondominioService;
	
	private Condominio condominio;	

	@PostConstruct
	public void init() {
		condominio = new Condominio();
	}

	private void limpar() {
		condominio = new Condominio();
	}

	public String salvar() {
		this.condominio = cadastroCondominioService.salvar(this.condominio);
		limpar();
		FacesUtil.addInfoMessage("Condominio salvo com sucesso!");
		return "";
	}

	public Condominio getCondominio() {
		return condominio;
	}
	
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public Situacao[] getSituacao() {
		return Situacao.values();
	}

	public boolean isEditando() {
		return this.condominio.getId() != null;
	}
}
