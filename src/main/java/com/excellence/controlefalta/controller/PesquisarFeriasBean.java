package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import com.excellence.controlefalta.model.Ferias;
import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.repository.FeriasRepository;
import com.excellence.controlefalta.repository.Funcionarios;

@Named
@ViewScoped
public class PesquisarFeriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FeriasRepository feriasRepository;
	@Inject
	private Funcionarios funcionarios;

	List<Ferias> listaDeFerias;
	List<Funcionario> listaFuncionarios;
	private Ferias ferias;

	public PesquisarFeriasBean() {
		listaDeFerias = new ArrayList<Ferias>();
		listaFuncionarios = new ArrayList<Funcionario>();
		ferias = new Ferias();
	}

	public void pesquisarTodos() {
		/* Busca todos os funcionários */
		listaFuncionarios = funcionarios.listaTodos(Situacao.ATIVO);
		Calendar calendar1 = Calendar.getInstance();
		for (int i = 0; i < listaFuncionarios.size(); i++) {
			/*
			 * Bloco try pegar todos os funcionários que tem férias e faz a soma
			 * de 1 ano e 335 dias e coloca no list de Férias
			 */
			try {
				/* Busca férias baseadas no Id do funcionário */
				ferias = feriasRepository.pesquisarFerias(listaFuncionarios
						.get(i).getId());

				if (!ferias.equals(null)) {
					calendar1.setTime(ferias.getDataInicioAquisitivo());
					calendar1.add(Calendar.YEAR, 2);
					calendar1.add(Calendar.DAY_OF_MONTH, 335);
					ferias.setNomeDoFuncionario(listaFuncionarios.get(i)
							.getNome());
					ferias.setUltimaDataParaFerias(calendar1.getTime());
					listaDeFerias.add(ferias);
				}
				/*
				 * O bloco Catch pegar os retornos sem resultados, ou seja,
				 * funcionários que não tem nenhuma férias ainda e acrescenta na
				 * data de admissão 1 ano e 335 dias, por fim acrescenta no List
				 * de Férias
				 */
			} catch (NoResultException e) {
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(listaFuncionarios.get(i).getDataAdmissao());
				calendar2.add(Calendar.YEAR, 1);
				calendar2.add(Calendar.DAY_OF_MONTH, 335);
				Ferias ferias2 = new Ferias();
				ferias2.setNomeDoFuncionario(listaFuncionarios.get(i).getNome());
				ferias2.setUltimaDataParaFerias(calendar2.getTime());
				listaDeFerias.add(ferias2);
			}
		}
	}

	public List<Ferias> getListaDeFerias() {
		return listaDeFerias;
	}

	public void setListaDeFerias(List<Ferias> listaDeFerias) {
		this.listaDeFerias = listaDeFerias;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Ferias getFerias() {
		return ferias;
	}

	public void setFerias(Ferias ferias) {
		this.ferias = ferias;
	}
}