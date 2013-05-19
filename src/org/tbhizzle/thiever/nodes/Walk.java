package org.tbhizzle.thiever.nodes;

import org.powerbot.core.script.job.state.Node;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.map.LocalPath;

import org.tbhizzle.thiever.util.Methods;
import org.tbhizzle.thiever.util.Vars;

public class Walk extends Node {
	private LocalPath pathToBank , pathToTarget;
	private boolean inBankArea ,inTargetArea, foodInInventory;
	@Override
	public boolean activate() {
		inBankArea = Vars.getTarget().getBankArea().contains(Vars.getCurrentTile());
		inTargetArea = Vars.getTarget().getTargetArea().contains(Vars.getCurrentTile());
		
		foodInInventory = Inventory.contains(Vars.getFood().getFoodID());
				
		return
		// not at the bank area and not at the target area
		(!inBankArea && !inTargetArea)

		||
		// ate the bank area and inventory has food
		(inBankArea && foodInInventory)

		||
		// at the target spot and no food and low HP
		(inTargetArea && !foodInInventory && Methods.getHP() < Vars.getLowestHP());

	}

	@Override
	public void execute() {
		System.out.println("trying to walk");
		
		// Path fromTargetToBank = new Path();
		if(pathToBank == null || pathToTarget == null){
			pathToBank = Walking.findPath(Vars.getTarget().getBankTile());
			pathToTarget = Walking.findPath(Vars.getTarget().getTargetHome());
		}
		
		
		if(!foodInInventory){
			pathToBank.getNext().clickOnMap();
			System.out.println("bank walk");
		}else{
			pathToTarget.getNext().clickOnMap();
			System.out.println("target walk");
		}
		Timer t = new Timer(5000);
		while(t.isRunning() && Vars.getCurrentTile().equals(Players.getLocal().getLocation())){
			sleep(600);//only loops again if player has moved
		}

	}
	

}
