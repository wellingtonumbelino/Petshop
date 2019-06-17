package syspet;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ClienteDao;
import dao.LoginDao;
import dao.PetDao;
import dao.ServicoDao;
import dao.TrabalhandoDao;
import entidade.Cliente;
import entidade.Login;
import entidade.Pet;
import entidade.Servico;
import entidade.Trabalhando;

public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner read = new Scanner(System.in);
		LoginDao loginDao = new LoginDao();
		Cliente cli = new Cliente();
		ClienteDao cliDao = new ClienteDao();
		Pet pet = new Pet();
		PetDao petDao = new PetDao();
		Servico serv = new Servico();
		ServicoDao servDao = new ServicoDao();
		Trabalhando trab = new Trabalhando();
		TrabalhandoDao trabDao = new TrabalhandoDao();
		
		System.out.print("Usuário: ");
		String user = read.nextLine();
		System.out.print("Senha: ");
		String pass = read.nextLine();
		Login usuario = loginDao.obterLogin(user, pass);
		if(usuario == null) {
			System.out.println("Usuario ou senha inválidos");
		} else {
			System.out.println("Bem-vindo! " + usuario.getUser());
		}
		
		while(true) {
			String op;
			Menu m = new Menu();
			m.menu();
			op = read.next();
			if(op.equals("1")) {
				//cadastrando um cliente
				System.out.print("Nome: ");
				cli.setNome(read.next());
				System.out.print("CPF: ");
				cli.setCpf(read.next());
				System.out.print("Dasta de Nascimento Ex.: (01/01/1900): ");
				cli.setDataNasc(read.nextLine());
				System.out.print("Bairro: ");
				cli.setBairro(read.next());
				System.out.print("Rua: ");
				cli.setRua(read.nextLine());
				System.out.print("Número da casa: ");
				cli.setNumero(Integer.parseInt(read.nextLine()));
				System.out.print("Telefone Ex.: (X XXXX-XXXX): ");
				cli.setTelefone(read.next());				
				cliDao.addCliente(cli);
			}else if(op.equals("2")) {
				//atualizando os dados do cliente
				System.out.print("Bairro: ");
				cli.setBairro(read.next());
				System.out.print("Rua: ");
				cli.setRua(read.next());
				System.out.print("Número da casa: ");
				cli.setNumero(read.nextInt());
				read.nextLine();
				System.out.print("Telefone Ex.: (X XXXX-XXXX): ");
				cli.setTelefone(read.next());
				System.out.print("CPF: ");
				cli.setCpf(read.next());
				cliDao.upCliente(cli);
			}else if(op.equals("3")) {
				//mostrando todos os clientes da base
				List<Cliente> listaCli = cliDao.mostrarCliente(cli);
				for(Cliente c : listaCli) {
					System.out.println("Nome: " + c.getNome() + " CPF: " + c.getCpf() + " Data_Nasc: " + c.getDataNasc() + " Bairro: " + c.getBairro() + " Rua: " + c.getRua() + " Número: " + c.getNumero() + " Telefone: " + c.getTelefone());
				}
			}else if(op.equals("4")){
				//adicionando o pet
				System.out.println("Adicione seu Pet");
				System.out.print("Nome: ");
				pet.setNomePet(read.next());
				System.out.print("Raça: ");
				pet.setRaca(read.next());
				System.out.print("Cor: ");
				pet.setCor(read.next());
				System.out.print("Observações: ");
				pet.setObservacao(read.next());
				System.out.print("CPF do cliente responsável: ");
				pet.setCpfCliente(read.next());				
				petDao.addPet(pet);
			}else if(op.equals("5")) {
				//atualizando os dados dos pets
				System.out.print("Observações: ");
				pet.setObservacao(read.next());
				System.out.print("ID do pet a ser modificado: ");
				pet.setId(read.nextInt());				
				petDao.upPet(pet);
			}else if(op.equals("6")) {
				//mostrando todos os pets da base
				List<Pet> listaPet = petDao.mostrarPet(pet);
				for(Pet p : listaPet) {
					System.out.println("ID: " + p.getId() + " Nome: " + p.getNomePet() + " Raça: " + p.getRaca() + " Cor: " + p.getCor() + " Observações: " + p.getObservacao() + " CPF Cliente: " + p.getCpfCliente());
				}
			}else if(op.equals("7")) {
				System.out.print("Digite o CPF do cliente: ");
				cli.setCpf(read.next());
				cliDao.deleteCli(cli);
			}else if(op.equals("8")){
				System.out.print("Digite o ID do Pet: ");
				pet.setId(read.nextInt());
				petDao.deletePet(pet);
			}else if(op.equals("9")){
				System.out.print("Nome do serviço: ");
				serv.setNomeServ(read.next());
				System.out.print("Valor do serviço: ");
				serv.setValorServ(read.nextDouble());
				servDao.addServico(serv);
			}else if(op.equals("10")){
				List<Servico> listaServ = servDao.mostrarServ(serv);
				for(Servico s : listaServ) {
					System.out.println("ID: " + s.getIdServ() + " Nome: " + s.getNomeServ() + " Valor: " + s.getValorServ());
				}
			}else if(op.equals("11")) {
				List<Trabalhando> listaTrab = trabDao.mostrarTrab(trab);
				for (Trabalhando t : listaTrab) {
					System.out.println("Id_pet: " + t.getId_pet_serv() + " Id_prod: " + t.getId_prod_serv() + " Id_serv: " + t.getId_serv());
				}
			}else if(op.equals("12")){
				System.out.print("Digite o novo valor do serviço: ");
				serv.setValorServ(read.nextDouble());
				System.out.print("ID do serviço a ser alterado: ");
				serv.setIdServ(read.nextInt());
				servDao.upServ(serv);
			}else if(op.equals("13")){
				System.out.print("Digite o ID do serviço a ser deletado: ");
				serv.setIdServ(read.nextInt());
				servDao.deleteServ(serv);
			}else if(op.equals("0")) {
				//saindo do sistema
				break;
			}
		}
		read.close();
	}
}
