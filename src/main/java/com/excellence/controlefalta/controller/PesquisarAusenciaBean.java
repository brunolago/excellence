package com.excellence.controlefalta.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.excellence.controlefalta.model.Ausencia;
import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.model.Pago;
import com.excellence.controlefalta.repository.Ausencias;
import com.excellence.controlefalta.service.CadastroAusenciaService;
import com.excellence.controlefalta.service.CadastroPagamentoService;
import com.excellence.controlefalta.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarAusenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ausencias ausencias;

	@Inject
	CadastroPagamentoService cadastroPagamentoService;

	@Inject
	CadastroAusenciaService cadastroAusenciaService;

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale(
			"pt", "BR"));
	
	private Pagamento pagamento;
	private Ausencia ausenciaSelecionado;
	List<Ausencia> todasAusencias;
	List<Ausencia> ausenciasSelecionadas;
	int quantidadePlantao;
	Pago pago = Pago.NAO;
	private String situacaoPagamento;

	public PesquisarAusenciaBean() {
		ausenciaSelecionado = new Ausencia();
		pagamento = new Pagamento();
		todasAusencias = new ArrayList<Ausencia>();
		ausenciasSelecionadas = new ArrayList<Ausencia>();
	}

	public void excluir() {
		ausencias.remover(ausenciaSelecionado);
		FacesUtil.addInfoMessage("Lançamento excluído com sucesso.");
	}

	public void pesquisarTodos() {
		todasAusencias = ausencias.listaTodos(this.pago);
		situacaoPagamento = this.pago.getDescricao();
	}

	public void passaAusenciaParaPagamento() {
		String plantoes = "";
		for (int i = 0; i < ausenciasSelecionadas.size(); i++) {
			Date d = ausenciasSelecionadas.get(i).getDataAusencia();
			String data = df.format(d);
			data = data.substring(0, 5);
			plantoes += data + ", ";
		}

		if (plantoes.length() > 0) {
			plantoes = plantoes.substring(0, plantoes.length() - 2);
		}
		if (quantidadePlantao == 2) {
			plantoes = plantoes.replace(",", " e");
		}
		
		pagamento.setDataPagamento(ausenciasSelecionadas.get(0).getDataAusencia());
		pagamento.setFuncionario(ausenciasSelecionadas.get(0)
				.getFuncionarioSubstituto().getNome());
		pagamento.setCondominio(ausenciasSelecionadas.get(0).getCondominio()
				.getNome());
		pagamento.setPlantoes(plantoes);
		pagamento.setQuantidadeplantao(quantidadePlantao);

	}

	public void calculaPlantao() {
		if (pagamento.getValorPlantao() != null) {
			BigDecimal temp = new BigDecimal(quantidadePlantao);
			pagamento.setValorTotal(pagamento.getValorPlantao().multiply(temp));
		} else {
			BigDecimal nulo = new BigDecimal("0.00");
			pagamento.setValorTotal(nulo);
		}
	}

	public void salvarPagamento() {
		cadastroPagamentoService.salvar(this.pagamento);
		FacesUtil.addInfoMessage("Pagamento realizado com sucesso");
		
		for (int i = 0; i < ausenciasSelecionadas.size(); i++) {
			// id[i] = ausenciasSelecionadas.get(i).getId().intValue();
			Long codigoAusencia = new Long((long) ausenciasSelecionadas.get(i)
					.getId());
			ausenciaSelecionado = ausencias.porId(codigoAusencia);
			ausenciaSelecionado.setPago(Pago.SIM);
			cadastroAusenciaService.salvar(ausenciaSelecionado);
		}
		pagamento = new Pagamento();
		ausenciasSelecionadas = new ArrayList<Ausencia>();
		pesquisarTodos();
	}

	public Ausencias getAusencias() {
		return ausencias;
	}

	public Ausencia getAusenciaSelecionado() {
		return ausenciaSelecionado;
	}

	public void setAusencias(Ausencias ausencias) {
		this.ausencias = ausencias;
	}

	public void setAusenciaSelecionado(Ausencia ausenciaSelecionado) {
		this.ausenciaSelecionado = ausenciaSelecionado;
	}

	public List<Ausencia> getTodasAusencias() {
		return todasAusencias;
	}

	public void setTodasAusencias(List<Ausencia> todasAusencias) {
		this.todasAusencias = todasAusencias;
	}

	public List<Ausencia> getAusenciasSelecionadas() {
		return ausenciasSelecionadas;
	}

	public void setAusenciasSelecionadas(List<Ausencia> ausenciasSelecionadas) {
		quantidadePlantao = ausenciasSelecionadas.size();
		this.ausenciasSelecionadas = ausenciasSelecionadas;
	}

	public void setQuantidadePlantao(int quantidadePlantao) {
		this.quantidadePlantao = quantidadePlantao;
	}

	public int getQuantidadePlantao() {
		return quantidadePlantao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public boolean isPagamentoFeito() {
		if (situacaoPagamento.equals("Não")) {
			return false;
		}
		return true;
	}
}
