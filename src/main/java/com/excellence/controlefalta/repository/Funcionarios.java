package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.repositoty.filter.FuncionarioFilter;
import com.excellence.controlefalta.service.NegocioException;
import com.excellence.controlefalta.util.jpa.Transactional;

public class Funcionarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Transactional
	public Funcionario guardar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}
	@Transactional
	public Funcionario porId(Long id) {
		return this.manager.find(Funcionario.class, id);
	}
	@Transactional
	public List<Funcionario> listaTodos(Situacao situacao){
		return this.manager.createQuery("select a from Funcionario a inner join fetch a.cargo where a.situacao = :ativo order by a.nome asc ", Funcionario.class)
				.setParameter("ativo", situacao)
				.getResultList();
	}
	
	//Carregamento Lazy
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Funcionario> filtrados(FuncionarioFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		criteria.setFetchMode("cargo", FetchMode.JOIN);
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		return criteria.addOrder(Order.asc("nome"))
				.setCacheable(true)
				.list();
	}
	//Carregamento Lazy
	//Filtro de situação e Nome
	@Transactional
	private Criteria criarCriteriaParaFiltro(FuncionarioFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);
		
		if (filtro.getSituacao() != null) {
			criteria.add(Restrictions.eq("situacao", filtro.getSituacao()));
		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		return criteria;
	}
	//Carregamento Lazy
	//Retorna a quatidade de funcionários filtrados
	@Transactional
	public int quantidadeFiltrados(FuncionarioFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	@Transactional
	public List<Funcionario> porNome(String nome) {
		return this.manager.createQuery("from Funcionario " +
				"where upper(nome) like :nome", Funcionario.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	

	@Transactional
	public void remover(Funcionario funcionario) {
		try {
			funcionario = porId(funcionario.getId());
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionario não pode ser excluído.");
		}
	}
	@SuppressWarnings("unchecked")
	public List<String> pesquisarBairros(String bairro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);
		return criteria.setProjection(
						Projections.distinct(Projections.property("bairro")))
				.add(Restrictions.ilike("bairro", bairro,
						MatchMode.ANYWHERE)).addOrder(Order.asc("bairro"))
				.list();
	}
	@SuppressWarnings("unchecked")
	public List<String> pesquisarCidades(String cidade) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);
		return criteria.setProjection(
						Projections.distinct(Projections.property("cidade")))
				.add(Restrictions.ilike("cidade", cidade,
						MatchMode.ANYWHERE)).addOrder(Order.asc("cidade"))
				.list();
	}
	
}