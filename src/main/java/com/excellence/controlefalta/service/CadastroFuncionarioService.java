package com.excellence.controlefalta.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.repository.Funcionarios;
import com.excellence.controlefalta.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionarios funcionarios;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarios.guardar(funcionario);
	}
}
