package com.excellence.controlefalta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.excellence.controlefalta.model.Condominio;
import com.excellence.controlefalta.repository.Condominios;
import com.excellence.controlefalta.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Condominio.class)
public class CondominioConverter implements Converter {

	//@Inject
	private Condominios condominios;
	
	public CondominioConverter() {
		this.condominios = (Condominios) CDIServiceLocator.getBean(Condominios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Condominio retorno = null;

		if (value != null) {
			retorno = this.condominios.porId(new Long(value));
		}

		return retorno;
	}
	//ALTEREI O CONVERSOR POR ISSO O PROBLEMA NO PEDIDO, O CORRETO É
		//if (value != null) {
	//     return ((Condominio) value).getId().toString();
     //     }
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Condominio condominio = (Condominio) value;
			return condominio.getId() == null ? null : condominio.getId().toString();
		}
		return "";
	}

}