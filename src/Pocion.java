public class Pocion extends Objeto {

    public Pocion() {
        super(
            "Pocion",
            "Cura 20 PS al Pokemon activo propio"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {
        return jugador != null
            && jugador.getCampo().getPokemonActivo() != null;
    }

    @Override
    public Void Activar(Jugador jugador) {

        Pokemon activo =
            jugador.getCampo().getPokemonActivo();

        if (activo != null) {
            activo.curar(20);
        }

        return null;
    }
}
