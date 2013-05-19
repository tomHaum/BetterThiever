package org.tbhizzle.thiever.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tbhizzle.thiever.BetterThiever;
import org.tbhizzle.thiever.util.enums.*;



public class GUI extends JFrame{

	private static final long serialVersionUID = 7669235481844168407L;
	
	private final JComboBox<String> locations , foods;
	private final JButton startButton;
	
	public GUI(){

		setTitle("Tbhizzle's Better Thiever");
		
		final JPanel bottom = new JPanel();
		final JPanel left = new JPanel();
		final JPanel right = new JPanel();
		
		bottom.setLayout( new BorderLayout());
		left.setLayout( new BorderLayout());
		right.setLayout( new BorderLayout());
		getContentPane().setLayout(new BorderLayout());
		
		
		locations = new JComboBox<String>();
		for(Target t : Target.values()){
			locations.addItem(t.getDisplayName());
		}
		
		foods = new JComboBox<String>();
		for(Food f : Food.values()){
			foods.addItem(f.getDisplayName());
		}
		locations.setSelectedIndex(1);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(Target t : Target.values()){//gets selected target
					if(t.getDisplayName().equals(locations.getSelectedItem().toString())){
						Vars.setTarget(t);
						System.out.println(t.getDisplayName() + " was selected");
					}
				}
				for(Food f : Food.values()){//gets selected food
					if(f.getDisplayName().equals(foods.getSelectedItem().toString())){
						Vars.setFood(f);
						System.out.println(f.getDisplayName() + " was selected");
					}
				}
				
				BetterThiever.startLoop();
				setVisible(false);
				dispose();
			}
		});
	
		
		left.add(locations , BorderLayout.SOUTH);
		left.add(new JLabel("Select Location") , BorderLayout.NORTH);
		
		right.add(foods , BorderLayout.LINE_END);
		right.add(new JLabel("Select Food") , BorderLayout.NORTH);
		
		bottom.add(startButton, BorderLayout.SOUTH);
		
		add(bottom , BorderLayout.SOUTH);
		add(left , BorderLayout.WEST);
		add(right , BorderLayout.EAST);
				
		setSize(200,200);
		pack();
		
	}
}
