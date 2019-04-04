package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			
			conn = DB.getConnetion();
			// Query para inserir dados
			st = conn.prepareStatement(
					 "INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+"(?, ?, ?, ?, ?)");
			
			// Paramteros para inserir			
			st.setString(1, "Carl Purple"); // o primeiro parametro refere-se ao interregoção da query
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			//executar query
			
			// pegar quantidade de linhas afetadas no banco
			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows Affected: " + rowsAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnetion();
		}
		
	}

}
