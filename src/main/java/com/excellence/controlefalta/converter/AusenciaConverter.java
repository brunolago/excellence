package com.excellence.controlefalta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.excellence.controlefalta.model.Ausencia;
import com.excellence.controlefalta.repository.Ausencias;
import com.excellence.controlefalta.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Ausencia.class)
public class AusenciaConverter implements Converter {

	//@Inject
	private Ausencias ausencias;
	
	public AusenciaConverter() {
		this.ausencias = (Ausencias) CDIServiceLocator.getBean(Ausencias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ausencia retorno = null;

		if (value != null) {
			retorno = this.ausencias.porId(new Long(value));
		}

		return retorno;
	}
	//ALTEREI O CONVERSOR POR ISSO O PROBLEMA NO PEDIDO, O CORRETO É
		//if (value != null) {
	//     return ((Ausencia) value).getId().toString();
     //     }
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Ausencia ausencia = (Ausencia) value;
			return ausencia.getId() == null ? null : ausencia.getId().toString();
		}
		return "";
	}

}