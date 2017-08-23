package grafica;

public class EstremiRange {
	private int primo;
	private int ultimo;
	
	public EstremiRange (int primo, int ultimo) {
		this.primo = primo;
		this.ultimo = ultimo;
	}

	public int getPrimo() {
		return primo;
	}

	public void setPrimo(int primo) {
		this.primo = primo;
	}

	public int getUltimo() {
		return ultimo;
	}

	public void setUltimo(int ultimo) {
		this.ultimo = ultimo;
	}
}
