import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class confordoc {

	Connection con;
	
	public confordoc() {
		
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public Connection returnConnection() {
		
		return con;
	}
}
