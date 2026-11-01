import java.util.List;

public class PokeBall extends Objeto {

    public PokeBall() {
        super(
            "Poke Ball",
            "Busca un Pokemon basico y lo agrega a la mano"
        );
    }

    @Override
    public Boolean puedeActivar(Jugador jugador) {
        return jugador != null;
    }

@Override
public Void Activar(Jugador jugador) {

    if (jugador == null) {
        return null;
    }

    List<Carta> cartas =
        jugador.getMazo().getCartas();

    for (int i = 0; i < cartas.size(); i++) {

        Carta carta = cartas.get(i);

        if (carta.esPokemonBasico()) {

            jugador.getMano().add(carta);

            cartas.remove(i);

            System.out.println(
                "Poke Ball agrego "
                + carta.getNombre()
                + " a la mano"
            );

            return null;
        }
    }

    System.out.println(
        "No se encontro Pokemon basico"
    );

    return null;
}
}