public class Bill extends Soporte {

    public Bill() {
        super(
            "Bill",
            "Roba 2 cartas del mazo"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {

        return jugador != null &&
            jugador.puedeRobar();
    }

    @Override
    public Void Activar(Jugador jugador) {

        if (!puedeActivar(jugador)) {
            return null;
        }

        jugador.robarCarta();
        jugador.robarCarta();

        return null;
    }
}