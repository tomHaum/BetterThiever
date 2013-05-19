package org.tbhizzle.thiever.util.enums;


import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public enum Target {
	FALLY_GUARDS(	new int[]{9} , "Pickpocket" , 
					new Area() , 
					new Tile(1,1,1),
					new Area() , 
					new Tile(1, 1, 1),
					"Fally Guards"),
	DRAYNOR_MG(	new int[]{2234,3299} , "PickPocket" , 
				new Area(new Tile(3092,3240,0) , new Tile(3095,3246,0)) ,
				new Tile(3092,3243,0),
				new Area(new Tile(3074,3245,0) , new Tile(3086,3256,0)) ,
				new Tile(3080,3250,0),
				"Draynor Master Gardners");
	
	private int[] targetIDs;
	private String targetAction;
	private Area bankArea;
	private Tile bankTile;
	private Area targetArea;
	private Tile targetHome;
	private String displayName;
	
	private Target(int[] targetIDs, String targetAction, Area bankArea, Tile bankTile, Area targetArea, Tile targetHome , String displayName){
		this.targetIDs = targetIDs;
		this.targetAction = targetAction;
		this.bankArea = bankArea;
		this.bankTile = bankTile;
		this.targetArea = targetArea;
		this.targetHome = targetHome;
		this.displayName = displayName;
	}
	
	public int[] getTargetIDs(){
		return targetIDs;
	}
	public String getTargetAction(){
		return targetAction;
	}
	public Area getBankArea(){
		return bankArea;
	}
	public Tile getBankTile(){
		return bankTile;
	}
	public Area getTargetArea(){
		return targetArea;
	}
	public Tile getTargetHome(){
		return targetHome;
	}

	public String getDisplayName() {
		return displayName;
	}


}
