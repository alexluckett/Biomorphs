package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Splash screen with user selection.
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public class GuiStartup extends AbstractGUI {

	public GuiStartup() {
		super("Biomorph Mutation: Select User type", 500, 300);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);

		JLabel description1 = new JLabel("Welcome to the Biomorph Mutation Application");
		JLabel description2 = new JLabel("Produce evolutionary art based on Richard Dawkin's Biomorphs in a creative way");
		JLabel usertype = new JLabel("First, Choose the type of user you are: ");
		
		JButton beginner = new JButton("Beginner");
		beginner.setToolTipText("Using the application for the first time? Choose Beginner");
		
		JButton advanced = new JButton("Advanced");
		advanced.setToolTipText("Want to experience something extraordinary? Choose advanced");
		
		JButton quit= new JButton("Quit Application");
		quit.setToolTipText("Exit the Application. Unsaved Settings will be lost");
		
		JPanel panel = new JPanel(new GridBagLayout());

	
		/** Add components to panel */
		windowFrame.getContentPane().add(panel, BorderLayout.NORTH);
		windowFrame.add(panel);	
		panel.add(description1);
		panel.add(description2);
		panel.add(usertype);
		panel.add(beginner);
		panel.add(advanced);
		panel.add(quit);

		/** Set GridBrag constraints */
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(description1, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(description2, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usertype, c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(beginner, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(advanced, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(quit, c);			
		
		/** Beginner selection */
		beginner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				//new BasicGUI().displayGui();
			}

		});

		/** Advanced Selection */
		advanced.addActionListener(new ActionListener() { 	

			@Override
			public void actionPerformed(ActionEvent e) {
				destroyGui();
				new AdvancedGUI().displayGui();
				//new EvolutionaryGUI().displayGui();
			}

		});

		
		/** Exit the application*/
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			exitApplication();
					}
				});
		
		
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
	}

	@Override
	public void addMutateListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addExitListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSaveListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLoadListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHelpListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}
}