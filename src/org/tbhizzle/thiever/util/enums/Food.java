package org.tbhizzle.thiever.util.enums;

public enum Food {
	TUNA(361 , "Tuna");
	
	private int foodID;
	private String displayName;
	
	Food(int foodID , String displayName){
		this.foodID = foodID;
		this.displayName = displayName;
	}

	public int getFoodID(){
		return foodID;
	}

	public String getDisplayName() {
		return displayName;
	}
}
