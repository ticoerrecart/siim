package ar.com.siim.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.CanonMineroDTO;

public class CanonMineroForm extends ActionForm {

	private CanonMineroDTO canonMinero;
	
	private List<BoletaDepositoDTO> boletasDeposito;
	
	public CanonMineroForm(){
		
		canonMinero = new CanonMineroDTO();
		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
							FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));		
	}	
	
	public CanonMineroDTO getCanonMinero() {
		return canonMinero;
	}

	public void setCanonMinero(CanonMineroDTO canonMinero) {
		this.canonMinero = canonMinero;
	}

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}
}
