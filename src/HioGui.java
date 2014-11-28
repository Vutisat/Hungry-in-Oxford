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
		String text = "New to Miami?  In Oxford for the weekend?  Just looking for some good food? \n " +
				"Hungry in Oxford is your answer! Hungry in Oxford allows you to search the top \n " +
				"restaurants in Oxford to find exactly what you are looking for! Search by restaurant type \n" +
				"or view all restaurants.  Look up menu items, hours, locations, and more.  Everything \n" +
				"you could possibly need, right here at Hungry in Oxford!";
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
		descrip.setBounds(50, 200, 300, 300);
		descrip.setBackground(new Color(255, 255, 255, 0));
		descrip.setEditable(false);
		this.add(descrip);
		
		//Frame to show
		frame = new JFrame("Hungry In Oxford");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 400, 400);
		frame.setLocationRelativeTo(null);
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
