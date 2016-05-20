package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Ausencia;
import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.model.Desconto;
import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.model.Motivo;
import com.excellence.controlefalta.model.Pago;
import com.excellence.controlefalta.model.Turno;
import com.excellence.controlefalta.repository.Condominios;
import com.excellence.controlefalta.repository.Funcionarios;
import com.excellence.controlefalta.service.CadastroAusenciaService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAusenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroAusenciaService cadastroAusenciaService;
	
	@Inject
	Funcionarios funcionarios;
	
	@Inject
	Condominios condominios;

	private Ausencia ausencia = new Ausencia();;

	private void limpar() {
		ausencia = new Ausencia();
	}

	public String salvar() {
		this.ausencia = cadastroAusenciaService.salvar(this.ausencia);
		limpar();
		FacesUtil.addInfoMessage("Ausencia salvo com sucesso!");
		return "";
	}

	public Motivo[] getMotivo() {
		return Motivo.values();
	}

	public Pago[] getPago() {
		return Pago.values();
	}

	public Turno[] getTurno() {
		return Turno.values();
	}

	public Desconto[] getDesconto() {
		return Desconto.values();
	}

	public Ausencia getAusencia() {
		return ausencia;
	}

	public void setAusencia(Ausencia ausencia) {
		this.ausencia = ausencia;
	}
	
	public List<Funcionario> completarFuncionarios(String nome) {
		return this.funcionarios.porNome(nome);
	}
	public List<Condominio> completarCondominios(String nome) {
		return this.condominios.porNome(nome);
	}

	public boolean isEditando() {
		return this.ausencia.getId() != null;
	}
}
