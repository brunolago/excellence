package com.excellence.controlefalta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.excellence.controlefalta.model.Pagamento;
import com.excellence.controlefalta.repository.Pagamentos;
import com.excellence.controlefalta.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Pagamento.class)
public class PagamentoConverter implements Converter {

	//@Inject
	private Pagamentos pagamentos;
	
	public PagamentoConverter() {
		this.pagamentos = (Pagamentos) CDIServiceLocator.getBean(Pagamentos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pagamento retorno = null;

		if (value != null) {
			retorno = this.pagamentos.porId(new Long(value));
		}

		return retorno;
	}
	//ALTEREI O CONVERSOR POR ISSO O PROBLEMA NO PEDIDO, O CORRETO É
		//if (value != null) {
	//     return ((Pagamento) value).getId().toString();
     //     }
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pagamento pagamento = (Pagamento) value;
			return pagamento.getId() == null ? null : pagamento.getId().toString();
		}
		return "";
	}

}