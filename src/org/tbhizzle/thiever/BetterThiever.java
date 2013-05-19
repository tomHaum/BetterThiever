package org.tbhizzle.thiever;

import org.tbhizzle.thiever.nodes.*;
import org.tbhizzle.thiever.util.GUI;
import org.tbhizzle.thiever.util.Methods;
import org.tbhizzle.thiever.util.Paint;
import org.tbhizzle.thiever.util.Vars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;




@Manifest(authors = { "tbhizzle" }, description = "an improved thieving script", name = "Better Thiever")
public class BetterThiever extends ActiveScript implements PaintListener, MouseListener {
	private static Tree jobContainer = null;
	private static List<Node> jobCollection = Collections.synchronizedList(new ArrayList<Node>());
	
	private Color paintColor = Color.WHITE;
	private GUI gui;
	
	private static boolean allowedToRun;

	public void onStart() {
		
		provide(new Steal(), new Walk(), new Banking(),
				new EatFood());
		Vars.setStartXP(Skills.getExperience(Skills.THIEVING));

		gui = new GUI();
		gui.setVisible(true);
		
	}

	public int loop() {
		if (allowedToRun) {
			if (Vars.getStartXP() == 0)Vars.setStartXP(Skills.getExperience(Skills.THIEVING));

			if (Game.getClientState() == Game.INDEX_MAP_LOADED) {
				Vars.setCurrentTile(Players.getLocal().getLocation());
				Vars.setPlayerLocation();
				Vars.setInventroyHasFood(Methods.checkForFood());
				if (jobContainer != null) {
					final Node job = jobContainer.state();
					if (job != null) {
						jobContainer.set(job);
						getContainer().submit(job);
						job.join();
					}
				}
			}
		}
		return 10;
	}

	public void onRepaint(Graphics g) {
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		g.setColor(paintColor);
		g.drawLine(mouseX - 5, mouseY - 5, mouseX + 5, mouseY + 5);
		g.drawLine(mouseX - 5, mouseY + 5, mouseX + 5, mouseY - 5);
		Paint.onRepaint(g);
	}

	private static synchronized final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobCollection.contains(job)) {
				jobCollection.add(job);
			}
		}
		jobContainer = new Tree(jobCollection.toArray(new Node[jobCollection
				.size()]));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		paintColor = Color.YELLOW;
		sleep(500);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		paintColor = Color.WHITE;
	}

	public static void startLoop() {
		allowedToRun = true;
	}
	
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}


}
