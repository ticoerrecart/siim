package ar.com.siim.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import ar.com.siim.negocio.ItemMenu;

public class MenuJSCook {

	public static String getJs(List<ItemMenu> menu)
	// recibe la collection del menú ordenada según la propiedad rol (que se usa como clave de menú)
	// y devuelve la estructura del menú para su uso en JSCookMenu
	{
		return "[" + agregarOpciones(menu, true) + " ];";
	}

	private static String agregarOpcion(ItemMenu item) {
		String retorno = "";
		String url = "";
		if (item.getUrl() != null && item.getUrl().trim().length() > 0) {
			url = "javascript:irA('" + item.getUrl() + "');";
		}

		if (item.getHijos().size() > 0) {
			retorno = "\r\n[ " + determinarIcono(item) + " ,'" + item.getItem() + "'" + //etiqueta
					" ,\"" + url + "\"" + //" ,'"+url+"'" +//url  
					" ,null,'" + item.getItem() + "', \r\n" + //target frame y descripcion 
					agregarOpciones(item.getHijos(), false) + "]";
		} else { // es una opción 
			retorno = "\r\n[ " + determinarIcono(item) + " ,'" + item.getItem() + "'" + //etiqueta
					" ,\"" + url + "\"" + //" ,'"+url+ "'"+//url  
					" ,null,'" + item.getItem() + "']"; //target frame y descripcion
		}
		/*
				if (item.getHijos().size() > 0) {
					retorno = "[ " + determinarIcono(item) + " ,'" + item.getItem() + "'" + //etiqueta
							" ,\"" + url + "\"" + //" ,'"+url+"'" +//url  
							" ,null,'" + item.getItem() + "', " + //target frame y descripcion 
							agregarOpciones(item.getHijos(), false) + "]";
				} else { // es una opción 
					retorno = "[ " + determinarIcono(item) + " ,'" + item.getItem() + "'" + //etiqueta
							" ,\"" + url + "\"" + //" ,'"+url+ "'"+//url  
							" ,null,'" + item.getItem() + "']"; //target frame y descripcion
				}*/
		return retorno;
	}

	private static String agregarOpciones(List<ItemMenu> menu, boolean soloRaices) {
		// este método se llama desde el método de servicio principal y recursivamente desde agregarOpcion
		List<String> opciones = new ArrayList<String>();
		for (ItemMenu item : menu) {
			if (!soloRaices || item.esRaiz()) {
				opciones.add(agregarOpcion(item));
			}
		}
		return StringUtils.collectionToDelimitedString(opciones, ",");
	}

	/*
	 * SIC-AJUSTEMENUICON ademaestri 08/09/2008
	 * */
	private static String determinarIcono(ItemMenu item) {
		/*if (item.getPathIcon() == null) {
			return "null";
		}
		return "'<img src=\"" + item.getPathIcon() + "\"/>'";*/

		return null;
	}

}
