package com.excellence.controlefalta.model;

public enum Turno {
	DIURNO("Diurno"),
	NOTURNO("Noturno");
	
	private String descricao;
	
	Turno(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
