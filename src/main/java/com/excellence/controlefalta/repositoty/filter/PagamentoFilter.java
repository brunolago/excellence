package com.excellence.controlefalta.repositoty.filter;

import java.io.Serializable;
import java.util.Date;

public class PagamentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Date dataInicio;
	private Date dataFim;
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
}
