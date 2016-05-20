package com.excellence.controlefalta.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.model.DescontoFuncionario;
import com.excellence.controlefalta.model.EstadoCivil;
import com.excellence.controlefalta.model.Ferias;
import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.model.Sexo;
import com.excellence.controlefalta.model.Situacao;
import com.excellence.controlefalta.model.Uniforme;
import com.excellence.controlefalta.repository.Cargos;
import com.excellence.controlefalta.repository.Condominios;
import com.excellence.controlefalta.repository.Funcionarios;
import com.excellence.controlefalta.service.CadastroFuncionarioService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroFuncionarioService cadastroFuncionarioService;

	private Funcionario funcionario;

	@Inject
	private Funcionarios funcionarios;

	@Inject
	private Cargos cargos;

	@Inject
	private Condominios condominios;

	private DescontoFuncionario desconto;
	private Ferias ferias;
	private Uniforme uniforme;
	List<Cargo> listaCargos;
	List<Condominio> listaCondominios;
	Situacao situacao = Situacao.ATIVO;
	
	@PostConstruct
	public void init() {
		this.funcionario = new Funcionario();
		this.desconto = new DescontoFuncionario();
		this.ferias = new Ferias();
		this.uniforme = new Uniforme();
		this.listaCargos = new ArrayList<Cargo>();
		this.listaCondominios = new ArrayList<Condominio>();
	}

	private void limpar() {
		funcionario = new Funcionario();
		desconto = new DescontoFuncionario();
	}
	
	public String salvar() {
		String cpf = this.funcionario.getCpf();
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		this.funcionario.setCpf(cpf);
		this.funcionario = cadastroFuncionarioService.salvar(this.funcionario);
		limpar();
		FacesUtil.addInfoMessage("Funcionario salvo com sucesso!");
		return "";
	}

	public void enviaImagem(FileUploadEvent event) throws IOException {
		UploadedFile file = event.getFile();
		byte[] foto = IOUtils.toByteArray(file.getInputstream());
		funcionario.setImagem(foto);
	}

	public void carregaCargosCondominios() {
		listaCargos = cargos.listaTodos();
		listaCondominios = condominios.listaTodos(situacao);
	}

	public void adicionarDesconto() {
		this.funcionario.descontosAdicionados(this.desconto);
		this.desconto = new DescontoFuncionario();
	}
	
	public void adicionarUniforme(){
		if (this.uniforme.getDataDeEntrega() == null || this.uniforme.getUniformeEntregue() == null) {
			FacesUtil.addErrorMessage("Todos os campos devem ser preenchido");
		} else {
			this.funcionario.uniformeAdicionados(this.uniforme);
			this.uniforme = new Uniforme();
		}
	}

	public void removerDesconto() {
		this.funcionario.getDescontoFuncionario().remove(desconto);
	}

	public void adicionarFerias() {
		if (this.ferias.getDataFimAquisitivo() == null|| this.ferias.getDataInicioAquisitivo() == null
				|| this.ferias.getDataInicioGozo() == null || this.ferias.getDataFimGozo() == null) {
			FacesUtil.addErrorMessage("Todos os campos devem ser preenchido");
		} else {
			this.funcionario.feriasAdicionados(this.ferias);
			this.ferias = new Ferias();
		}
	}

	public void removerFerias() {
		this.funcionario.getFerias().remove(ferias);
	}

	public List<String> sugerirBairro(Object event) {
		List<String> bairros = funcionarios.pesquisarBairros(event.toString());
		return bairros;
	}

	public List<String> sugerirCidade(Object event) {
		List<String> cidades = funcionarios.pesquisarCidades(event.toString());
		return cidades;
	}

	public Situacao[] getSituacao() {
		return Situacao.values();
	}

	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public EstadoCivil[] getEstadoCivil() {
		return EstadoCivil.values();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;

	}

	public boolean isEditando() {
		return this.funcionario.getId() != null;
	}

	public void setDesconto(DescontoFuncionario desconto) {
		this.desconto = desconto;
	}

	public DescontoFuncionario getDesconto() {
		return desconto;
	}

	public Ferias getFerias() {
		return ferias;
	}

	public void setFerias(Ferias ferias) {
		this.ferias = ferias;
	}

	public Uniforme getUniforme() {
		return uniforme;
	}

	public void setUniforme(Uniforme uniforme) {
		this.uniforme = uniforme;
	}

	public List<Cargo> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<Cargo> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public List<Condominio> getListaCondominios() {
		return listaCondominios;
	}

	public void setListaCondominios(List<Condominio> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	// Aviso para informar a data de Demissão quanto colocar a situação com
	// INATIVO
	public void informarDataDeDemissao() {
		if (this.funcionario.getSituacao().equals(Situacao.INATIVO)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Aviso Importante", "Informe a Data de Demissão"
							+ " no campo Observações.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

}
