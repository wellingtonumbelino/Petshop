package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import entidade.Login;
import util.Connect;

public class LoginDao extends Connect{	
	public LoginDao() {
		super();
	}

	public Login obterLogin(String user, String pass) {
		String sql = "SELECT * FROM login l WHERE l.usuario ILIKE '"+ user + "' AND l.senha ILIKE'" + pass + "';";
		
		try {
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			if(rs.next()) {
				Login login = new Login();
				login.setUser(rs.getString("usuario"));
				login.setPass(rs.getString("senha"));
				return login;
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
}
