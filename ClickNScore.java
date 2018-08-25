import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickNScore extends JFrame implements ActionListener, Runnable {
	private JPanel panel;
	private final int JUMP_COUNT = 40;
	private int score, x[], y[];
	private JLabel labelScore;
	private JButton buttonExit, tempButton, buttons[];
	public ClickNScore() {
		super("Click & Score");
		
		this.setSize(400, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelScore = new JLabel("Score: 0");
		labelScore.setBounds(10, 5, 80, 30);
		panel.add(labelScore);
		
		buttonExit = new JButton("EXIT");
		buttonExit.setBounds(50, 400, 290, 45);
		buttonExit.setBackground(Color.GRAY);
		buttonExit.addActionListener(this);
		panel.add(buttonExit);
		
		tempButton = new JButton("Button X");
		tempButton.setBounds(50, 60, 120, 45);
		tempButton.setBackground(Color.GREEN);
		tempButton.addActionListener(this);
		panel.add(tempButton);
		
		buttons = new JButton[10];
		x = new int[2];
		x[0] = 50;
		x[1] = 220;
		y = new int[5];
		int ypos = 60;
		for (int i = 0; i < 10; i+=2) {
			buttons[i] = new JButton("Button "+(i+1));
			buttons[i].setBounds(x[0], ypos, 120, 45);
			buttons[i].setBackground(Color.RED);
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
			
			buttons[i+1] = new JButton("Button "+(i+2));
			buttons[i+1].setBounds(x[1], ypos, 120, 45);
			buttons[i+1].setBackground(Color.RED);
			buttons[i+1].addActionListener(this);
			panel.add(buttons[i+1]);
			
			y[i/2] = ypos;
			ypos += 70;
		}
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String res = ae.getActionCommand();
		if (res.equals(tempButton.getText())) {
			score += 10;
		}
		else if (res.equals(buttonExit.getText())) {
			JOptionPane.showMessageDialog(null, "Score: "+score);
			System.exit(0);
		}
		else {
			score -= 5;
		}
		labelScore.setText("Score: "+score);
	}
	
	public void run() {
		Random r = new Random();
		int xi = 0, yi = 0;
		for(int i = 0; i < JUMP_COUNT; i++) {
			try {
				xi=r.nextInt(2);
				yi=r.nextInt(5);
				System.out.println("X: "+xi+" Y: "+yi);
				tempButton.setBounds(x[xi],y[yi],120,45);
				tempButton.setText("Button "+(2*yi+xi+1));
				Thread.sleep(1000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		confirmDialog();
	}
	
	public void confirmDialog() {
		int input = JOptionPane.showConfirmDialog(null, "Want to try again?", "Score: "+score, JOptionPane.YES_NO_OPTION);
		if (input == 0) {
			this.score = 0;
			ClickNScore cns = new ClickNScore();
			this.setVisible(false);
			cns.setVisible(true);
			cns.run();
		}
		else
			System.exit(0);
	}
}