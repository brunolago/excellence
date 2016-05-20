package com.excellence.controlefalta.model;

public enum Motivo {
	ATESTADO("Atestado"),
	FALTA("Falta"),
	FERIAS("Férias"),
	FOLGA("Folga"),
	OUTROS("Outros");
	
private String descricao;
	
	Motivo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
