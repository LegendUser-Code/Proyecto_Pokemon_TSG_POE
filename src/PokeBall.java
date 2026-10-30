public class PokeBall extends Objeto {

    public PokeBall() {
        super(
            "Poke Ball",
            "Busca un Pokemon basico y lo agrega a la mano"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        System.out.println(
            "Poke Ball activada"
        );

        return null;
    }
}
