package com.excellence.controlefalta.repositoty.filter;

import java.io.Serializable;

public class AusenciaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int primeiroRegistro;
	private int quantidadeRegistro;
	private String propriedadeOrdenação;
	private boolean ascendente;
	
	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}
	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}
	public int getQuantidadeRegistro() {
		return quantidadeRegistro;
	}
	public void setQuantidadeRegistro(int quantidadeRegistro) {
		this.quantidadeRegistro = quantidadeRegistro;
	}
	public String getPropriedadeOrdenação() {
		return propriedadeOrdenação;
	}
	public void setPropriedadeOrdenação(String propriedadeOrdenação) {
		this.propriedadeOrdenação = propriedadeOrdenação;
	}
	public boolean isAscendente() {
		return ascendente;
	}
	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}
	
	
}
