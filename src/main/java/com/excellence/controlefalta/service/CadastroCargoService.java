package com.excellence.controlefalta.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.repository.Cargos;
import com.excellence.controlefalta.util.jpa.Transactional;

public class CadastroCargoService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Cargos cargos;
	
	@Transactional
	public Cargo salvar(Cargo cargo) {
		return cargos.guardar(cargo);
	}
}
