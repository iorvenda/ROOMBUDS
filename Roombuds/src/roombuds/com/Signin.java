package roombuds.com;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@WebServlet("/Signin")
public class Signin extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	 String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://mysql3000.mochahost.com:3306/iorvenda_clients?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 String USER = "iorvenda_i";
	 String PASS = "!@#123ASdfghj"; 
	  Connection conn; 
	PreparedStatement prestm = null;
	ResultSet myRs=null;
// hash function
	public static String getHash(byte[]inputBytes)
	{
		String hashValue="";
		String algorithm="SHA-256";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(inputBytes);
			byte[] digestedBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		}
		catch(Exception e)
		{
			
		}
		return hashValue;
	}
 
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
       try {
      	 Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        System.out.println("connected");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	 	      

		   }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{  
		       
	      try {
	    	  String username = req.getParameter("username");
	    	 String password = getHash(req.getParameter("password").getBytes());
	    	 Statement statement=conn.createStatement();
	    	 myRs=statement.executeQuery("SELECT * FROM login ");
	    	while(myRs.next()) {
	    	    if(username.equals(myRs.getString(1)) && password.equals(myRs.getString(2)))
    	         {
	    	    	resp.setContentType("text/html");
		    		 HttpSession session = req.getSession();
		    		 session.setAttribute("loggedin", username);
		    		  RequestDispatcher rd = req.getRequestDispatcher("publicprofile.html");
		    		  rd.forward(req, resp);
		    		 // return;
	    	         }
	    	else {
	    		
	    	   RequestDispatcher rd = req.getRequestDispatcher("index.html");
	    	      
	  	        rd.forward(req, resp);
	  	       // return;
	    	     }
	    		
	    	}
	    	 
	    	
			
			myRs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
	      

	}

}
