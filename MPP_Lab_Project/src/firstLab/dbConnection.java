package firstLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dbConnection {
	 private static String temp;
	 private static ArrayList<String> TableName=new ArrayList<String>();
	 public dbConnection()
	 {
		 	TableName.clear();
			TableName.add("lib_author");
			TableName.add("lib_author_catalog");
			TableName.add("lib_book");
		 	TableName.add("lib_book_archive");
		 	TableName.add("lib_book_catalog");
		 	TableName.add("lib_book_genre");
		 	TableName.add("lib_book_rating");
		 	TableName.add("lib_chat");
		 	TableName.add("lib_comment");
		 	TableName.add("lib_message");
		 	TableName.add("lib_genre");
		 	TableName.add("lib_news");
		 	TableName.add("lib_news_page");
		 	TableName.add("lib_section");
		 	TableName.add("lib_user");
	 }
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
			temp="";
			//con = DriverManager.getConnection(url,user,password);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			System.out.println("Connection successfull");
			for (int j=0;j<TableName.size();j++)
			{
				temp+="<table border=1 style=margin-bottom:44px;><thead text-align=center>"+TableName.get(j)+"</thead>";
				Statement Stmt = con.createStatement();
				ResultSet RS = Stmt.executeQuery("SELECT * FROM "+TableName.get(j));
				temp+="<tr>";
				for(int i=1;i<=RS.getMetaData().getColumnCount();i++)
				{
					temp+="<td>"+RS.getMetaData().getColumnName(i)+"</td>";
				}
				temp+="</tr><br>";
				while(RS.next())
				{
					temp+="<tr>";
				for(int i=1;i<=RS.getMetaData().getColumnCount();i++)
				{
					temp+="<td>"+RS.getString(i)+"</td>";
				}
				temp+="</tr>";
			}
			temp+="</table>";
			RS.close();
			Stmt.close();
			}
			con.close();
		}
		catch (SQLException e)
		{
			System.out.println("Connection filed"+e);
		}
		return temp;
		
	}
}
