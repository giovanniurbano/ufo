package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=giovanni";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <> '' ORDER BY shape ASC";
			
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUfo = new ArrayList<String>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUfo.add(forma);
			}
			System.out.println(formeUfo);
			
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
