package com.excellence.controlefalta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.excellence.controlefalta.model.Cargo;
import com.excellence.controlefalta.repository.Cargos;
import com.excellence.controlefalta.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Cargo.class)
public class CargoConverter implements Converter {

	//@Inject
	private Cargos cargos;
	
	public CargoConverter() {
		this.cargos = (Cargos) CDIServiceLocator.getBean(Cargos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cargo retorno = null;

		if (value != null) {
			retorno = this.cargos.porId(new Long(value));
		}

		return retorno;
	}
	//ALTEREI O CONVERSOR POR ISSO O PROBLEMA NO PEDIDO, O CORRETO É
		//if (value != null) {
	//     return ((Cargo) value).getId().toString();
     //     }
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cargo cargo = (Cargo) value;
			return cargo.getId() == null ? null : cargo.getId().toString();
		}
		return "";
	}

}