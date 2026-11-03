import java.util.ArrayList;
import java.util.List;

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

    // El jugador elige obligatoriamente 1 Pokémon como Activo. Opcionalmente
    // puede colocar hasta 2 básicos más en la Banca. Solo puede haber 1 Activo.
    private void colocarPokemonInicial(Jugador jugador) {

        List<Carta> mano = jugador.getMano();

        // Recolectar los Pokémon básicos de la mano
        List<Carta> basicos = new ArrayList<>();
        for (Carta carta : mano) {
            if (carta instanceof Pokemon && carta.esPokemonBasico()) {
                basicos.add(carta);
            }
        }

        if (basicos.isEmpty()) {
            return;
        }

        // 1. El jugador elige 1 Pokémon como Activo
        int opcion = consola.elegirPokemonActivo(basicos);

        Carta activo = basicos.get(opcion - 1);
        jugador.colocarPokemonEnJuego(activo);
        System.out.println(activo.getNombre() + " es el Pokémon activo.");

        // 2. Opcionalmente colocar hasta 2 básicos más en la Banca
        while (jugador.getCampo().getBanco().size() < 2) {
            List<Carta> restantes = new ArrayList<>();
            for (Carta c : jugador.getMano()) {
                if (c instanceof Pokemon && c.esPokemonBasico()) {
                    restantes.add(c);
                }
            }
            if (restantes.isEmpty()) {
                break;
            }
            int sel = consola.elegirPokemonBanca(restantes);
            if (sel == 0) {
                break;
            }
            Carta alBanco = restantes.get(sel - 1);
            if (jugador.colocarPokemonEnJuego(alBanco)) {
                System.out.println(alBanco.getNombre() + " fue al banco.");
            } else {
                System.out.println("No fue posible colocarlo.");
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

    case 1: {
        // Adjuntar 1 energía del turno al Activo o a un Pokémon de la Banca
        List<Pokemon> propios = new ArrayList<>();
        Pokemon activoAux = jugadorActual.getCampo().getPokemonActivo();
        if (activoAux != null) {
            propios.add(activoAux);
        }
        propios.addAll(jugadorActual.getCampo().getBanco());

        if (propios.isEmpty()) {
            System.out.println("No tienes Pokémon en juego.");
            break;
        }

        System.out.println("¿A qué Pokémon quieres adjuntar la energía?");
        for (int i = 0; i < propios.size(); i++) {
            Pokemon p = propios.get(i);
            String zona = (p == activoAux) ? "[Activo]" : "[Banco]";
            System.out.println((i + 1) + ". " + zona + " " + p.getNombre()
                + " (PS " + p.getPsActual() + "/" + p.getPsMaximo() + ")");
        }

        int opcionEnergia;
        do {
            opcionEnergia = consola.elegirPokemon();
        } while (opcionEnergia < 1 || opcionEnergia > propios.size());

        Pokemon objetivoEnergia = propios.get(opcionEnergia - 1);

        if (jugadorActual.adjuntarEnergia(objetivoEnergia)) {
            System.out.println("Energia adjuntada correctamente.");
        } else {
            System.out.println("No fue posible adjuntar energia.");
        }
        break;
    }

    case 2:

    System.out.println(
        "Cartas de entrenador disponibles:"
    );

    List<Carta> mano =
        jugadorActual.getMano();

    int contador = 1;

    for (Carta carta : mano) {

        if (carta instanceof Entrenador) {

            System.out.println(
                contador
                + ". "
                + carta.getNombre()
            );

            contador++;
        }
    }

    if (contador == 1) {

        System.out.println(
            "No tienes entrenadores en la mano."
        );

        break;
    }

    int opcionEntrenador =
        consola.elegirEntrenador();

    contador = 1;

    for (int i = 0;
        i < mano.size();
        i++) {

        Carta carta = mano.get(i);

        if (carta instanceof Entrenador) {

            if (contador ==
                opcionEntrenador) {

                Entrenador entrenador =
                    (Entrenador) carta;

                if (entrenador.puedeActivar(
                        jugadorActual
                    )) {

                    entrenador.Activar(
                        jugadorActual
                    );

                    mano.remove(i);

                } else {

                    System.out.println(
                        "No es posible activar esa carta."
                    );
                }

                break;
            }

            contador++;
        }
    }
     
    break;

case 3:

    System.out.println(
        "Pokemon disponibles:"
    );

    List<Carta> manoPokemon =
        jugadorActual.getMano();

    int contadorPokemon = 1;

    for (Carta carta : manoPokemon) {

        if (carta instanceof Pokemon &&
            carta.esPokemonBasico()) {

            System.out.println(
                contadorPokemon
                + ". "
                + carta.getNombre()
            );

            contadorPokemon++;
        }
    }

    if (contadorPokemon == 1) {

        System.out.println(
            "No tienes Pokemon basicos en la mano."
        );

        break;
    }

    int opcionPokemon =
        consola.elegirPokemon();

    contadorPokemon = 1;

    for (Carta carta : manoPokemon) {

        if (carta instanceof Pokemon &&
            carta.esPokemonBasico()) {

            if (contadorPokemon ==
                opcionPokemon) {

                boolean colocado =
                    jugadorActual
                        .colocarPokemonEnJuego(
                            carta
                        );

                if (colocado) {

                    System.out.println(
                        carta.getNombre()
                        + " fue colocado en juego."
                    );

                } else {

                    System.out.println(
                        "No fue posible colocarlo."
                    );
                }

                break;
            }

            contadorPokemon++;
        }
    }

    break;

case 4:

    List<Pokemon> banco =
        jugadorActual.getCampo().getBanco();

    if (banco.isEmpty()) {

        System.out.println(
            "No hay Pokemon en el banco."
        );

        break;
    }

    System.out.println(
        "Pokemon disponibles para sustituir al activo:"
    );

    for (int i = 0;
        i < banco.size();
        i++) {

        Pokemon pokemon =
            banco.get(i);

        System.out.println(
            (i + 1)
            + ". "
            + pokemon.getNombre()
            + " (PS "
            + pokemon.getPsActual()
            + "/"
            + pokemon.getPsMaximo()
            + ")"
        );
    }

    int opcionRetirada =
        consola.elegirPokemon();

    opcionRetirada--;

    if (opcionRetirada < 0 ||
        opcionRetirada >= banco.size()) {

        System.out.println(
            "Seleccion invalida."
        );

        break;
    }

    Pokemon nuevoActivo =
        banco.get(opcionRetirada);

    boolean retirado =
        jugadorActual.retirarPokemonActivo(
            nuevoActivo
        );

    if (retirado) {

        System.out.println(
            "Se retiro el Pokemon activo."
        );

    } else {

        System.out.println(
            "No fue posible realizar la retirada."
        );
    }

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
        "Ataques disponibles:"
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
            + " | Daño: "
            + ataque.getDanioBase()
            + " | Coste: "
            + ataque.getCostoEnergia()
        );
    }

    int opcionAtaque =
        consola.elegirAtaque();

    opcionAtaque--;

    if (opcionAtaque < 0 ||
        opcionAtaque >= activo.getAtaques().size()) {

        System.out.println(
            "Ataque invalido."
        );

        break;
    }

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

        terminarTurno = true;

    } else {

        System.out.println(
            "No fue posible atacar."
        );
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
