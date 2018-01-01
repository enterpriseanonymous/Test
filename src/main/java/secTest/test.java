package secTest;

import org.slf4j.LoggerFactory;

public class test {


	final private static org.slf4j.Logger log = LoggerFactory.getLogger(test.class);
	//@SuppressWarnings("null")

	public static void main(String a[]) {

		
		//SecureDMapper mapper = new SecureDMapper();
		//mapper.loadSecureDMapping();
		String v_sql = "select * from user_details";
		System.setProperty("enable.column.encrypt", "true");
		
		//String mod_sql = translateStatement(v_sql);
		
		
		
		//System.out.println(mod_sql);

	}

}
