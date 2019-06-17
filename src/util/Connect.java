package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
		private String driver = "org.postgresql.Driver";
		private String user = "postgres";
		private String senha = "27707600";
		private String url = "jdbc:postgresql://localhost:5432/petshop";
		protected Connection con = null;

		public Connect() {
			try{
				Class.forName(driver);
				this.con = (Connection) DriverManager.getConnection(url, user, senha);
				System.out.println("Conexão realizada com sucesso");
			}catch(ClassNotFoundException ex) {
				System.err.print(ex.getMessage());
			}catch(SQLException e) {
				System.err.print(e.getMessage());
				e.printStackTrace();
			}	
		}
		
		public Connection getConexao() {
			return this.con;
		}
}