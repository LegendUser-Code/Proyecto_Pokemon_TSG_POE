import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Consola consola = new Consola();

        CatalogoCartas catalogo =
            new CatalogoCartas();

        String nombreJugador1 =
            consola.pedirNombreJugador();

        int mazoJugador1 =
            consola.elegirMazo(nombreJugador1);

        String nombreJugador2 =
            consola.pedirNombreJugador();

        int mazoJugador2 =
            consola.elegirMazo(nombreJugador2);

        Mazo mazo1 =
            catalogo.crearMazoPredefinido(
                mazoJugador1
            );

        Mazo mazo2 =
            catalogo.crearMazoPredefinido(
                mazoJugador2
            );

        List<TipoEnergia> energias =
            new ArrayList<>();

        energias.add(TipoEnergia.FUEGO);
        energias.add(TipoEnergia.AGUA);
        energias.add(TipoEnergia.ELECTRICO);
        energias.add(TipoEnergia.PSIQUICO);
        energias.add(TipoEnergia.INCOLORO);

        ZonaEnergia zonaJugador1 =
            new ZonaEnergia(energias);

        ZonaEnergia zonaJugador2 =
            new ZonaEnergia(energias);

        Jugador jugador1 =
            new Jugador(
                nombreJugador1,
                mazo1,
                zonaJugador1
            );

        Jugador jugador2 =
            new Jugador(
                nombreJugador2,
                mazo2,
                zonaJugador2
            );

        MotorJuego motor =
            new MotorJuego(
                jugador1,
                jugador2,
                consola
            );

        motor.iniciarPartida();

        motor.ejecutarPartida();
    }
}
