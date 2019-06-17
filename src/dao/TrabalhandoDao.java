package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Trabalhando;
import util.Connect;

public class TrabalhandoDao extends Connect{
	
	public TrabalhandoDao() {
		super();
	}
	
	//mostrando todos os trabalhos
		public List<Trabalhando> mostrarTrab(Trabalhando trab){
			String sql = "SELECT * FROM trabalhando";
			List<Trabalhando> lista = new ArrayList<Trabalhando>();
			try {
				PreparedStatement pstmn = con.prepareStatement(sql);
				ResultSet rs = pstmn.executeQuery();
				while(rs.next()) {
					Trabalhando s = new Trabalhando();
					s.setId_pet_serv(rs.getInt("id_pet_serv"));
					s.setId_prod_serv(rs.getInt("id_prod_serv"));
					s.setId_serv(rs.getInt("id_serv"));
					
					lista.add(s);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return lista;
		}
	
	
	
	
}
