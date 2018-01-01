package secTest;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.secured.api.SecureDMapper;

public class jdbcTst {
	

    public static void main(String[] argv) {
    	    //SecureDMapper mapper = new SecureDMapper();
    	   // mapper.loadSecureDMapping();
    	    System.setProperty("enable.column.encrypt", "true");
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        Statement statement = null;
		ResultSet rs = null;
		PreparedStatement pstatement = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Class.forName("com.securedmeta.jdbc.SecureMetaDriver");
        	    //Class.forName("com.secured.engine.spy.SecureDDriver");	
         	Class.forName("com.secured.engine.spy.SecureDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;
        Connection connection_clear = null;

        try {
        //connection = DriverManager.getConnection("jdbc:secured:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/test?sessionVariables=sql_mode=ANSI_QUOTES","secured", "pwd4secured");
        connection = DriverManager.getConnection("jdbc:secured:oracle:thin:@neworadb.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:1521:neworadb","OCT1018TEST","span1234");
        //connection_clear = DriverManager.getConnection("jdbc:secured:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/tpch_demo?sessionVariables=sql_mode=ANSI_QUOTES","secured", "pwd4secured");
        
        //	connection = DriverManager.getConnection("jdbc:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/secured_oct11?sessionVariables=sql_mode=ANSI_QUOTES","secured", "SS!23456");
            
        	//	connection = DriverManager.getConnection("jdbc:secured:mysql://mysqlrep.cqzcaawwqsmw.us-east-1.rds.amazonaws.com/tpch??sessionVariables=sql_mode=ANSI_QUOTES","secured", "pwd4secured");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        
      /* try {
			statement = connection.createStatement();
			//SecureDMapper mapper = new SecureDMapper();
			//mapper.loadSecureDMapping();
			String v_sql = "select * from user_details";
			//String v_sql = "select * from customer";
			System.setProperty("enable.column.encrypt", "true");
			rs = statement.executeQuery(v_sql);
			while (rs.next()) {
			    System.out.println(rs.getString(1));
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
        
		/* String sql = "select * from OOCKE1_PRODUCT where object_id = 'product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0'";
        //String sql = "select * from OOCKE1_PRODUCT where object_id = 'product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0' " ;
        try {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
        PreparedStatement preparedStatement;
       String sql = "select * from OOCKE1_PRODUCT where object_id = ?";
        
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, "product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0");
			//preparedStatement.setString(1, "product");
			//ResultSet rs1 = preparedStatement.executeQuery();
			ResultSet rs1 = preparedStatement.executeQuery();
			
			while (rs1.next()) {
			    System.out.println(rs1.getString(1));
			    //System.out.println(rs1.getString("USERID"));
			    }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	/*	
		 
		 try {
			DatabaseMetaData metadata = connection.getMetaData();
			String table[]={"TABLE"};  
			ResultSet metadatars=metadata.getTables(null,null,null,table);  
			  
			while(metadatars.next()){  
			System.out.println(metadatars.getString(3));  
			}  
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
    } 

}
