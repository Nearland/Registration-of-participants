package copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conforJbox1 {

	Connection con;
	
	public conforJbox1() {
		
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
