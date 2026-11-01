public class TenienteSurge extends Soporte {

    public TenienteSurge() {
        super(
            "Teniente Surge",
            "Adjunta hasta 3 energias Electricas a un Pokemon Electrico"
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
            activo.getTipo() == TipoEnergia.ELECTRICO;
    }

    @Override
    public Void Activar(Jugador jugador) {

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        if (activo != null &&
            activo.getTipo() == TipoEnergia.ELECTRICO) {

            activo.adjuntarEnergia(TipoEnergia.ELECTRICO);
            activo.adjuntarEnergia(TipoEnergia.ELECTRICO);
            activo.adjuntarEnergia(TipoEnergia.ELECTRICO);

            System.out.println(
                "Teniente Surge adjunto 3 energias Electricas"
            );
        }

        return null;
    }
}