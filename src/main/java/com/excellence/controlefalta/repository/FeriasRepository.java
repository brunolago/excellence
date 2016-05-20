package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.excellence.controlefalta.model.Ferias;

public class FeriasRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Ferias porId(Long id) {
		return this.manager.find(Ferias.class, id);
	}

	public Ferias pesquisarFerias(Long id) {
//		Session session = this.manager.unwrap(Session.class);
//		Query query = session.createQuery("select f from Ferias f where f.funcionario.id = :id order by f.dataInicioAquisitivo desc").
//				setParameter("id", id).
//				setMaxResults(1);
		
			TypedQuery<Ferias> f = manager
					.createQuery(
							"select f from Ferias f where f.funcionario.id = :id order by f.dataInicioAquisitivo desc",
							Ferias.class).setParameter("id", id)
					.setMaxResults(1);
			return f.getSingleResult();
	}

	public List<Ferias> porNome(String nome) {
		return this.manager
				.createQuery("from Ferias " + "where upper(nome) like :nome",
						Ferias.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

}