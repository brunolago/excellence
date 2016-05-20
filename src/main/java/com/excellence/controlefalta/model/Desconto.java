package com.excellence.controlefalta.model;

public enum Desconto {
	SIM("Sim"),
	NAO("Não");
	
private String descricao;
	
	Desconto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

