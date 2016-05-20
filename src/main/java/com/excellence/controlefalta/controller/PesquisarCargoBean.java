package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.repository.Cargos;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarCargoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Cargos cargos;
	
	List<Cargo> cargosFiltrados;
	List<Cargo> todosCargos;
	
	private Cargo cargoSelecionado;
	
	public PesquisarCargoBean(){
		cargoSelecionado = new Cargo();
		cargosFiltrados = new ArrayList<Cargo>();
		todosCargos = new ArrayList<Cargo>();
	}
	
	public void excluir() {
		try{
		cargos.remover(cargoSelecionado);
		cargosFiltrados.remove(cargoSelecionado);
		
		FacesUtil.addInfoMessage("Cargo " + cargoSelecionado.getNome() 
				+ " excluído com sucesso.");
		}catch(Exception e){
			FacesUtil.addErrorMessage("Cargo " + cargoSelecionado.getNome() 
					+ " não pode ser excluído pois possui lançamentos.");
		}
	}
	
	public void pesquisarTodos(){
		if(cargoSelecionado == null){
			cargoSelecionado = new Cargo();
		}
		todosCargos = cargos.listaTodos();
	}

	public Cargos getCargos() {
		return cargos;
	}


	public List<Cargo> getCargosFiltrados() {
		return cargosFiltrados;
	}


	public void setCargosFiltrados(List<Cargo> cargosFiltrados) {
		this.cargosFiltrados = cargosFiltrados;
	}

	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}

	public List<Cargo> getTodosCargos() {
		return todosCargos;
	}
	
	
}
