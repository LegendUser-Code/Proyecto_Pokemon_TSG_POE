public class Pocion extends Objeto {

    public Pocion() {
        super(
            "Pocion",
            "Cura 20 PS al Pokemon activo propio"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        System.out.println(
            "Pocion activada"
        );

        return null;
    }
}