public class Misty extends Soporte {

    public Misty() {
        super(
            "Misty",
            "Adjunta hasta 3 energias Agua a un Pokemon de Agua"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        System.out.println(
            "Misty activada"
        );

        return null;
    }

}