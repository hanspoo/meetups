package meetups;

public class Reserva {

	Persona persona;
	boolean estaVigente = true;

	public Reserva(Persona persona) {
		super();
		this.persona = persona;
	}

	public void anular() {
		estaVigente = false;

	}

	public boolean estaVigente() {
		return estaVigente;
	}

}
