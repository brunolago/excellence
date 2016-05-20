package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.service.NegocioException;
import com.excellence.controlefalta.util.jpa.Transactional;

public class Cargos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cargo guardar(Cargo funcionario) {
		return manager.merge(funcionario);
	}

	public Cargo porId(Long id) {
		return this.manager.find(Cargo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> listaTodos(){
		Session session = this.manager.unwrap(Session.class);
		return session.createQuery("select c from Cargo c order by c.nome ")
				.setCacheable(true)
				.list();
	}
	
	public List<Cargo> porNome(String nome) {
		return this.manager.createQuery("from Cargo " +
				"where upper(nome) like :nome", Cargo.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	

	@Transactional
	public void remover(Cargo funcionario) {
		try {
			funcionario = porId(funcionario.getId());
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cargo não pode ser excluído.");
		}
	}

	
}