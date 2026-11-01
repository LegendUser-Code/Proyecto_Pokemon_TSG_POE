public class Sabrina extends Soporte {

    public Sabrina() {
        super(
            "Sabrina",
            "Adjunta hasta 3 energias Psiquicas a un Pokemon Psiquico"
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
        activo.getTipo() == TipoEnergia.PSIQUICO;
}
    @Override
    public Void Activar(Jugador jugador) {

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        if (activo != null &&
            activo.getTipo() == TipoEnergia.PSIQUICO) {

            activo.adjuntarEnergia(TipoEnergia.PSIQUICO);
            activo.adjuntarEnergia(TipoEnergia.PSIQUICO);
            activo.adjuntarEnergia(TipoEnergia.PSIQUICO);
            jugador.registrarSoporte();
        }

        return null;
    }
}