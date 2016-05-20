package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.repository.Pagamentos;
import com.excellence.controlefalta.repositoty.filter.PagamentoFilter;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarPagamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Pagamentos pagamentos;

	
	private Pagamento pagamentoSelecionado;
	List<Pagamento> todasPagamentos;
	private PagamentoFilter filtro;
	
	public PesquisarPagamentoBean(){
		filtro = new PagamentoFilter();
		pagamentoSelecionado = new Pagamento();
		todasPagamentos = new ArrayList<Pagamento>();
	}
	
	public void excluir() {
		pagamentos.remover(pagamentoSelecionado);
		FacesUtil.addInfoMessage("Pagamento excluído com sucesso.");
	}
	
	public void pesquisarTodos(){
		todasPagamentos = pagamentos.filtrados(filtro);
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public List<Pagamento> getTodasPagamentos() {
		return todasPagamentos;
	}

	public void setTodasPagamentos(List<Pagamento> todasPagamentos) {
		this.todasPagamentos = todasPagamentos;
	}

	public PagamentoFilter getFiltro() {
		return filtro;
	}
	
}
