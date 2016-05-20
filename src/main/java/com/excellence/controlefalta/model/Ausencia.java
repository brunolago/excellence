package com.excellence.controlefalta.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Ausencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataAusencia;
	@NotNull
	@ManyToOne
	@JoinColumn(name="funcionarioFaltou_id")
	private Funcionario funcionarioFaltou;
	@NotNull
	@ManyToOne
	@JoinColumn(name="funcionarioSubstituto_id")
	private Funcionario funcionarioSubstituto;
	@NotNull
	@ManyToOne
	@JoinColumn(name="condominio_id")
	private Condominio condominio;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Desconto desconto;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Motivo motivo;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Pago pago;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Turno turno;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataAusencia() {
		return dataAusencia;
	}
	public void setDataAusencia(Date dataAusencia) {
		this.dataAusencia = dataAusencia;
	}
	public Funcionario getFuncionarioFaltou() {
		return funcionarioFaltou;
	}
	public void setFuncionarioFaltou(Funcionario funcionarioFaltou) {
		this.funcionarioFaltou = funcionarioFaltou;
	}
	public Funcionario getFuncionarioSubstituto() {
		return funcionarioSubstituto;
	}
	public void setFuncionarioSubstituto(Funcionario funcionarioSubstituto) {
		this.funcionarioSubstituto = funcionarioSubstituto;
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public Desconto getDesconto() {
		return desconto;
	}
	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
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
		Ausencia other = (Ausencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
