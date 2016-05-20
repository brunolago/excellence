package com.excellence.controlefalta.repositoty.filter;

import java.io.Serializable;

public class CondominioFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
