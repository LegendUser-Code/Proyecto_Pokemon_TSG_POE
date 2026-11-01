public class TurboVelocidad extends Objeto {

    public TurboVelocidad() {
        super(
            "Turbo Velocidad",
            "Reduce en 1 el costo de retirada del Pokemon activo"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {

        return jugador != null &&
               jugador.getCampo().getPokemonActivo() != null;
    }

    @Override
    public Void Activar(Jugador jugador) {

        if (!puedeActivar(jugador)) {
            return null;
        }

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        byte costoActual =
            activo.getCostoRetirada();

        if (costoActual > 0) {

            activo.setCostoRetirada(
                (byte)(costoActual - 1)
            );

            System.out.println(
                "Turbo Velocidad redujo el costo de retirada de "
                + activo.getNombre()
            );
        }

        return null;
    }
}