package com.excellence.controlefalta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.excellence.controlefalta.model.Funcionario;
import com.excellence.controlefalta.repository.Funcionarios;
import com.excellence.controlefalta.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Funcionario.class)
public class FuncionarioConverter implements Converter {

	//@Inject
	private Funcionarios funcionarios;
	
	public FuncionarioConverter() {
		this.funcionarios = (Funcionarios) CDIServiceLocator.getBean(Funcionarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;

		if (value != null) {
			retorno = this.funcionarios.porId(new Long(value));
		}

		return retorno;
	}
	//ALTEREI O CONVERSOR POR ISSO O PROBLEMA NO PEDIDO, O CORRETO É
		//if (value != null) {
	//     return ((Funcionario) value).getId().toString();
     //     }
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Funcionario funcionario = (Funcionario) value;
			return funcionario.getId() == null ? null : funcionario.getId().toString();
		}
		return "";
	}

}