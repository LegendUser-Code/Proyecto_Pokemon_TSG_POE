public class TurboVelocidad extends Objeto {

    public TurboVelocidad() {
        super(
            "Turbo Velocidad",
            "Reduce en 1 el costo de retirada"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {
        return jugador != null;
    }

    @Override
    public Void Activar(Jugador jugador) {

        System.out.println(
            "Turbo Velocidad usada"
        );

        return null;
    }
}