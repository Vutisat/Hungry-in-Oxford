import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ViewRestaurant extends JPanel {
	public String name;
	static JFrame frame;
	private JButton btn1;
	JDBC db;

	public ViewRestaurant(String str) {
		super();
		this.setLayout(null);
		this.name = str;
		
		//Back button
		btn1 = new JButton("Back");
		btn1.setBounds(600, 325, 75, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		ResultSet rs = null;
		try	{
			db = new JDBC();
			rs = db.getRestaurantFood(str);
			
			DefaultListModel listModel;
			
			listModel = new DefaultListModel();
			
			/*while(rs.next())	{
				listModel.addElement(rs.getString("name"));
			}
			
			JList list = new JList(listModel);
			JScrollPane scroll = new JScrollPane(list);
			scroll.setBounds(0, 0, 100, 100);
			this.add(scroll);*/
			
			while(rs.next()){
				String food = rs.getString("name");
				JButton btnx = new JButton(food);
				btnx.setBounds(0, 50, 100, 100);
				btnx.addActionListener(br);
				this.add(btnx);
			}
		}catch(Exception e){System.out.println("SQL Statement Failed.. probably");};
		db.closeDb();
		
		//Frame to show
		frame = new JFrame(this.name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 700, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
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
}
