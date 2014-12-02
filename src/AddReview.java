import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AddReview extends JPanel {
	static JFrame frame;
	JDBC db;
	JButton btn1;
	JTextArea review;
	JComboBox ratings;
	String name;

	public AddReview(String name) {
		super();
		this.name = name;
		this.setLayout(null);

		JLabel ratingLabel = new JLabel("Rating (1-5)");
		ratingLabel.setBounds(20, 20, 150, 30);
		this.add(ratingLabel);
		// Combo box for ratings
		String[] ratingNums = { "1", "2", "3", "4", "5" };
		ratings = new JComboBox(ratingNums);
		ratings.setBounds(20, 55, 150, 30);
		this.add(ratings);

		// Submit Button
		btn1 = new JButton("Submit");
		btn1.setBounds(20, 100, 150, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);

		JLabel reviewLabel = new JLabel("Enter review:");
		reviewLabel.setBounds(200, 20, 150, 30);
		this.add(reviewLabel);
		// Box for review
		review = new JTextArea();
		review.setBounds(200, 55, 450, 200);
		review.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(review);

		// Frame to show
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
			if (e.getSource() == btn1) {
				db = new JDBC();
				String descrip = review.getText();
				int num = ratings.getSelectedIndex() + 1;
				db.insertReview(num, descrip, name);
				db.closeDb();
				CloseFrame();
			}
			repaint();
		}
	}
}
