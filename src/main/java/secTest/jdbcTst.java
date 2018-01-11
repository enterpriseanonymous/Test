package secTest;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.secured.api.apiCalls;

public class jdbcTst {

	public static void main(String[] argv) {
		// SecureDMapper mapper = new SecureDMapper();
		// mapper.loadSecureDMapping();
		System.setProperty("enable.column.encrypt", "true");
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		Statement statement = null;
		ResultSet rs = null;
		PreparedStatement pstatement = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName("com.securedmeta.jdbc.SecureMetaDriver");
			// Class.forName("com.secured.engine.spy.SecureDDriver");
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
			// connection =
			// DriverManager.getConnection("jdbc:secured:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/test?sessionVariables=sql_mode=ANSI_QUOTES","secured",
			// "pwd4secured");
			connection = DriverManager.getConnection(
					"jdbc:secured:oracle:thin:@neworadb.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:1521:neworadb",
					"OCT1018TEST", "span1234");
			// connection_clear =
			// DriverManager.getConnection("jdbc:secured:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/tpch_demo?sessionVariables=sql_mode=ANSI_QUOTES","secured",
			// "pwd4secured");

			// connection =
			// DriverManager.getConnection("jdbc:mysql://securedrep1.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:3306/secured_oct11?sessionVariables=sql_mode=ANSI_QUOTES","secured",
			// "SS!23456");

			// connection =
			// DriverManager.getConnection("jdbc:secured:mysql://mysqlrep.cqzcaawwqsmw.us-east-1.rds.amazonaws.com/tpch??sessionVariables=sql_mode=ANSI_QUOTES","secured",
			// "pwd4secured");
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

		/*
		 * try { statement = connection.createStatement(); //SecureDMapper mapper = new
		 * SecureDMapper(); //mapper.loadSecureDMapping(); String v_sql =
		 * "select * from user_details"; //String v_sql = "select * from customer";
		 * System.setProperty("enable.column.encrypt", "true"); rs =
		 * statement.executeQuery(v_sql); while (rs.next()) {
		 * System.out.println(rs.getString(1)); } } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * String sql =
		 * "select * from OOCKE1_PRODUCT where object_id = 'product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0'"
		 * ; //String sql =
		 * "select * from OOCKE1_PRODUCT where object_id = 'product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0' "
		 * ; try { Statement stmt = connection.createStatement();
		 * stmt.executeQuery(sql); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

		try {
			Statement test;
			System.setProperty("enable.column.encrypt", "true");
			String sql_test = "select * from \"xUlY7tmoE47siU9STgk5tQ==\" where rownum <2 ";
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@neworadb.cqzcaawwqsmw.us-east-1.rds.amazonaws.com:1521:neworadb", "OCT1018TEST",
					"span1234");
			Statement stmt = connection.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql_test);
			java.sql.ResultSetMetaData rsmd = rs1.getMetaData();
			apiCalls c = new apiCalls();
			int count = rsmd.getColumnCount();

			//Header Decryption
			
			for (int i = 1; i <= count; i++) {
				System.out.println(rsmd.getColumnName(i));
				System.out.println(rsmd.getTableName(i));
				System.out.println(c.getEncryptedColName(rsmd.getColumnName(i)));
				System.out.println(rsmd.getColumnTypeName(i));
			}
			
			while (rs1.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.println(rsmd.getColumnName(i));
					System.out.println(c.getEncryptedColName(rsmd.getColumnName(i)));
					//System.out.println(rs1.getString(i));
					//System.out.println("TableName");
					//System.out.println(rsmd.getTableName(i));
					//System.out.println("DecryptedValue");
					if ( rs1.getString(i) != null ) {
						System.out.println(c.getEncryptedColName(rsmd.getColumnName(i)));
						System.out.println(rs1.getString(i));
					    System.out.println(c.getDecryptedColValue(rsmd.getColumnName(i),rs1.getString(i),"xUlY7tmoE47siU9STgk5tQ=="));
				    }
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
          try {
        	      System.out.println("Decrypting...");
        	      String datavalue = "1271357932";
     		 String colName = "M4o/IuG4usbFKJErL7A9Gw=="; 
     		 String tabName = "xUlY7tmoE47siU9STgk5tQ==";
			System.out.println(apiCalls.getDecryptedColValue(colName,datavalue,tabName));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { PreparedStatement preparedStatement; String sql =
		 * "select * from OOCKE1_PRODUCT where object_id = ?"; preparedStatement =
		 * connection.prepareStatement(sql); preparedStatement.setString(1,
		 * "product/CRX/Standard/RHAAOER8L4ZJA1IE83KXQ5ID0"); ResultSet rs1 =
		 * preparedStatement.executeQuery(); java.sql.ResultSetMetaData
		 * rsmd=rs1.getMetaData();
		 * 
		 * String datavalue =
		 * "1271610475~1271614708~1271600973~1271572403~1271619599~1271564121~1271618804~1271356885~1271461464~1271506137~1271531757~1271356885~1271507261~1271618804~1271555003~1271600835~1271572403~1271555003~1271614708~1271572403~1271356885~1271506137~1271507261~1271484266~1271357932~1271461464~1271500268~1271484266~1271361455~1271361637~1271450995~1271465570~1271450995~1271463977~1271361455~1271483883~1271463977~1271397161~1271363058~1271484266~1271531757~1271500268~1271384333~1271483883~1271462254~1271357932";
		 * String colName = "iN49D6GC5guPO1NwQkZ9KQ=="; String tabName =
		 * "xUlY7tmoE47siU9STgk5tQ==";
		 * 
		 * System.out.println(apiCalls.getDecryptedColValue(colName,datavalue,tabName));
		 * System.out.println(apiCalls.getDecryptedColValue(colName,datavalue,tabName));
		 * 
		 * int count = rsmd.getColumnCount(); for (int i = 1; i <= count; i++) {
		 * //System.out.println("Column Name of 1st column: "+apiCalls.
		 * getEncryptedColName(rsmd.getColumnName(i)));
		 * 
		 * }
		 * 
		 * System.out.println("Total columns: "+rsmd.getColumnCount());
		 * System.out.println("Column Name of 1st column: "+apiCalls.getEncryptedColName
		 * (rsmd.getColumnName(1)));
		 * System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(
		 * 1));
		 * 
		 * while (rs1.next()) {
		 * 
		 * for (int i = 1; i <= 1; i++) {
		 * //System.out.println("Column Name of 1st column: "+apiCalls.
		 * getEncryptedColName(rsmd.getColumnName(i)));
		 * //System.out.println("Column Name of 1st column: "+apiCalls.
		 * getDecryptedColValue(rsmd.getColumnName(i),rs1.getString(i),"OOCKE1_PRODUCT")
		 * .toString());
		 * 
		 * //System.out.println(rs1.getString("OBJECT_ID"));
		 * //System.out.println("Column Name of 1st column: "+rs1.getString(apiCalls.
		 * getEncryptedColName(rsmd.getColumnName(i)).toUpperCase()));
		 * 
		 * } //System.out.println(rs1.getString("USERID")); } } catch (SQLException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 */

		/*
		 * 
		 * try { DatabaseMetaData metadata = connection.getMetaData(); String
		 * table[]={"TABLE"}; ResultSet
		 * metadatars=metadata.getTables(null,null,null,table);
		 * 
		 * while(metadatars.next()){ System.out.println(metadatars.getString(3)); }
		 * 
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

}
