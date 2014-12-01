import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	JButton btn1;
	JTextArea review;
	JComboBox ratings;
	
	public AddReview(String name)	{
		super();
		this.setLayout(null);
		
		// Combo box for ratings
		String[] ratingNums = {"1", "2", "3", "4", "5"};
		ratings = new JComboBox(ratingNums);
		ratings.setBounds(20, 20, 150, 30);
		this.add(ratings);
		
		//Submit Button
		btn1  = new JButton("Submit");
		btn1.setBounds(20, 75, 150, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		//Box for review
		review = new JTextArea();
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
	
	private void CloseFrame() {
		frame.dispose();
		frame.setVisible(false);
	}
	
	public class ButtonResponder implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn1)	{
				String descrip = review.getText();
				int num = ratings.getSelectedIndex() + 1;
				System.out.println(descrip);
				System.out.println(num);
			}
			repaint();
		}
	}
}
