package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.repository.Condominios;
import com.excellence.controlefalta.repositoty.filter.CondominioFilter;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarCondominioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Condominios condominios;
	
	private CondominioFilter filtro;
	List<Condominio> condominiosFiltrados;
	List<Condominio> todosCondominios;
	
	int quantidadeCondominio;
	
	private Situacao situacao = Situacao.ATIVO;
	
	private Condominio condominioSelecionado;
	
	public PesquisarCondominioBean(){
		condominioSelecionado = new Condominio();
		filtro = new CondominioFilter();
		condominiosFiltrados = new ArrayList<Condominio>();
		todosCondominios = new ArrayList<Condominio>();
	}
	
	public void excluir() {
		try{
		condominios.remover(condominioSelecionado);
		condominiosFiltrados.remove(condominioSelecionado);
		
		FacesUtil.addInfoMessage("Condominio " + condominioSelecionado.getNome() 
				+ " excluído com sucesso.");
		}catch(Exception e){
			FacesUtil.addErrorMessage("Condominio " + condominioSelecionado.getNome() 
					+ " não pode ser excluído pois possui lançamentos.");
		}
	}
	
	public void pesquisar() {
		condominiosFiltrados = condominios.Filtrados(filtro);
	}
	public void pesquisarTodos(){
		todosCondominios = condominios.listaTodos(this.situacao);
		quantidadeCondominio = todosCondominios.size();
	}

	public Condominios getCondominios() {
		return condominios;
	}

	public CondominioFilter getFiltro() {
		return filtro;
	}

	public List<Condominio> getCondominiosFiltrados() {
		return condominiosFiltrados;
	}

	public void setFiltro(CondominioFilter filtro) {
		this.filtro = filtro;
	}

	public void setCondominiosFiltrados(List<Condominio> condominiosFiltrados) {
		this.condominiosFiltrados = condominiosFiltrados;
	}

	public Condominio getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(Condominio condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public List<Condominio> getTodosCondominios() {
		return todosCondominios;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public int getQuantidadeCondominio() {
		return quantidadeCondominio;
	}

	public void setQuantidadeCondominio(int quantidadeCondominio) {
		this.quantidadeCondominio = quantidadeCondominio;
	}
}
