package com.excellence.controlefalta.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
public class Ferias implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInicioAquisitivo;
	private Date dataFimAquisitivo;
	private Date dataInicioGozo;
	private Date dataFimGozo;
	private Funcionario funcionario;
	private String nomeDoFuncionario;
	private Date ultimaDataParaFerias;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataInicioAquisitivo() {
		return dataInicioAquisitivo;
	}
	public void setDataInicioAquisitivo(Date dataInicioAquisitivo) {
		this.dataInicioAquisitivo = dataInicioAquisitivo;
	}
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataFimAquisitivo() {
		return dataFimAquisitivo;
	}
	public void setDataFimAquisitivo(Date dataFimAquisitivo) {
		this.dataFimAquisitivo = dataFimAquisitivo;
	}
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataInicioGozo() {
		return dataInicioGozo;
	}
	public void setDataInicioGozo(Date dataInicioGozo) {
		this.dataInicioGozo = dataInicioGozo;
	}
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataFimGozo() {
		return dataFimGozo;
	}
	public void setDataFimGozo(Date dataFimGozo) {
		this.dataFimGozo = dataFimGozo;
	}
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@Transient
	public String getNomeDoFuncionario() {
		return nomeDoFuncionario;
	}
	public void setNomeDoFuncionario(String nomeDoFuncionario) {
		this.nomeDoFuncionario = nomeDoFuncionario;
	}
	@Transient
	@Temporal(TemporalType.DATE)
	public Date getUltimaDataParaFerias() {
		return ultimaDataParaFerias;
	}
	public void setUltimaDataParaFerias(Date ultimaDataParaFerias) {
		this.ultimaDataParaFerias = ultimaDataParaFerias;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ferias other = (Ferias) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
