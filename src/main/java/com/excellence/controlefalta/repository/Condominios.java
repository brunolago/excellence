package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.repositoty.filter.CondominioFilter;
import com.excellence.controlefalta.service.NegocioException;
import com.excellence.controlefalta.util.jpa.Transactional;

public class Condominios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Condominio guardar(Condominio condominio) {
		return manager.merge(condominio);
	}
	
	public Condominio porId(Long id) {
		return this.manager.find(Condominio.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Condominio> listaTodos(Situacao situacao){
		Session session = this.manager.unwrap(Session.class);
		return session.createQuery("select c from Condominio c where c.situacao = :ativo order by c.nome asc")
				.setCacheable(true)
				.setParameter("ativo", situacao)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Condominio> Filtrados(CondominioFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Condominio.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Condominio> porNome(String nome) {
		return this.manager.createQuery("from Condominio " +
				"where upper(nome) like :nome", Condominio.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	

	@Transactional
	public void remover(Condominio condominio) {
		try {
			condominio = porId(condominio.getId());
			manager.remove(condominio);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Condominio não pode ser excluído.");
		}
	}

	
}