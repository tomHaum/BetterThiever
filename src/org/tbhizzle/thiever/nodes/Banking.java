package org.tbhizzle.thiever.nodes;

import org.powerbot.core.script.job.state.Node;

import org.powerbot.game.api.methods.interactive.NPCs;

import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.Walking;

import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

import org.tbhizzle.thiever.util.Methods;
import org.tbhizzle.thiever.util.Vars;

public class Banking extends Node {
	public static int foodAmount = 4;

	public boolean activate() {

		return Vars.isAtBank() && !Vars.inventoryHasFood();
	}

	@Override
	public void execute() {
		Timer t = new Timer(10000);
		if (!Bank.isOpen()) {
			NPC banker = NPCs.getNearest(Bank.BANK_NPC_IDS);

			if (banker == null) {
				Walking.walk(Vars.getTarget().getBankTile());
				return;
			}

			banker.interact("Bank");
			t.reset();
			while (!Bank.isOpen() && t.isRunning()) {
				sleep(200);
			}
		}

		if (Bank.getCurrentTab() != Bank.Tab.ALL)
			Bank.setCurrentTab(Bank.Tab.ALL);
		Bank.depositInventory();
		Bank.withdraw(Vars.getFood().getFoodID(), foodAmount);
		t.reset();
		while (t.isRunning() && !Vars.inventoryHasFood()) {
			Vars.setInventroyHasFood(Methods.checkForFood());
			sleep(200);// dynamic sleep
		}
		Bank.close();
		
	}

}
