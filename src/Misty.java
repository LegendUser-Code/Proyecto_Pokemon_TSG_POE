public class Misty extends Soporte {

    public Misty() {
        super(
            "Misty",
            "Adjunta hasta 3 energias Agua a un Pokemon de Agua"
        );
    }

@Override
public Boolean puedeActivar(Jugador jugador) {

    if (jugador == null) {
        return false;
    }

    if (!jugador.puedeJugarSoporte()) {
        return false;
    }

    Pokemon activo =
        jugador.getCampo().getPokemonActivo();

    return activo != null &&
        activo.getTipo() == TipoEnergia.AGUA;
}

@Override
public Void Activar(Jugador jugador) {

    if (jugador == null) {
        return null;
    }

    Pokemon activo =
        jugador.getCampo().getPokemonActivo();

    if (activo != null &&
        activo.getTipo() == TipoEnergia.AGUA) {

        activo.adjuntarEnergia(TipoEnergia.AGUA);
        activo.adjuntarEnergia(TipoEnergia.AGUA);
        activo.adjuntarEnergia(TipoEnergia.AGUA);
        jugador.registrarSoporte();

        System.out.println(
            "Misty adjunto 3 energias Agua"
        );
    }

    return null;
}

}