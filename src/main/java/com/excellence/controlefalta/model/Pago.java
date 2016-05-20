package com.excellence.controlefalta.model;

public enum Pago {
	SIM("Sim"),
	NAO("Não");
	
private String descricao;
	
	Pago(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
