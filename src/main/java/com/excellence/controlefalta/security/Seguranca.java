package com.excellence.controlefalta.security;


import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;
	

	public String getNomeUsuario() {
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	public String getSaudacao(){
		String diaTardeNoite;
		Calendar c1 = Calendar.getInstance();
		int hora = c1.get(Calendar.HOUR_OF_DAY);
		if(hora <= 12){
			diaTardeNoite = "Bom dia!";
		}else if(hora > 12 && hora < 18){
			diaTardeNoite = "Boa Tarde!";
		}else{
			diaTardeNoite ="Boa Noite!";
		}
		return diaTardeNoite;
	}
	
	public boolean isExcluirAusencia(){
		return externalContext.isUserInRole("USUARIOS") ;
	}
	
}