package org.tbhizzle.thiever.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import org.tbhizzle.thiever.util.Methods;
import org.tbhizzle.thiever.util.Vars;

public class EatFood extends Node{
	private int inventoryCount;
	private final Timer t = new Timer(5000);
	
	public boolean activate() {
		
		return (Vars.inventoryHasFood()//has food
				&& Vars.isAtTarget()//at spot
				&& Methods.getHP() < Vars.getLowestHP());//player is low hp
	}

	@Override
	public void execute() {
		System.out.println("eating");
		inventoryCount = Inventory.getCount();
		final WidgetChild foodWid = Inventory.getItem(Vars.getFood().getFoodID()).getWidgetChild();
		if(foodWid != null){
			foodWid.interact("Eat");
			t.reset();
			while(inventoryCount == Inventory.getCount() && t.isRunning()){
				sleep(100);
			}
		}
		
		Vars.setInventroyHasFood(Methods.checkForFood());
	}
	
}
