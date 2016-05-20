package com.excellence.controlefalta.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.excellence.controlefalta.model.Ausencia;
import com.excellence.controlefalta.repository.Ausencias;
import com.excellence.controlefalta.util.jpa.Transactional;

public class CadastroAusenciaService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Ausencias ausencias;
	
	@Transactional
	public Ausencia salvar(Ausencia ausencia) {
		return ausencias.guardar(ausencia);
	}
}
