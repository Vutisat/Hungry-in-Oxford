import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ViewRestaurant extends JPanel {
	public String name;
	static JFrame frame;
	private JButton btn1, btn2, btn3;
	private JList list;
	private JTable foodList;
	JDBC db;

	// title.setBounds(x, y, width, height);

	public ViewRestaurant(String str) {
		super();
		this.setLayout(null);
		this.name = str;

		// Title
		JLabel title = new JLabel(str, JLabel.CENTER);
		title.setBounds(200, 0, 700, 50);
		title.setFont(title.getFont().deriveFont(32f));
		this.add(title);

		// Back button
		btn1 = new JButton("Back");
		btn1.setBounds(5, 5, 75, 30);
		this.add(btn1);
		ButtonResponder br = new ButtonResponder();
		btn1.addActionListener(br);
		
		//Add space for average rating
		db = new JDBC();
		ResultSet rs = db.getAverageRating(str);
		JLabel avgScore = null;
		try {
			avgScore = new JLabel("("+ Integer.toString(rs.getInt(1)) + ")");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		avgScore.setFont(new Font("Arial", Font.PLAIN, 30));
		avgScore.setBounds(625, 0, 100, 50);
		this.add(avgScore);

		// Create list of food items
		rs = db.getRestaurantFoodAndDrink(str);;
		try {

			DefaultListModel listModel = new DefaultListModel();
			while (rs.next()) {
				listModel.addElement(rs.getString("name"));
			}
			list = new JList(listModel);
			list.setBackground(new Color(255, 255, 255, 0));
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListListener ll = new ListListener();
			list.addMouseListener(ll);
			JScrollPane scroll = new JScrollPane(list);
			scroll.setBounds(25, 225, 300, 200);
			this.add(scroll);
		} catch (Exception e) {
			System.out.println("SQL Statement Failed.. probably");
		};

		// Availability of restaurants
		rs = db.getAvailability(name);
		StringBuilder sb = new StringBuilder();
		try {
			while (rs.next()) {
				sb.append(rs.getString("DayOpen") + " ");
				sb.append(rs.getString("TimeOpen") + " - ");
				sb.append(rs.getString("TimeClose"));
				sb.append("\n");
				// McDonalds, SoHi, papa johns, fiest charra, sushi nara,
				// krishna, steinkeller all have no available times
			}
		} catch (Exception e) {
			System.out.println("BlahLBalgh");
		}
		;
		db.closeDb();
		JTextPane availability = new JTextPane();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		availability.setParagraphAttributes(attribs, true);
		availability.setText(sb.toString());
		availability.setBounds(400, 50, 300, 150);
		availability.setBackground(new Color(255, 255, 255, 0));
		availability.setEditable(false);
		this.add(availability);

		// Add food to table button
		JButton add = new JButton("Add food to table");
		add.setBounds(350, 275, 150, 25);
		AddFood af = new AddFood();
		add.addActionListener(af);
		this.add(add);

		// Delete food from table button
		JButton remove = new JButton("Remove food from table");
		remove.setBounds(350, 400, 150, 25);
		remove.setFont(new Font("Arial", Font.PLAIN, 11));
		RemoveFood rf = new RemoveFood();
		remove.addMouseListener(rf);
		this.add(remove);

		// View all food
		JButton allFood = new JButton("View all food");
		allFood.setBounds(350, 225, 150, 25);
		AllFood allF = new AllFood();
		allFood.addActionListener(allF);
		this.add(allFood);

		// Table of food items with nutritional info
		String[] columnNames = { "Food Item", "Calories", "Fat", "Sugar",
				"Sodium", "Carbs", "Price" };
		foodList = new JTable();
		foodList.setModel(new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		foodList.getColumnModel().getColumn(0).setPreferredWidth(200);
		JScrollPane scrollTable = new JScrollPane(foodList);
		scrollTable.setBounds(25, 450, 750, 250);
		this.add(scrollTable);

		// Frame to show
		frame = new JFrame("Restaurant");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);

		// Add Review Button
		btn2 = new JButton("Review this restaurant");
		btn2.setFont(new Font("Arial", Font.PLAIN, 11));
		btn2.setBounds(625, 225, 150, 25);
		this.add(btn2);
		ButtonResponder br2 = new ButtonResponder();
		btn2.addActionListener(br2);

		// Add View Review Button
		btn3 = new JButton("View Reviews");
		btn3.setBounds(625, 275, 150, 25);
		this.add(btn3);
		ButtonResponder br3 = new ButtonResponder();
		btn3.addActionListener(br3);

		// Image to show
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(getClass().getResource(
					"/images/" + name + ".jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel headerImage = new JLabel(new ImageIcon(myPicture));
		headerImage.setBounds(0, 0, 200, 300);
		this.add(headerImage);
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

			if (e.getSource() == btn2) {
				AddReview ar = new AddReview(name);
			}
			if (e.getSource() == btn3) {
				ViewReviews vr = new ViewReviews(name);
			}
			repaint();
		}
	}

	public class AddFood implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel) foodList.getModel();
			String food = (String) list.getSelectedValue();
			StringBuilder sb = new StringBuilder(food);
			if (food.contains("\"")) {
				int index = food.indexOf("\"");
				sb.insert(index, "\"");
			}
			ResultSet rs = null;
			db = new JDBC();
			try {
				rs = db.getFoodItemInfo(name, sb.toString());
				String[] row = { rs.getString("name"),
						rs.getString("Calories"), rs.getString("Fat"),
						rs.getString("Sugar"), rs.getString("Sodium"),
						rs.getString("Carbs"), rs.getString("Price") };
				model.addRow(row);
			} catch (Exception z) {
				z.printStackTrace();
			}
			;
		}
	}

	public class AllFood implements ActionListener {
		// what are we going to do about drinks that don't have any nutritional
		// info? not include them in the list? should price go in this table
		// too?
		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel) foodList.getModel();
			ResultSet rs = null;
			db = new JDBC();
			try {
				rs = db.getFoodItemInfo(name);
				while (rs.next()) {
					String[] row = { rs.getString("name"),
							rs.getString("Calories"), rs.getString("Fat"),
							rs.getString("Sugar"), rs.getString("Sodium"),
							rs.getString("Carbs") };
					model.addRow(row);
				}
			} catch (Exception z) {
				z.printStackTrace();
			}
			;
		}
	}

	public class RemoveFood implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model = (DefaultTableModel) foodList.getModel();
			try {
				model.removeRow(foodList.getSelectedRow());
			} catch (Exception z) {
			}
			;
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

	public class ListListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
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
