import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewAllRestuarants extends JPanel {

	static JFrame frame;
	private JButton btn1;
	JDBC db;

	public ViewAllRestuarants() {
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

		//add restaurant buttons
		ResultSet rs = null;
		try	{
			db = new JDBC();
			rs = db.getAllRestaurants();
			
			while(rs.next()){
				String str = rs.getString("name");
				JButton btnx = new JButton(str);
				btnx.setBounds(0, 50, buttonWidth, buttonHeight);
				btnx.addActionListener(br);
				this.add(btnx);
			}
		}catch(Exception e){System.out.println("SQL Statement Failed.. probably");};
		db.closeDb();
		
		this.setVisible(true);
		
		frame = new JFrame("View all restaurants");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(300, 400, 1000, 1000);
		frame.setBackground(new Color(100, 0, 20));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn1) {
				HioGui hg = new HioGui();
				//hg.main(null);
				CloseFrame();
			}else	{
				//System.out.println(e.getActionCommand());
				ViewRestaurant vr = new ViewRestaurant(e.getActionCommand());
				//vr.main(null);
				CloseFrame();
			}
			repaint();
		}

	}

	private void CloseFrame() {
		frame.dispose();
		frame.setVisible(false);
	}

	/*public static void main(String[] args) {

		frame = new JFrame("View all restaurants");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ViewAllRestuarants());
		frame.setBounds(300, 400, 500, 500);
		frame.setBackground(new Color(100, 0, 20));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}*/

}
