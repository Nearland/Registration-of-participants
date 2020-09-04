import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conforemploeyy {

	Connection con;
	
	public conforemploeyy() {
		
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://Employee.accdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public Connection returnConnection() {
		
		return con;
	}
}
