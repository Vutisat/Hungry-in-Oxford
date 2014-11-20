import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {
	private Connection connection;
	private Statement statement;
	
	//constructor establishes a connection with the db
	public JDBC() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:Project.db");
			this.statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//returns a list of all restaurants
	public ResultSet getAllRestaurants()	{
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT Name FROM Restaurant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getRestaurantFood(String rname)	{
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT Name FROM FoodItems WHERE RName = " + rname );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void closeDb()	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {

		// load the sqlite-JDBC driver using the current class loader
		
		
		//Connection connection = null;
		/*try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:Project.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// create a scanner for user input

			Scanner keyboard = new Scanner(System.in);

			ResultSet rs = statement
					.executeQuery("SELECT Name FROM Restaurant");
			while (rs.next()) {
				// read the result set
				System.out.println("Company Name = " + rs.getString("cname"));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}*/
	}
}
