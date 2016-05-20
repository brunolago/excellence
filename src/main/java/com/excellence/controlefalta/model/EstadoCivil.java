package com.excellence.controlefalta.model;

public enum EstadoCivil {
	CASADO("Casado"), 
	SOLTEIRO("Solteiro"),
	OUTROS("Outros");
	
private String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
