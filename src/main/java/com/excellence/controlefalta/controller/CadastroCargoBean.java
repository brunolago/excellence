package com.excellence.controlefalta.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.service.CadastroCargoService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroCargoService cadastroCargoService;
	
	private Cargo cargo;	

	@PostConstruct
	public void init() {
		cargo = new Cargo();
	}

	private void limpar() {
		cargo = new Cargo();
	}

	public String salvar() {
		this.cargo = cadastroCargoService.salvar(this.cargo);
		limpar();
		FacesUtil.addInfoMessage("Cargo salvo com sucesso!");
		return "";
	}

	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean isEditando() {
		return this.cargo.getId() != null;
	}
}
