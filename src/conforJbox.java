import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conforJbox {

	Connection con;
	
	public conforJbox() {
		
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
