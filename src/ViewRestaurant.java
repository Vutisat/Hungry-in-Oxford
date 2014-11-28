import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ViewRestaurant extends JPanel {
	public String name;
	static JFrame frame;
	private JButton btn1;
	JDBC db;
	
	//title.setBounds(x, y, width, height);

	public ViewRestaurant(String str) {
		super();
		this.setLayout(null);
		this.name = str;
		
		//Title
		JLabel title = new JLabel(str, JLabel.CENTER);
		title.setBounds(200, 0, 700, 50);
		title.setFont(title.getFont().deriveFont(32f));
		this.add(title);
		
		//Back button
		btn1 = new JButton("Back");
		btn1.setBounds(600, 325, 75, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		//Create list of food items
		ResultSet rs = null;
		try	{
			db = new JDBC();
			rs = db.getRestaurantFood(str);
			DefaultListModel listModel = new DefaultListModel();
			while(rs.next())	{
				listModel.addElement(rs.getString("name"));
			}
			JList list = new JList(listModel);
			list.setBackground(new Color(255, 255, 255, 0));
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			ListResponder lr = new ListResponder();
			list.addMouseListener(lr);
			JScrollPane scroll = new JScrollPane(list);
			scroll.setBounds(0, 0, 300, 300);
			this.add(scroll);
		}catch(Exception e){System.out.println("SQL Statement Failed.. probably");};
		
		//Availability of restaurants
		rs = db.getAvailability(name);
		StringBuilder sb = new StringBuilder();
		try{
			while(rs.next())	{
				sb.append(rs.getString("DayOpen") + " ");
				sb.append(rs.getString("TimeOpen") + " - ");
				sb.append(rs.getString("TimeClose"));
				sb.append("\n");
				//McDonalds, SoHi, papa johns, fiest charra, sushi nara, krishna, steinkeller all have no available times
			}
		}catch(Exception e){System.out.println("BlahLBalgh");};
		
		db.closeDb();	
		JTextPane availability = new JTextPane();	 
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		availability.setParagraphAttributes(attribs, true);
		availability.setText(sb.toString());     
		availability.setBounds(50, 200, 300, 300);
		availability.setBackground(new Color(255, 255, 255, 0));
		availability.setEditable(false);
		this.add(availability);
			
		System.out.println(sb.toString());
		
		
		//Frame to show
		frame = new JFrame("Restaurant");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void getNutInfo()	{
		
	}

	private void CloseFrame() {
		frame.setVisible(false);
	}

	public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn1) {
				ViewAllRestuarants vr = new ViewAllRestuarants();
				CloseFrame();
			}
			repaint();
		}
	}
	
	public class ListResponder implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked me yo");
			revalidate();
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
