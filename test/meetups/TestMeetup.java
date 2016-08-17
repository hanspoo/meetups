package meetups;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class TestMeetup {

	@Test
	public void pruebaConstructor() {

		Meetup meetup = new Meetup(20);
		assertThat(meetup.numCupos(), is(20));

	}

	@Test
	public void pruebaConstructorCon10Cupos() {

		Meetup meetup = new Meetup(10);
		assertThat(meetup.numCupos(), is(10));

	}

	@Test(expected = IllegalStateException.class)
	public void noSePuedeHacerReservaSiCerrado() {

		Meetup meetup = new Meetup(10);
		meetup.hacerReserva(new Persona());

	}

	@Test
	public void pimerCupoDebeEstarDisponible() {

		Meetup meetup = new Meetup(10);

		Cupo cupo = meetup.cupos.get(0);
		assertThat(cupo.estaDisponible(), is(true));
		assertThat(cupo.utilizado(), is(false));
		
		meetup.abrir();
		
		Persona persona = new Persona();
		meetup.hacerReserva(persona);

		assertThat(cupo.estaDisponible(), is(false));
		assertThat(cupo.utilizado(), is(true));
		
		assertThat(cupo.getPersonaActual(), is(persona));
	}
	
	
	@Test(expected=IllegalStateException.class)
	public void cupoSinReservaLanzaException() {
		Meetup meetup = new Meetup(10);

		Cupo cupo = meetup.cupos.get(0);
		
		cupo.getPersonaActual();
	}
	
	

	@Test
	public void testCuposTodosDisponibles() {

		Meetup meetup = new Meetup(10);
		for (Cupo cupo : meetup.cupos) {
			assertThat(cupo.estaDisponible(), is(true));
			assertThat(cupo.utilizado(), is(false));
		}

	}

	@Test
	public void todosUtilizadosConReserva() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		for (Cupo cupo : meetup.cupos) {
			meetup.hacerReserva(new Persona());
			assertThat(cupo.estaDisponible(), is(false));
		}

	}

	@Test
	@Ignore
	public void libereCupoQuedaDisponible() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		Cupo cupo = meetup.primerCupoDisponible();

		assertThat(cupo.estaDisponible(), is(true));

		Persona persona = new Persona();
		cupo.agregarReserva(persona);
		assertThat(cupo.estaDisponible(), is(false));

		Reserva reserva = meetup.reservaDe(persona);
		assertThat(reserva, is(notNullValue()));

		meetup.liberarCupo(persona);
		assertThat(cupo.estaDisponible(), is(true));

	}

	@Ignore
	@Test
	public void sePuedeReservarSiAbierto() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		meetup.hacerReserva(new Persona());

	}

	@Ignore
	@Test(expected = IllegalStateException.class)
	public void exceptionMaxReservasSuperadas() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		for (int i = 0; i < 11; i++) {
			meetup.hacerReserva(new Persona());
		}

	}

	@Ignore
	@Test
	public void cuposDisponiblesVsOcupadas() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		for (int i = 0; i < 3; i++) {
			meetup.hacerReserva(new Persona());
		}
		assertThat(meetup.numCuposUtilizados(), is(3));
		assertThat(meetup.numCuposDisponibles(), is(7));

	}

	@Ignore
	@Test
	public void cuposDisponiblesVsOcupadasCincoCinco() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();
		for (int i = 0; i < 5; i++) {
			meetup.hacerReserva(new Persona());
		}
		assertThat(meetup.numCuposUtilizados(), is(5));
		assertThat(meetup.numCuposDisponibles(), is(5));
	}

	@Ignore
	@Test
	public void agregarReservaCorrectamenteValidaPersonas() {

		Meetup meetup = new Meetup(10);
		meetup.abrir();

		Persona persona1 = new Persona();
		meetup.hacerReserva(persona1);
		assertThat(meetup.estaInscrito(persona1), is(true));

		Persona persona2 = new Persona();
		assertThat(meetup.estaInscrito(persona2), is(false));
	}

}
