package ar.com.siim.fachada;

import ar.com.siim.dao.CanonMineroDAO;

public class CanonMineroFachada implements ICanonMineroFachada {

	private CanonMineroDAO canonMineroDAO;
	
	public CanonMineroFachada(){}
	
	public CanonMineroFachada(CanonMineroDAO canonDAO){
		
		this.canonMineroDAO = canonDAO;
	}
	
	public int recuperarCanonMineroXPertenencia(){
		
		return canonMineroDAO.recuperarCanonMineroXPertenencia();
	}
	
	public void modificacionValorCanonMinero(double valor){
	
		canonMineroDAO.modificacionValorCanonMinero(valor);
	}
}
