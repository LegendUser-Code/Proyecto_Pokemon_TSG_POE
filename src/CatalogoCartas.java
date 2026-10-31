import java.util.ArrayList;
import java.util.List;

public class CatalogoCartas {

    public Mazo crearMazoPredefinido(int opcion) {
        if (opcion == 1) {
            return new Mazo(armarMazoFuegoAgua());
        } else if (opcion == 2) {
            return new Mazo(armarMazoElectricoPsiquico());
        }
        return null;
    }

    public List<String> getNombreMazos() {
        List<String> nombres = new ArrayList<>();
        nombres.add("Mazo Fuego/Agua");
        nombres.add("Mazo Eléctrico/Psíquico");
        return nombres;
    }

    // 20 cartas: 10 Pokémon (5 tipos x 2 copias) + 10 entrenadores (5 tipos x 2 copias).
    // Se añaden como Carta (polimorfismo).
    private List<Carta> armarMazoFuegoAgua() {
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cartas.add(new PokemonBasico("Charmander", TipoEnergia.FUEGO, (short) 60, (short) 60, (byte) 1));
            cartas.add(new PokemonBasico("Squirtle", TipoEnergia.AGUA, (short) 60, (short) 60, (byte) 1));
            cartas.add(new PokemonBasico("Magikarp", TipoEnergia.AGUA, (short) 30, (short) 30, (byte) 1));
            cartas.add(new PokemonEx("Moltres ex", TipoEnergia.FUEGO, (short) 130, (short) 130, (byte) 1));
            cartas.add(new PokemonEx("Articuno ex", TipoEnergia.AGUA, (short) 130, (short) 130, (byte) 1));
            cartas.add(new InvestigacionDelProfesor());
            cartas.add(new Pocion());
            cartas.add(new PokeBall());
            cartas.add(new Misty());
            cartas.add(new TurboVelocidad());
        }
        return cartas;
    }

    private List<Carta> armarMazoElectricoPsiquico() {
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cartas.add(new PokemonBasico("Pikachu", TipoEnergia.ELECTRICO, (short) 60, (short) 60, (byte) 1));
            cartas.add(new PokemonEx("Zapdos ex", TipoEnergia.ELECTRICO, (short) 130, (short) 130, (byte) 1));
            cartas.add(new PokemonEx("Mewtwo ex", TipoEnergia.PSIQUICO, (short) 150, (short) 150, (byte) 1));
            cartas.add(new PokemonBasico("Jigglypuff", TipoEnergia.INCOLORO, (short) 70, (short) 70, (byte) 1));
            cartas.add(new PokemonBasico("Snorlax", TipoEnergia.INCOLORO, (short) 150, (short) 150, (byte) 1));
            cartas.add(new InvestigacionDelProfesor());
            cartas.add(new Pocion());
            cartas.add(new PokeBall());
            cartas.add(new Misty());
            cartas.add(new TurboVelocidad());
        }
        return cartas;
    }
}
