import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class HioGui extends JPanel {
	static JFrame frame;
	private JButton btn1;
	JDBC db;

	//title.setBounds(x, y, width, height);
	
	public HioGui() {
		super();
		this.setLayout(null);

		//Title
		JLabel title = new JLabel("Hungry in Oxford", JLabel.CENTER);
		title.setBounds(0, 0, 400, 50);
		title.setFont(title.getFont().deriveFont(32f));
		this.add(title);
		
		//View Restaurants
		btn1 = new JButton("View All Restaurants");
		btn1.setBounds(75, 100, 250, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		//App Description
		String text = "NORA NORA NORA NORA NORA NORA NORA NORA NORA NORA NORA NORA NORA NORA PUT YOUR DESCRIPTION Hqwer qwer qwer qwer qwer qwer qwer qwer qwer qwer aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		JTextPane descrip = new JTextPane();
		 try {
	            SimpleAttributeSet attribs = new SimpleAttributeSet();
	            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
	            descrip.setParagraphAttributes(attribs, true);
	            descrip.setText(text);
	        }
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }
		descrip.setBounds(0, 200, 400, 300);
		descrip.setBackground(new Color(255, 255, 255, 0));
		descrip.setEditable(false);
		this.add(descrip);
		

		//Frame to show
		frame = new JFrame("Hungry In Oxford");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 400, 400);
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setVisible(true);
		frame.setResizable(false);
	}


	private void CloseFrame() {
		frame.dispose();
	}
	

	public static void main(String[] args) throws Exception, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
        //UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		HioGui start = new HioGui();
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
			
			repaint();
		}

	}

}