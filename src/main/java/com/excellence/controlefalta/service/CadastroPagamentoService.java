package com.excellence.controlefalta.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.repository.Pagamentos;
import com.excellence.controlefalta.util.jpa.Transactional;

public class CadastroPagamentoService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Pagamentos pagamentos;
	
	@Transactional
	public Pagamento salvar(Pagamento pagamento) {
		return pagamentos.guardar(pagamento);
	}
}
