package org.tbhizzle.thiever.util;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Methods {
	
	
	public static int getHP(){
		WidgetChild hpWid = new Widget(748).getChild(8);
		return((hpWid != null)? Integer.parseInt(hpWid.getText()) : -1);
	}

	public static boolean checkForFood() {
		return Inventory.contains(Vars.getFood().getFoodID());
	}
	
}
