package fr.ring.menu;

import fr.ring.personnage.*;

public class TestMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu m = new Menu();
		Personnage p = m.menuCreationPersonnage();
		String s = p.toString();
		System.out.println(s);
	}

}
