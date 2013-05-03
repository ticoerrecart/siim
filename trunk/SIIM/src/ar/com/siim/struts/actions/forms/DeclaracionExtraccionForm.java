package ar.com.siim.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;

public class DeclaracionExtraccionForm extends ActionForm {

	private DeclaracionExtraccionDTO declaracion;
	
	private List<BoletaDepositoDTO> boletasDeposito;
	
	public DeclaracionExtraccionForm(){
		
		declaracion = new DeclaracionExtraccionDTO();
		
		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
							FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));		
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));		
	}

	public DeclaracionExtraccionDTO getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(DeclaracionExtraccionDTO declaracion) {
		this.declaracion = declaracion;
	}

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}
	
	
}
