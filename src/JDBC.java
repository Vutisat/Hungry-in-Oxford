import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {

	public static void main(String[] args) throws ClassNotFoundException {

		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:firstexam.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// create a scanner for user input

			Scanner keyboard = new Scanner(System.in);

			int companyId;

			System.out.println("Please enter the cid for the company: ");
			companyId = keyboard.nextInt();

			ResultSet rs = statement
					.executeQuery("SELECT cname FROM Company WHERE cid ="
							+ companyId + ";");
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
		}
	}
}
