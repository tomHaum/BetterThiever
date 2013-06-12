package org.tbhizzle.thiever.util;

import java.awt.Color;
import java.awt.Graphics;

import org.powerbot.game.api.methods.tab.Skills;


public class Paint {
	private static int successCount = 0 , failCount = 0;
	private static int repaintCount = 0;
	private static String xp;
	
	public static void onRepaint(final Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(6, 395, 506, 130);
		
		g.setColor(Color.BLACK);
		g.drawString("Number of Pickpockets winss: " + successCount, 8 , 410);
		g.drawString("Number of Pickpockets fails: " + failCount, 8 , 425);
		g.drawString("Experience Gained: " + (Skills.getExperience(Skills.THIEVING) - Vars.getStartXP()) , 8 , 440);
		g.drawString("Run Time: " + printTime(System.currentTimeMillis() - Vars.getStartTime()),
				8, 455);
		
		if(repaintCount == 20){
			repaintCount = 0;
			xp = printTime(System.currentTimeMillis());
		}
		g.drawString("XP per Hour: " + printXPRate() + "k", 8 , 470);
		
		repaintCount++;
	}

	private static String printTime(final long timeInMills){
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
