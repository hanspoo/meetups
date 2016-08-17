package meetups;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Meetup {

	boolean abierto = false;

	List<Cupo> cupos;

	public Meetup(int numCupos) {
		super();
		this.cupos = new ArrayList<>(numCupos);
		for (int i = 0; i < numCupos; i++) {
			cupos.add(new Cupo());
		}
	}

	public int numCupos() {
		return cupos.size();
	}

	public void hacerReserva(Persona persona) {

		if (!abierto)
			throw new IllegalStateException("El proceso está cerrado, no puede reservar");

		if (numCuposDisponibles() == 0)
			throw new IllegalStateException("No quedan cupos");

		primerCupoDisponible().agregarReserva(persona);
	}

	Cupo primerCupoDisponible() {
		Optional<Cupo> cupo = cupos.stream().filter(c -> c.estaDisponible()).findFirst();
		return cupo.orElse(null);
	}

	public void abrir() {
		abierto = true;

	}

	public int numCuposUtilizados() {

		return (int) cupos.stream().filter(c -> c.utilizado()).count();
	}

	public int numCuposDisponibles() {
		int size = cupos.size();
		int numCuposUtilizados = numCuposUtilizados();
		return size - numCuposUtilizados;
	}

	public boolean estaInscrito(Persona persona) {
		return true;
	}

	public void liberarCupo(Persona persona) {

		Reserva reserva = reservaDe(persona);
		reserva.anular();

	}

	Reserva reservaDe(Persona persona) {

		Optional<Cupo> cupo = cupos.stream().filter(c -> c.esReservaDe(persona)).findFirst();
		if (cupo.isPresent())
			return cupo.get().reservaActual();
		else
			return null;
	}

}
