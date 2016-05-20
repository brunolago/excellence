package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.excellence.controlefalta.model.Ausencia;
import com.excellence.controlefalta.model.Pago;
import com.excellence.controlefalta.service.NegocioException;
import com.excellence.controlefalta.util.jpa.Transactional;

public class Ausencias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	@Transactional
	public Ausencia guardar(Ausencia ausencia) {
		return manager.merge(ausencia);
	}
	@Transactional
	public Ausencia porId(Long id) {
		return this.manager.find(Ausencia.class, id);
	}
	@Transactional
	public List<Ausencia> listaTodos(Pago pago){
		return this.manager.createQuery("select a from Ausencia a inner join fetch a.funcionarioFaltou inner join fetch a.funcionarioSubstituto"
				+ "   inner join fetch a.condominio where a.pago = :pago order by a.dataAusencia ASC", Ausencia.class)
				.setParameter("pago", pago)
				.getResultList();
	}
	
	@Transactional
	public List<Ausencia> porNome(String nome) {
		return this.manager.createQuery("from Ausencia " +
				"where upper(nome) like :nome", Ausencia.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	

	@Transactional
	public void remover(Ausencia ausencia) {
		try {
			ausencia = porId(ausencia.getId());
			manager.remove(ausencia);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Ausencia não pode ser excluído.");
		}
	}

	
}