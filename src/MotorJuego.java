public class MotorJuego {

    private Jugador jugador1;
    private Jugador jugador2;

    private Consola consola;

    private int turno;

    public MotorJuego(
        Jugador jugador1,
        Jugador jugador2,
        Consola consola
    ) {

        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.consola = consola;

        this.turno = 1;
    }

public void iniciarPartida() {

    jugador1.robarManoInicial();
    jugador2.robarManoInicial();

    colocarPokemonInicial(jugador1);
    colocarPokemonInicial(jugador2);

    jugador1.generarEnergiaTurno();
    jugador2.generarEnergiaTurno();

    System.out.println("La partida ha comenzado");
}

    private void colocarPokemonInicial(Jugador jugador) {

    for (Carta carta : jugador.getMano()) {

        if (carta instanceof Pokemon &&
            carta.esPokemonBasico()) {

            jugador.colocarPokemonEnJuego(carta);

            System.out.println(
                jugador.getNombre()
                + " coloco a "
                + carta.getNombre()
                + " como Pokemon inicial"
            );

            break;
        }
    }
}

    public void ejecutarPartida() {

        while (!verificarGanador()) {

            ejecutarTurno(
                jugadorActual(),
                jugadorRival()
            );

            cambiarTurno();
        }

        anunciarGanador();
    }

private void ejecutarTurno(
    Jugador jugadorActual,
    Jugador rival
) {

    jugadorActual.iniciarTurno();

    jugadorActual.robarCarta();

    jugadorActual.generarEnergiaTurno();

    boolean terminarTurno = false;

    while (!terminarTurno) {

        consola.mostrarEstado(
            jugadorActual,
            rival,
            turno
        );

        int accion =
            consola.pedirAccion();

        switch (accion) {

    case 1:

        if (jugadorActual.adjuntarEnergia()) {

            System.out.println(
                "Energia adjuntada correctamente."
            );

        } else {

            System.out.println(
                "No fue posible adjuntar energia."
            );
        }

        break;

    case 2:

        System.out.println(
            "Jugar entrenador aun no implementado."
        );

        break;

    case 3:

        System.out.println(
            "Colocar Pokemon en banco aun no implementado."
        );

        break;

    case 4:

        System.out.println(
            "Retirar Pokemon aun no implementado."
        );

        break;

    case 5:

        Pokemon activo =
            jugadorActual.getCampo()
                .getPokemonActivo();

        if (activo == null) {

            System.out.println(
                "No hay Pokemon activo."
            );

            break;
        }

        if (activo.getAtaques().isEmpty()) {

            System.out.println(
                "Este Pokemon no tiene ataques."
            );

            break;
        }

        System.out.println(
            "Selecciona un ataque:"
        );

        for (int i = 0;
             i < activo.getAtaques().size();
             i++) {

            Ataque ataque =
                activo.getAtaques().get(i);

            System.out.println(
                (i + 1)
                + ". "
                + ataque.getNombre()
            );
        }

        int opcionAtaque =
            consola.elegirAtaque();

        opcionAtaque--;

        if (opcionAtaque >= 0 &&
            opcionAtaque <
            activo.getAtaques().size()) {

            Ataque ataqueSeleccionado =
                activo.getAtaques()
                    .get(opcionAtaque);

            boolean resultado =
                jugadorActual.atacar(
                    ataqueSeleccionado,
                    rival
                );

            if (resultado) {

                System.out.println(
                    "Ataque realizado."
                );

            } else {

                System.out.println(
                    "No fue posible atacar."
                );
            }
        }

        break;

    case 6:

        terminarTurno = true;

        break;

    default:

        System.out.println(
            "Opcion invalida."
        );

        break;
    }
}

    System.out.println(
        "Turno finalizado para "
        + jugadorActual.getNombre()
    );
}
public boolean verificarGanador() {

    if (jugador1.gano()) {
        return true;
    }

    if (jugador2.gano()) {
        return true;
    }

    if (!jugador1.tienePokemonEnJuego()) {
        return true;
    }

    if (!jugador2.tienePokemonEnJuego()) {
        return true;
    }

    return false;
}

private void anunciarGanador() {

    if (jugador1.gano() ||
        !jugador2.tienePokemonEnJuego()) {

        consola.anunciarGanador(
            jugador1.getNombre()
        );

        return;
    }

    consola.anunciarGanador(
        jugador2.getNombre()
    );
}

private void cambiarTurno() {

    if (turno == 1) {
        turno = 2;
    }
    else {
        turno = 1;
    }
}

private Jugador jugadorActual() {

    if (turno == 1) {
        return jugador1;
    }

    return jugador2;
}

private Jugador jugadorRival() {

    if (turno == 1) {
        return jugador2;
    }

    return jugador1;
}

public Jugador getJugador1() {
    return jugador1;
}

public Jugador getJugador2() {
    return jugador2;
}

public int getTurno() {
    return turno;
}
}
