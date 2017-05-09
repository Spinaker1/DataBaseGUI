package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
 
public class JavaDB 
{ 
    private Connection connection;
    private Statement stmt;
    
   public void createConnection(String user, String pswd)
   {
	System.out.println("-------- MySQL JDBC Connection Testing ------------");
        
	try 
        {
            Class.forName("com.mysql.jdbc.Driver");
	} 
        catch (ClassNotFoundException e) 
        {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	connection = null;

	try 
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/marcindb",user,pswd);
        } 
        catch (SQLException e) 
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
	}

	if (connection != null) 
        {
            System.out.println("You made it, take control your database now!");
	} 
        else 
        {
            System.out.println("Failed to make connection!");
            return;
	}
        
        createStatement();
   }
   
   private void createStatement()
   {
       System.out.println("Creating statement...");
       
       try 
       {
            stmt = connection.createStatement();
       }
       catch (SQLException e) 
       {
            System.out.println("Cannot create statement.");
            e.printStackTrace();
            return;
        }
   }
   
   public Vector getColumnNames(String tableName) 
   {
        Vector columnNames = new Vector();
       
        try 
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData rsmd = rs.getMetaData();

            int max = rsmd.getColumnCount();
            for (int i=1;i<=max;i++)
            {
                columnNames.add(rsmd.getColumnName(i));
            }
        }
       catch (SQLException e) 
       {
           e.printStackTrace();
       }
        
       return columnNames;
   }
   
   public Vector getTable(String tableName) 
   {
        Vector columnNames = getColumnNames(tableName);
        Vector table = new Vector();
        
        try 
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
            
            while(rs.next())
            {
                Vector row = new Vector();
                for (int i=0; i<columnNames.size(); i++)
                {
                    String text  = rs.getString(columnNames.get(i).toString());
                    row.add(text);
                }
                table.add(row);
            }
        }
       catch (SQLException e) 
       {
           e.printStackTrace();
       }
        
       return table;
   }
   
   public void deleteRow(String tableName, String id)
   {
       try
       {
            stmt.executeUpdate("DELETE FROM "+tableName+" WHERE nr_rejestracyjny='"+id+"'");
       }
       catch (SQLException e)
       {
            e.printStackTrace();
       }
   }
   
   public void insertRow(String tableName, Vector newValues)
   {
       //.try
       {
           String text="INSERT INTO Ubezpieczenie VALUES('";
            for (Object newText: newValues)
            {
                text += newText + "','";
            }
            text = text.substring(0, text.length()-2);
            text += ")";
            System.out.println(text);
            //stmt.executeUpdate("");
       }
       /*catch (SQLException e)
       {
            e.printStackTrace();
       }*/
   }


}

         