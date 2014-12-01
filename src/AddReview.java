import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class AddReview extends JPanel  {
	static JFrame frame;
	JDBC db;
	
	public AddReview(String name)	{
		super();
		this.setLayout(null);
		
		// Combo box for ratings
		String[] ratingNums = {"1", "2", "3", "4", "5"};
		JComboBox ratings = new JComboBox(ratingNums);
		ratings.setBounds(20, 20, 150, 30);
		this.add(ratings);
		
		//Submit Button
		JButton btn1  = new JButton("Submit");
		btn1.setBounds(20, 75, 150, 30);
		this.add(btn1);
		
		//Box for review
		JTextArea review = new JTextArea();
		review.setBounds(200, 20, 450, 225);
		review.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(review);
		
		//Frame to show
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 700, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
