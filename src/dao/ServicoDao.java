package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Servico;
import util.Connect;

public class ServicoDao extends Connect{	
	public ServicoDao() {
		super();
	}

	//função que adiciona um serviço
	public void addServico(Servico serv) throws SQLException {
		String sql = "INSERT INTO servico (id_serv, nome_serv, valor_serv) VALUES (default, ?, ?);";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, serv.getNomeServ());
			pstm.setDouble(2, serv.getValorServ());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Serviço cadastrado com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//função que atualiza o valor de um serviço
	public void upServ(Servico serv) throws SQLException {
		String sql = "UPDATE servico SET valor_serv = ? WHERE id = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDouble(1,  serv.getValorServ());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Dados atualizados com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//mostrando todos os serviços
	public List<Servico> mostrarServ(Servico serv){
		String sql = "SELECT * FROM servico";
		List<Servico> lista = new ArrayList<Servico>();
		try {
			PreparedStatement pstmn = con.prepareStatement(sql);
			ResultSet rs = pstmn.executeQuery();
			while(rs.next()) {
				Servico s = new Servico();
				s.setIdServ(rs.getInt("id_serv"));
				s.setNomeServ(rs.getString("nome_serv"));
				s.setValorServ(rs.getDouble("valor_serv"));
				
				lista.add(s);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	//função que apaga um serviço
	public void deleteServ(Servico serv) {
		String sql = "DELETE FROM trabalhando WHERE id_serv IN (SELECT id_serv FROM servico WHERE id_serv = ?);\r\n" + 
				"DELETE FROM servico WHERE id_serv = ?;";
		try {
			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setInt(1, serv.getIdServ());
			pstmn.setInt(2, serv.getIdServ());
			
			pstmn.execute();
			pstmn.close();
			
			System.out.println("Serviço deletado!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
