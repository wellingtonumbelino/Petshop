package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
import util.Connect;

public class ClienteDao extends Connect{
	public ClienteDao() {
		super();
	}
	
	//função que adiciona um cliente no banco
	public void addCliente(Cliente cli) throws SQLException {
		String sql = "INSERT INTO cliente (cpf, data_nasc, bairro, rua, num_casa, telefone, nome) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, cli.getCpf());
			pstm.setString(2, cli.getDataNasc());
			pstm.setString(3, cli.getBairro());
			pstm.setString(4, cli.getRua());
			pstm.setInt(5, cli.getNumero());
			pstm.setString(6, cli.getTelefone());
			pstm.setString(7, cli.getNome());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Cliente cadastrado com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//função que atualiza um cliente da base
	public void upCliente(Cliente cli) throws SQLException {
		String sql = "UPDATE cliente SET bairro = ?, rua = ?, num_casa = ?, telefone = ? WHERE cpf = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, cli.getBairro());
			pstm.setString(2, cli.getRua());
			pstm.setInt(3, cli.getNumero());
			pstm.setString(4, cli.getTelefone());
			pstm.setString(5, cli.getCpf());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Dados atualizados com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//função que mostra todos os clientes
	public List<Cliente> mostrarCliente(Cliente cliente){
		String sql = "SELECT * FROM cliente ORDER BY nome";
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			PreparedStatement pstmn = con.prepareStatement(sql);
			ResultSet rs = pstmn.executeQuery();
			while(rs.next()) {
				Cliente cli = new Cliente();
				cli.setNome(rs.getString("nome"));
				cli.setCpf(rs.getString("cpf"));
				cli.setDataNasc(rs.getString("data_nasc"));
				cli.setBairro(rs.getString("bairro"));
				cli.setRua(rs.getString("rua"));
				cli.setNumero(rs.getInt("num_casa"));
				cli.setTelefone(rs.getString("telefone"));
				
				lista.add(cli);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	//função que deleta um cliente da base junto com o seu pet
	public void deleteCli(Cliente cli) {
		String sql = "DELETE FROM trabalhando  WHERE id_pet_serv IN\r\n" + 
				"(SELECT id_pet FROM pet WHERE cpf_cliente IN\r\n" + 
				" (SELECT cpf FROM cliente WHERE cpf = ?));\r\n" + 
				"DELETE FROM pet WHERE cpf_cliente IN\r\n" + 
				"(SELECT cpf FROM cliente WHERE cpf = ?);\r\n" + 
				"DELETE FROM cliente WHERE cpf = ?;";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, cli.getCpf());
			pstm.setString(2, cli.getCpf());
			pstm.setString(3, cli.getCpf());
			pstm.execute();
			pstm.close();
			
			System.out.println("Cliente e seu pet deletados!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
