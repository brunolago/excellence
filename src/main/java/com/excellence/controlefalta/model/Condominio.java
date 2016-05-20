package com.excellence.controlefalta.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Condominio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	private String endereco;
	private String cnpj;
	private Situacao situacao;
	private String sindico;
	private String telefone;
	private String vencimentoFatura;
	private String contabilidade;
	private BigDecimal valorLiquido;
	private BigDecimal valorBruto;
	@Lob
	private String obs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(length = 255)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column(length = 20)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	@Column(length = 255)
	public String getSindico() {
		return sindico;
	}

	public void setSindico(String sindico) {
		this.sindico = sindico;
	}
	@Column(length = 255)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Column(length = 60)
	public String getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(String vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}
	@Column(length = 255)
	public String getContabilidade() {
		return contabilidade;
	}

	public void setContabilidade(String contabilidade) {
		this.contabilidade = contabilidade;
	}
	@Column(name="valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	@Column(name="valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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
		Condominio other = (Condominio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
