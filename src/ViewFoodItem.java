import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewFoodItem extends JPanel {

	static JFrame frame;
	private JButton btn1;
	JDBC db;

	public ViewFoodItem() {
		super();
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);

		// set up the buttons
		btn1 = new JButton("Back");
		int buttonHeight = btn1.getPreferredSize().height;
		int buttonWidth = btn1.getPreferredSize().width;
		btn1.setBounds(0, 50, buttonWidth, buttonHeight);

		this.add(btn1);

		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);

		this.setVisible(true);
	}

	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn1) {
				// db.main(null);
				HioGui hg = new HioGui();
				hg.main(null);

				CloseFrame();
			}
			repaint();
		}

	}

	private void CloseFrame() {
		frame.setVisible(false);
	}

	public static void main(String[] args) {

		frame = new JFrame("Restaurant");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ViewFoodItem());
		frame.setBounds(300, 400, 500, 500);
		frame.setBackground(new Color(100, 0, 20));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}