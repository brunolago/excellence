package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModelListener;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.repository.Funcionarios;
import com.excellence.controlefalta.repositoty.filter.FuncionarioFilter;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionarios funcionarios;

	private FuncionarioFilter filtro;
	private LazyDataModel<Funcionario> model;

	int quantidadeFuncionario;

	private Funcionario funcionarioSelecionado;

	private Situacao situacao = Situacao.ATIVO;

	public PesquisarFuncionarioBean() {
		funcionarioSelecionado = new Funcionario();
		// filtro = new FuncionarioFilter();

		filtro = new FuncionarioFilter();
		filtro.setSituacao(situacao);
		model = new LazyDataModel<Funcionario>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public List<Funcionario> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				//Retorna a quantitade total de funcionários
				quantidadeFuncionario = funcionarios.quantidadeFiltrados(filtro);
				
			    setRowCount(quantidadeFuncionario);

				return funcionarios.filtrados(filtro);
			}

		};

	}

	public void excluir() {
		try {
			model.removeDataModelListener((DataModelListener) funcionarioSelecionado);
			FacesUtil.addInfoMessage("Funcionario "
					+ funcionarioSelecionado.getNome()
					+ " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Funcionário "
					+ funcionarioSelecionado.getNome()
					+ " não pode ser excluído porque possuí lançamentos");
		}
	}
	

//	public void pesquisar() {
//		funcionariosFiltrados = funcionarios.Filtrados(filtro);
//	}
//
//	public void pesquisarTodos() {
//		todosFuncionarios = funcionarios.listaTodos(this.situacao);
//		quantidadeFuncionario = todosFuncionarios.size();
//	}
	
	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public FuncionarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FuncionarioFilter filtro) {
		this.filtro = filtro;
	}
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public int getQuantidadeFuncionario() {
		return quantidadeFuncionario;
	}

	public void setQuantidadeFuncionario(int quantidadeFuncionario) {
		this.quantidadeFuncionario = quantidadeFuncionario;
	}

	public LazyDataModel<Funcionario> getModel() {
		return model;
	}

}
