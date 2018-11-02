package roombuds.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Publicprofile")
public class Publicprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://servername:3306/databasename?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 String USER = "username";
	 String PASS = "password"; 
	 Connection conn; 
	Statement stmt;
	ResultSet myRs=null;

	public void init(ServletConfig config) throws ServletException {
		
      try {
     	 Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		      

		   }
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
{
	Statement statement;
	try {
		statement = conn.createStatement();
		myRs=statement.executeQuery("SELECT * FROM users ");
		while(myRs.next())
		{
			String name = myRs.getString(1);
			String username = myRs.getString(2);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}

}
