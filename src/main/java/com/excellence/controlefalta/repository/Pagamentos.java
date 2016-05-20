package com.excellence.controlefalta.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.repositoty.filter.PagamentoFilter;
import com.excellence.controlefalta.service.NegocioException;
import com.excellence.controlefalta.util.jpa.Transactional;

public class Pagamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Pagamento guardar(Pagamento pagamento) {
		return manager.merge(pagamento);
	}
	
	public Pagamento porId(Long id) {
		return this.manager.find(Pagamento.class, id);
	}
	
	public List<Pagamento> listaTodos(){
		return this.manager.createQuery("select p from Pagamento p order by p.funcionario ASC ", Pagamento.class)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagamento> filtrados(PagamentoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Pagamento.class);
		criteria.add(Restrictions.ge("dataPagamento", filtro.getDataInicio())).add(Restrictions.le("dataPagamento", filtro.getDataFim()));


		return criteria.addOrder(Order.desc("dataPagamento")).list();
	}
	
	public List<Pagamento> porNome(String nome) {
		return this.manager.createQuery("from Pagamento " +
				"where upper(nome) like :nome", Pagamento.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	

	@Transactional
	public void remover(Pagamento pagamento) {
		try {
			pagamento = porId(pagamento.getId());
			manager.remove(pagamento);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Pagamento não pode ser excluído.");
		}
	}

	
}