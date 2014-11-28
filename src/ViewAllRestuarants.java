import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewAllRestuarants extends JPanel {

	static JFrame frame;
	private ArrayList<JButton> restButtons = new ArrayList<JButton>();
	private JButton btn1;
	JDBC db;
	
	//title.setBounds(x, y, width, height);

	public ViewAllRestuarants() {
		super();
		this.setLayout(null);
		
		//Title
		JLabel title = new JLabel("Restaurants in the OxBox", JLabel.CENTER);
		title.setBounds(0, 0, 700, 50);
		title.setFont(title.getFont().deriveFont(32f));
		this.add(title);
		
		//ComboBox
		String[] types = {"All Restaurants", "Fast Food", "American", "Mexican", "Asian", "Bar", "Subs", "Indian", "Wings"};
		JComboBox foodTypes = new JComboBox(types);
		foodTypes.setBounds(20, 65, 150, 30);
		((JLabel)foodTypes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		this.add(foodTypes);
		foodTypes.setSelectedIndex(0);
		ComboBoxSelector cb = new ComboBoxSelector();
		foodTypes.addActionListener(cb);
		restButtons("All Restaurants");
		
		//Back button
		btn1 = new JButton("Back");
		btn1.setBounds(600, 325, 75, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		//Frame to show
		frame = new JFrame("View all restaurants");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 700, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	//Create buttons 
	public void restButtons(String type)	{
		ResultSet rs = null;
		for(JButton but : restButtons)	{
			this.remove(but);
		}
		restButtons.clear();
		try	{
			db = new JDBC();
			rs = db.getRestByType(type);
			int x = 200;
			int y = 65;
			int count = 0;
			while(rs.next()){
				String str = rs.getString("name");
				JButton btnx = new JButton(str);
				if(str.length() > 11)	{
					btnx.setFont(new Font("Arial", Font.PLAIN, 8));
				}
				else if(str.length() > 6)	{
					btnx.setFont(new Font("Arial", Font.PLAIN, 11));
				}
				else {
					btnx.setFont(new Font("Arial", Font.PLAIN, 14));
				}
				restButtons.add(btnx);
				count++;
				if(count > 4)	{
					y+= 50;
					x = 200;
					btnx.setBounds(x, y, 100, 30);
					count = 1;
				}else	{
					btnx.setBounds(x, y, 100, 30);
				}
				x += 115;
				ButtonResponder br = new ButtonResponder();
				btnx.addActionListener(br);
				this.add(btnx);
				repaint();
			}
		}catch(Exception e){
				System.out.println("SQL Statement Failed.. probably"); 
				e.printStackTrace();
			};
			db.closeDb();
	}

	private void CloseFrame() {
		frame.dispose();
		frame.setVisible(false);
	}
	
	public class ButtonResponder implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn1) {
				HioGui hg = new HioGui();
				CloseFrame();
			}else	{
				ViewRestaurant vr = new ViewRestaurant(e.getActionCommand());
				CloseFrame();
			}
			repaint();
		}
	}
	
	public class ComboBoxSelector implements ActionListener	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String foodType = (String)cb.getSelectedItem();
			System.out.println(foodType);
			restButtons(foodType);
			
		}
	}
}
