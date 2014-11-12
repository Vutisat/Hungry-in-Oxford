import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HioGui extends JPanel {

	private JButton btn1, btn2, btn3;
	JDBC db = new JDBC();

	public HioGui() {
		super();
		this.setLayout(null);

		// set up the buttons
		btn1 = new JButton("View all resturants");
		int buttonHeight = btn1.getPreferredSize().height;
		int buttonWidth = btn1.getPreferredSize().width;
		btn1.setBounds(0, 50, buttonWidth, buttonHeight);
		btn2 = new JButton("View Restuarants by food category");
		btn2.setBounds(170, 50, buttonWidth, buttonHeight);
		btn3 = new JButton("Back");
		btn3.setBounds(340, 50, buttonWidth, buttonHeight);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);

		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);

		this.setVisible(true);
	}

	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn1) {
				try {
					db.main(null);
					System.out.println("Press 1");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			repaint();
		}

	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Hungry In Oxford Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HioGui());
		frame.setBounds(300, 400, 500, 500);
		frame.setBackground(new Color(100, 0, 20));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
