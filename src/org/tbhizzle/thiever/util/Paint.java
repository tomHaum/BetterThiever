package org.tbhizzle.thiever.util;

import java.awt.Color;
import java.awt.Graphics;

import org.powerbot.game.api.methods.tab.Skills;


public class Paint {
	private static int successCount = 0 , failCount = 0;
	
	public static void onRepaint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 400, 100);
		
		g.setColor(Color.BLACK);
		g.drawString("Number of Pickpockets winss: " + successCount, 1 , 10);
		g.drawString("Number of Pickpockets fails: " + failCount, 1 , 25);
		g.drawString("Experience Gained: " + (Skills.getExperience(Skills.THIEVING) - Vars.getStartXP()) , 1 , 40);
		g.drawString(printTime(System.currentTimeMillis() - Vars.getStartTime()),
				1, 60);
		
		g.drawString("XP per Hour: " + printXPRate() + "k",200 , 10);
	}

	private static String printTime(long timeInMills){
		String time = String.format("%02d:%02d:%02d",
				(timeInMills / 3600000) % 24,
				(timeInMills / 60000) %60,
				(timeInMills / 1000) % 60);
		time = (timeInMills >=86400000) ? ((timeInMills / 86400000) + ":" + time ): time;
		return time;
	}
	private static double printXPRate(){
		long deltaTime = System.currentTimeMillis() - Vars.getStartTime();
		deltaTime = deltaTime / 1000;
		
		double rate =  
				((3600 *(Skills.getExperience(Skills.THIEVING) - Vars.getStartXP())) 
				/
				deltaTime);

		rate = Math.round(rate);
		rate = rate / 1000;
		return rate;
	}
	
	public static void successfullSteal(){
		successCount++;
	}
	public static void failedSteal(){
		failCount++;
	}
}
