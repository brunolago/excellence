package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.model.Desconto;
import com.excellence.controlefalta.model.Motivo;
import com.excellence.controlefalta.model.Pago;
import com.excellence.controlefalta.model.Turno;
import com.excellence.controlefalta.service.CadastroPagamentoService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroPagamentoService cadastroPagamentoService;

	private Pagamento pagamento = new Pagamento();
	List<Pagamento> pagamentosSelecionadas = new ArrayList<Pagamento>();

	private void limpar() {
		pagamento = new Pagamento();
	}

	public String salvar() {
		this.pagamento = cadastroPagamentoService.salvar(this.pagamento);
		limpar();
		FacesUtil.addInfoMessage("Pagamento salvo com sucesso!");
		return "";
	}
	
	public void efeturarPagamento(){
		
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

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getPagamentosSelecionadas() {
		return pagamentosSelecionadas;
	}

	public void setPagamentosSelecionadas(List<Pagamento> pagamentosSelecionadas) {
		this.pagamentosSelecionadas = pagamentosSelecionadas;
	}

	public boolean isEditando() {
		return this.pagamento.getId() != null;
	}
}
