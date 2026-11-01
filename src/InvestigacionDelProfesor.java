public class InvestigacionDelProfesor extends Soporte {

    public InvestigacionDelProfesor() {
        super(
            "Investigacion del Profesor",
            "Descarta tu mano actual y roba 2 cartas del mazo"
        );
    }

@Override
public Boolean puedeActivar(Jugador jugador) {
    return jugador != null &&
        jugador.puedeJugarSoporte() &&
        jugador.puedeRobar();
}

@Override
public Void Activar(Jugador jugador) {

    if (!puedeActivar(jugador)) {
        return null;
    }
    jugador.getMano().clear();
    jugador.robarCarta();
    jugador.robarCarta();
    jugador.registrarSoporte();
    System.out.println(
        "Investigacion del Profesor activada"
    );

    return null;
}
}