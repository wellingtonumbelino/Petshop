package entidade;

public class Servico {
	private int idServ;
	private String nomeServ;
	private double valorServ;
	
	public Servico() {
		
	}

	public int getIdServ() {
		return idServ;
	}

	public void setIdServ(int idServ) {
		this.idServ = idServ;
	}

	public String getNomeServ() {
		return nomeServ;
	}

	public void setNomeServ(String nomeServ) {
		this.nomeServ = nomeServ;
	}

	public double getValorServ() {
		return valorServ;
	}

	public void setValorServ(double valorServ) {
		this.valorServ = valorServ;
	}
}
