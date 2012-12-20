package ar.com.siim.utils;

import java.util.Comparator;

import ar.com.siim.negocio.ItemMenu;

public class ComparadorItemsMenu implements Comparator<ItemMenu> {

	public int compare(ItemMenu o1, ItemMenu o2) {

		return o1.getId().compareTo(o2.getId());
	}

}
