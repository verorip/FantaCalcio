import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Sqlaccess {
	public Connection connection;
	
	public Sqlaccess(){
		
	}
	public Connection get_access() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fantacalcio", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void destroy(){
		try{
			connection.close();
		}
		catch(SQLException e){
			
		e.printStackTrace();
		}
	}
}
