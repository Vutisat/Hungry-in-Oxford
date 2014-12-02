import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewReviews extends JPanel {

	JFrame frame;

	public ViewReviews(String name) {
		super();
		this.setLayout(null);

		// Title
		JLabel title = new JLabel(name + " Reviews", JLabel.CENTER);
		title.setBounds(200, 0, 700, 50);
		title.setFont(title.getFont().deriveFont(32f));
		this.add(title);

		// Table of reviews
		String[] columnNames = { "Rating", "Review" };
		JTable reviewList = new JTable();
		reviewList.setModel(new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		reviewList.getColumnModel().getColumn(1).setPreferredWidth(600);
		JScrollPane scrollTable = new JScrollPane(reviewList);
		scrollTable.setBounds(25, 50, 650, 200);
		DefaultTableModel model = (DefaultTableModel) reviewList.getModel();
		ResultSet rs = null;
		JDBC db = new JDBC();
		try {
			rs = db.getReviews(name);
			while (rs.next()) {
				String[] row = { Integer.toString(rs.getInt("Rating")),
						rs.getString("Description") };
				model.addRow(row);
			}
		} catch (Exception z) {
			z.printStackTrace();
		}
		;
		this.add(scrollTable);

		// Frame to show
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 700, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
