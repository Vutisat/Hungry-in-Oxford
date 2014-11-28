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
			rs = this.statement.executeQuery("SELECT Name FROM FoodItems WHERE RName = " + "\"" + rname + "\"" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
		public ResultSet getFoodInfo(String fid) {
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT * FROM NutritionalValues WHERE fid = "+ "\"" + fid + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getRestaurantDrink(String did) {
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT Name FROM SpecialtyDrinks WHERE did = "+ "\"" + did + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getAvailability(String rname) {
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT DayOpen, TimeOpen, TimeClose FROM Availability WHERE rname = " + "\"" + rname + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getRestByType(String type)	{
		ResultSet rs = null;	
		try	{
			if(!type.contains(" Restaurant")){
				rs = this.statement.executeQuery("SELECT name FROM Restaurant WHERE type = " + "\"" + type + "\"");
			}
			else{
				rs = this.statement.executeQuery("SELECT name From Restaurant");
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet customQuery(String query)	{
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery(query);
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
	
}
