package org.tbhizzle.thiever.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
//import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;

import org.tbhizzle.thiever.util.Methods;
import org.tbhizzle.thiever.util.Paint;
import org.tbhizzle.thiever.util.Vars;

public class Steal extends Node {
	public static int startXP = -1 , startHP = -1;
	
	public boolean activate() {
		
		//player is at the target Area and HP is good
		return (Vars.isAtTarget() && Methods.getHP() > Vars.getLowestHP());
	
	}

	Filter<NPC> fil = new Filter<NPC>(){
		public boolean accept(NPC arg0) {
			
			return false;
		}
		
	};
	public void execute() {
		if(Methods.getHP() <= Vars.getLowestHP()){
			System.out.println("hp too low eating");
			return;
		}
		if(Players.getLocal().isInCombat()){
			System.out.println("you are stunned");
			sleep(600);
			return;
		}
		
		
		startXP = Skills.getExperience(Skills.THIEVING);
		startHP = Methods.getHP();
		
		NPC target = NPCs.getNearest(new Filter<NPC>(){
			public boolean accept(NPC npc) {
				boolean checkID = false;
				for(int id : Vars.getTarget().getTargetIDs()){
					if(npc.getId() == id && checkID == false){
						checkID = true;
					}
				}
				return (npc.getLocation().canReach() && checkID);
			}
		});
		
		
		
		if(target == null){
			Walking.walk(Vars.getTarget().getTargetHome());
			return;
		}
		
		if(!target.isOnScreen()){
			Camera.turnTo(target);
		}
		Timer t = new Timer(1000);
		
		if(target.interact("Pickpocket")){//if successful interact
			t.reset();
			while(	startXP == Skills.getExperience(17) 
					&& Methods.getHP() >= startHP 		//conditions
					&& t.isRunning())
			{
				sleep(100);
			}
			if(startXP < Skills.getExperience(17)){
				Paint.successfullSteal();
			}
			if(startHP < Methods.getHP()){
				Paint.failedSteal();
			}
			
		}else{
			Walking.walk(target);
		}
		
		
	}

}
