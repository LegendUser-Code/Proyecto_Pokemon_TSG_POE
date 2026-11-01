public class Blaine extends Soporte {

    public Blaine() {
        super(
            "Blaine",
            "Adjunta hasta 3 energias Fuego a un Pokemon de Fuego"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {

        if (jugador == null) {
            return false;
        }

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        return activo != null &&
            activo.getTipo() == TipoEnergia.FUEGO;
    }

    @Override
    public Void Activar(Jugador jugador) {

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        if (activo != null &&
            activo.getTipo() == TipoEnergia.FUEGO) {

            activo.adjuntarEnergia(TipoEnergia.FUEGO);
            activo.adjuntarEnergia(TipoEnergia.FUEGO);
            activo.adjuntarEnergia(TipoEnergia.FUEGO);
        }

        return null;
    }
}