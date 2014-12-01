import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddReview extends JPanel  {
	static JFrame frame;
	JDBC db;
	
	public AddReview(String name)	{
		super();
		this.setLayout(null);
		
		String[] ratingNums = {"1", "2", "3", "4", "5"};
		JComboBox ratings = new JComboBox(ratingNums);
		ratings.setBounds(20, 65, 150, 30);
		this.add(ratings);
		
		//Frame to show
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 700, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
