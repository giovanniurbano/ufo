package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			
			//Statement st = conn.createStatement();
			
			String sql = "SELECT DISTINCT shape FROM sighting WHERE shape <> '' ORDER BY shape ASC";
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUfo = new ArrayList<String>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUfo.add(forma);
			}
			st.close();
			
			System.out.println(formeUfo);
			
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ?";
			String shapeScelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			st2.close();
			System.out.println("Ufo di forma " + shapeScelta + " sono " +count);
			
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
