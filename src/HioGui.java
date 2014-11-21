import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HioGui extends JPanel {
	static JFrame frame;
	private JButton btn1, btn2;
	JDBC db;

	public HioGui() {
		super();
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);

		// set up the buttons
		btn1 = new JButton("View All Resturants");
		int buttonHeight = btn1.getPreferredSize().height;
		int buttonWidth = btn1.getPreferredSize().width;
		btn1.setBounds(0, 50, buttonWidth, buttonHeight);
		btn2 = new JButton("View Restuarants by food category");
		btn2.setSize(50, 20);
		btn2.setBounds(170, 50, buttonWidth, buttonHeight);
		this.add(btn1);
		this.add(btn2);

		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);

		ButtonResponder br2 = new ButtonResponder();
		btn2.addActionListener(br2);
		
		this.setVisible(true); //I don't think these are necessary since we're having each class have its own jframe
		
		frame = new JFrame("Hungry In Oxford");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(300, 400, 500, 500);
		frame.setBackground(new Color(100, 0, 20));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn1) {
				// db.main(null);
				ViewAllRestuarants vr = new ViewAllRestuarants();
				//vr.main(null);
				vr.setVisible(true);

				CloseFrame();
			}
			
			if (e.getSource() == btn2) {
				ViewByCategory br = new ViewByCategory();
				//br.main(null);
				br.setVisible(true);

				CloseFrame();
			}
			repaint();
		}

	}

	private void CloseFrame() {
		frame.dispose();
	}

	public static void main(String[] args) throws Exception, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		HioGui start = new HioGui();
	}

}
