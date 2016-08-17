package meetups;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cupo {

	List<Reserva> reservas = new ArrayList<>();

	public boolean utilizado() {
		return !estaDisponible();
	}

	public Reserva agregarReserva(Persona persona) {
		Reserva reserva = new Reserva(persona);
		this.reservas.add(reserva);

		return reserva;

	}

	public boolean estaDisponible() {

		if (reservas.size() == 0)
			return true;

		return reservas.stream().filter(r -> r.estaVigente()).count() == 0;
	}

	public boolean esReservaDe(final Persona persona) {

		Optional<Reserva> reserva = reservas.stream().filter(r -> r.estaVigente() && r.persona == persona).findFirst();
		return reserva.isPresent();

	}

	public Reserva reservaActual() {

		Optional<Reserva> reserva = reservas.stream().filter(r -> r.estaVigente()).findFirst();
		return reserva.orElse(null);
	}

	public Persona getPersonaActual() {
		Reserva reservaActual = reservaActual();

		if (reservaActual == null)
			throw new IllegalStateException("Reserva no encontrada para el cupo");

		return reservaActual.persona;
	}
}
