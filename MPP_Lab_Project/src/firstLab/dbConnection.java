package firstLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
	 public static String temp="";
	public static String getDBConnection() 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Driver found" );
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			System.out.println("Class not found"+ e);
		}
		String url="jdbc.mysql://localhost/library";
		String user = "root";
		String password = "";
		Connection con = null;
		try
		{
			//con = DriverManager.getConnection(url,user,password);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			System.out.println("Connection successfull");
			Statement Stmt = con.createStatement();
			ResultSet RS = Stmt.executeQuery("SELECT * FROM lib_user");
			while(RS.next())
			{
				temp+=RS.getString(1);
				System.out.println(RS.getString(1));
			}
			RS.close();
			Stmt.close();
			con.close();
		}
		catch (SQLException e)
		{
			System.out.println("Connection filed"+e);
		}
		return temp;
		
	}
}
