package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Pet;
import util.Connect;

public class PetDao extends Connect{
	public PetDao() {
		super();
	}
	
	//função que adiciona um pet
	public void addPet(Pet pet) throws SQLException {
		String sql = "INSERT INTO pet (id_pet, nome_pet, raca, cor, observacoes, cpf_cliente) VALUES (default, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, pet.getNomePet());
			pstm.setString(2, pet.getRaca());
			pstm.setString(3, pet.getCor());
			pstm.setString(4, pet.getObservacao());
			pstm.setString(5, pet.getCpfCliente());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Pet cadastrado com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//função que atualiza o pet
	public void upPet(Pet pet) throws SQLException {
		String sql = "UPDATE pet SET observacoes = ? WHERE id_pet = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, pet.getObservacao());
			pstm.setInt(2, pet.getId());
			
			pstm.execute();
			pstm.close();
			
			System.out.println("Dados atualizados com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	//função que mostra todos os pets
	public List<Pet> mostrarPet(Pet pet){
		String sql = "SELECT * FROM pet ORDER BY id_pet";
		List<Pet> lista = new ArrayList<Pet>();
		try {
			PreparedStatement pstmn = con.prepareStatement(sql);
			ResultSet rs = pstmn.executeQuery();
			while(rs.next()) {
				Pet p = new Pet();
				p.setId(rs.getInt("id_pet"));
				p.setNomePet(rs.getString("nome_pet"));
				p.setRaca(rs.getString("raca"));
				p.setCor(rs.getString("cor"));
				p.setObservacao(rs.getString("observacoes"));
				p.setCpfCliente(rs.getString("cpf_cliente"));
				
				lista.add(p);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	//função que remove um pet
	public void deletePet(Pet pet) {
		String sql = "DELETE FROM trabalhando WHERE id_pet_serv IN (SELECT id_pet FROM pet WHERE id_pet = ?);\r\n" + 
				"DELETE FROM pet WHERE id_pet = ?;";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, pet.getId());
			pstm.setInt(2, pet.getId());
			pstm.execute();
			pstm.close();
			
			System.out.println("Pet deletado!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
