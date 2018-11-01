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
import javax.xml.bind.DatatypeConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



@WebServlet("/Profile")
public class Profile extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	 String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://mysql3000.mochahost.com:3306/iorvenda_clients?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 String USER = "iorvenda_i";
	 String PASS = "!@#123ASdfghj"; 
	 Connection conn; 
	Statement stmt;

	public void init(ServletConfig config) throws ServletException {
		
       try {
      	 Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		      

		   }
	public static String getHash(byte[]inputBytes)
	{
		String hashValue = null;
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
 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String password="";
	      try {
	    	  String name = req.getParameter("name");
	    	  String username = req.getParameter("username");
	    	  String firstpassword = req.getParameter("firstpassword");
	    	  String secondpassword= req.getParameter("secondpassword");
	    	 
	    	  password = getHash(secondpassword.getBytes());
	    	  
	    	  int age = Integer.parseInt(req.getParameter("age"));
	    	  String state = req.getParameter("state");
	    	  String gender = req.getParameter("gender");
	    	  String school = req.getParameter("school"); 
	     	  String city = req.getParameter("city");
	    	  String zipcode = req.getParameter("zipcode");
	    	  String major = req.getParameter("major");
	    	  String occupation = req.getParameter("occupation");
	    	  String phone =req.getParameter("phone");
	    	  stmt  = conn.createStatement(); 
     		  String sql = "insert into users values('"+username+"','"+password+"','"+name+"','"+age+"','"+state+"','"+gender+"','"+school+"','"+city+"','"+zipcode+"','"+major+"','"+occupation+"','"+phone+"')"; 
     		 while(firstpassword.equals(secondpassword)!=true || firstpassword==null || secondpassword==null)
	    	  {  resp.setContentType("text/html");
	    		                                                 
	    		  RequestDispatcher rd = req.getRequestDispatcher("profile.html");
	    		 rd.forward(req, resp);
	    	
	    	  }
     		resp.setContentType("text/html");
  		  if(firstpassword.equals(secondpassword)==true) { 
  			stmt.executeUpdate(sql);
			stmt.close();
  		
  		
  		
  		  }
  		 RequestDispatcher rd = req.getRequestDispatcher("publicprofile.html");
  		 rd.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
	      

	}

}
