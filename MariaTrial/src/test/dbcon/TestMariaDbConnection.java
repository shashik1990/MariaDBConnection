package test.dbcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TestMariaDbConnection {

	public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	public static final String DB_URL = "jdbc:mariadb://127.0.0.1/trial";

    //  Database credentials
	public static final String USER = "root";
	public static final String PASS = "mariadbpwd";
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Connection conn = null;
	        Statement stmt = null;
	        try {
	            //STEP 2: Register JDBC driver
	            Class.forName("org.mariadb.jdbc.Driver");

	            //STEP 3: Open a connection
	            System.out.println("Connecting to a selected database...");
	            conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3307/trial", "root", "mariadbpwd");
	            System.out.println("Connected database successfully...");

	            //STEP 4: Execute a query
	            stmt = conn.createStatement();	        
	            //  List<CardContactsBean> cardContactList = new ArrayList<CardContactsBean>();
	            Map<String, String> hmap = new HashMap<String, String>();     
	           
	            String sql = "SELECT * FROM card_contract where contract_id= '1234'";
	            ResultSet rs = stmt.executeQuery(sql);  
	            ResultSetMetaData rm= rs.getMetaData();
	            
	            int columnCount = rm.getColumnCount();
	            System.out.println("Column count : "+columnCount);
	           
	                while(rs.next())
	            { 
	              
	            for(int i= 1; i < columnCount; i++)
	            {
	            	String columnName = rm.getColumnName(i);
	           	    System.out.println(columnName);
	            	String columnValue= rs.getString(i);
	            	System.out.println(columnValue);
	            	hmap.put(columnName, columnValue);
	            	 }}
	                rs.close();
	                stmt.close();
	      
	           // hmap.put("contract_id", "1234");
	         //  hmap.put("account_number", "DE1234");
	           // hmap.put("account_currency", "EUR");
	            
	           for (Map.Entry<String, String> entry: hmap.entrySet())
	           {
	            	
	            	System.out.println("key: "+ entry.getKey() + " Value:" + entry.getValue());
	            	
	            }
	  } catch (SQLException se) {
          se.printStackTrace();
      } catch (Exception e) {
         
          e.printStackTrace();
      } finally {          
      	
      	try {
			
      		conn.close();
			} catch (SQLException e) {
				
				Logger.getLogger("", e.getMessage());
			}
      
}}

}
