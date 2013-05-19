package org.tbhizzle.thiever.util;


import org.powerbot.game.api.wrappers.Tile;
import org.tbhizzle.thiever.util.enums.Food;
import org.tbhizzle.thiever.util.enums.Target;

public class Vars {

	private static Tile currentTile;
	private static int lowestHP = 4000;

	private final static long startTime = System.currentTimeMillis();
	private static int startXP;	
	
	private static Target selectedTarget = Target.FALLY_GUARDS;
	private static Food selectedFood = Food.TUNA;
	
	private static boolean inBankArea, inTargetArea, inventoryHasFood;
	
	public static void setCurrentTile(Tile location) {
		currentTile = location;
	}
	public static Tile getCurrentTile() {
		return currentTile;
	}
	
	
	public static void setLowestHP(int hp){
		lowestHP = hp;
	}
	public static int getLowestHP(){
		return lowestHP;
	}
	
	
	public static void setStartXP(int xp) {
		startXP = xp;
	}
	public static int getStartXP() {
		return startXP;
	}

	
	public static long getStartTime(){
		return startTime;
	}
	
	
	public static void setTarget(Target t){
		selectedTarget = t;
	}
	public static Target getTarget() {
		return selectedTarget;
	}

	
	public static void setFood(Food f){
		selectedFood = f;
	}
	public static Food getFood(){
		return selectedFood;
	}

	public static void setPlayerLocation(){
		if(getTarget().getBankArea().contains(getCurrentTile())){
			inBankArea = true ; inTargetArea = false;
		}else if(getTarget().getTargetArea().contains(getCurrentTile())){
			inBankArea = false ; inTargetArea = true;
		}else{
			inBankArea = false ; inTargetArea = true;
		}
	}
	
	public static boolean isAtBank(){
		return inBankArea;
	}
	public static boolean isAtTarget(){
		return inTargetArea;
	}
	
	
	public static void setInventroyHasFood(boolean b){
		inventoryHasFood = b;
	}
	public static boolean inventoryHasFood(){
		return inventoryHasFood;
	}
}
