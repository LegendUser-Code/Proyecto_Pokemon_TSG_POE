public class TurboVelocidad extends Objeto {

    public TurboVelocidad() {
        super(
            "Turbo Velocidad",
            "Reduce en 1 el costo de retirada del Pokemon activo"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        System.out.println(
            "Turbo Velocidad activada"
        );

        return null;
    }
}