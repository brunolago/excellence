package com.excellence.controlefalta.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.repository.Condominios;
import com.excellence.controlefalta.util.jpa.Transactional;

public class CadastroCondominioService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Condominios condominios;
	
	@Transactional
	public Condominio salvar(Condominio condominio) {
		return condominios.guardar(condominio);
	}
}
