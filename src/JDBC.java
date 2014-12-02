import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private Connection connection;
	private Statement statement;

	// constructor establishes a connection with the db
	public JDBC() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager
					.getConnection("jdbc:sqlite:Project.db");
			this.statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// returns a list of all restaurants
	public ResultSet getAllRestaurants() {
		ResultSet rs = null;
		try {
			rs = this.statement.executeQuery("SELECT Name FROM Restaurant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getRestaurantFood(String rname) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT Name FROM FoodItems WHERE RName = "
							+ "\"" + rname + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getRestaurantFoodAndDrink(String rest) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT Name FROM FoodItems WHERE RName = "
							+ "\""
							+ rest
							+ "\" UNION SELECT Name FROM SpecialtyDrinks WHERE RName = "
							+ "\"" + rest + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getAvailability(String rname) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT DayOpen, TimeOpen, TimeClose FROM Availability WHERE rname = "
							+ "\"" + rname + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getRestByType(String type) {
		ResultSet rs = null;
		try {
			if (!type.contains(" Restaurant")) {
				rs = this.statement
						.executeQuery("SELECT name FROM Restaurant WHERE type = "
								+ "\"" + type + "\"");
			} else {
				rs = this.statement.executeQuery("SELECT name From Restaurant");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getFoodItemInfo(String rest) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT FoodItems.name, Calories, Fat, Sugar, Sodium, Carbs FROM FoodItems, NutritionalValues WHERE FoodItems.FId = NutritionalValues.FId AND RName = "
							+ "\"" + rest + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getFoodItemInfo(String rest, String food) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT FoodItems.name, Calories, Fat, Sugar, Sodium, Carbs, Price "
							+ "FROM FoodItems, NutritionalValues "
							+ "WHERE FoodItems.FId = NutritionalValues.FId "
							+ "AND RName = "
							+ "\""
							+ rest
							+ "\""
							+ "AND FoodItems.name = " + "\"" + food + "\"");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ugh it didn't work");
		}
		
		return rs;
	}

	public void insertReview(int rating, String review, String RName) {
		try {
			String rate = Integer.toString(rating);
			this.statement.executeUpdate("INSERT INTO Reviews VALUES (" + "\""
					+ RName + "\"" + ", " + rate + ", " + "\"" + review + "\""
					+ ")");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ugh it didn't work");
		}
		
	}

	public ResultSet getReviews(String rest) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT rating, description FROM Reviews"
							+ " WHERE Reviews.rname=" + "\"" + rest + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ResultSet getAverageRating(String rest) {
		ResultSet rs = null;
		try {
			rs = this.statement
					.executeQuery("SELECT AVG(rating) AS AverageRating FROM Reviews"
							+ " WHERE Reviews.rname=" + "\"" + rest + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public void closeDb() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
