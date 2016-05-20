	package com.excellence.controlefalta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;



@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private byte[] imagem;
	private String nome;
	private Date dataAdmissao;
	private Situacao situacao;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefoneResidencial;
	private String telefoneCelular;
	private String telefoneComercial;
	private List<DescontoFuncionario> descontoFuncionario;
	private List<Ferias> ferias;
	private List<Uniforme> uniformes;
	private Cargo cargo;
	private Condominio condominio;
	private String obs;
	
	private String identidade;
	private String orgaoEmissor;
	private Date dataEmissao;
	private String titulo;
	private String zona;
	private String secao;
	private String ctps;
	private String serie;
	private String ufCtps;
	private String cpf;
	private String cnh;
	private String reservista;
	private String pis;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Lob
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@NotBlank
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 14)
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 14)
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	@Column(length = 80)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Column(length = 60)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	@Column(length = 60)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Column(length = 60)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	@Column(length = 10)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	@Column(length = 14)
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}	
	@Column(length = 14)
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	@Column(length = 14)
	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	@OneToMany(mappedBy="funcionario", cascade = CascadeType.ALL)
	public List<DescontoFuncionario> getDescontoFuncionario() {
		return descontoFuncionario;
	}

	public void setDescontoFuncionario(List<DescontoFuncionario> descontoFuncionario) {
		this.descontoFuncionario = descontoFuncionario;
	}
	@Lob
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	@Column(length = 20)
	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	@Column(length = 10)
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor.toUpperCase();
	}
	@Temporal(TemporalType.DATE)
	@Column(length = 14)
	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	@Column(length = 20)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Column(length = 10)
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
	@Column(length = 10)
	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}
	@Column(length = 20)
	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}
	@Column(length = 18)
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	@Column(length = 60)
	public String getUfCtps() {
		return ufCtps;
	}

	public void setUfCtps(String ufCtps) {
		this.ufCtps = ufCtps.toUpperCase();
	}
	@Column(length = 20)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Column(length = 20)
	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	@Column(length = 20)
	public String getReservista() {
		return reservista;
	}

	public void setReservista(String reservista) {
		this.reservista = reservista;
	}
	@Column(length = 20)
	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}
	
	@OneToMany(mappedBy="funcionario", cascade = CascadeType.ALL)
	public List<Ferias> getFerias() {
		return ferias;
	}

	public void setFerias(List<Ferias> ferias) {
		this.ferias = ferias;
	}
	@OneToMany(mappedBy="funcionario", cascade = CascadeType.ALL)
	@Basic(fetch=FetchType.LAZY)
	public List<Uniforme> getUniformes() {
		return uniformes;
	}

	public void setUniformes(List<Uniforme> uniformes) {
		this.uniformes = uniformes;
	}
	
	@OneToOne
	@JoinColumn(name = "cargo_id")
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	@OneToOne
	@JoinColumn(name = "condominio_id")
	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void descontosAdicionados(DescontoFuncionario desconto){
		desconto.setFuncionario(this);
		this.getDescontoFuncionario().add(0,desconto);
		
	}

	public void feriasAdicionados(Ferias ferias) {
		ferias.setFuncionario(this);
		this.getFerias().add(0, ferias);
	}
	public void uniformeAdicionados(Uniforme uniforme) {
		uniforme.setFuncionario(this);
		this.getUniformes().add(0, uniforme);
	}
}
