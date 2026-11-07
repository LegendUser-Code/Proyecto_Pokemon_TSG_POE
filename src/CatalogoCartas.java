import java.util.ArrayList;
import java.util.List;

public class CatalogoCartas {

    public Mazo crearMazoPredefinido(int opcion) {
        List<TipoEnergia> tipoEnergia = new ArrayList<>();

        if (opcion == 1) {
            tipoEnergia.add(TipoEnergia.FUEGO);
            tipoEnergia.add(TipoEnergia.AGUA);
            return new Mazo(armarMazoFuegoAgua(), tipoEnergia);
        }else if (opcion == 2) {
            tipoEnergia.add(TipoEnergia.ELECTRICO);
            tipoEnergia.add(TipoEnergia.PSIQUICO);
            return new Mazo(armarMazoElectricoPsiquico(), tipoEnergia);
        }
        else if (opcion == 3) {
            return new Mazo(armarMazoCampeones(), null);
        }
        return null;
    }
    

    public List<String> getNombreMazos() {
        List<String> nombres = new ArrayList<>();
        nombres.add("Mazo Fuego/Agua");
        nombres.add("Mazo Eléctrico/Psíquico");
        nombres.add("Mazo Campeones de Gimnasio");
        return nombres;
    }

    
    private List<Carta> armarMazoFuegoAgua() {
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cartas.add(crearCharmander());
            cartas.add(crearSquirtle());
            cartas.add(crearMagikarp());
            cartas.add(crearMoltresEx());
            cartas.add(crearArticunoEx());
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
            cartas.add(crearPikachu());
            cartas.add(crearZapdosEx());
            cartas.add(crearMewtwoEx());
            cartas.add(crearJigglypuff());
            cartas.add(crearSnorlax());
            cartas.add(new InvestigacionDelProfesor());
            cartas.add(new Pocion());
            cartas.add(new PokeBall());
            cartas.add(new Misty());
            cartas.add(new TurboVelocidad());
        }
        return cartas;
    }


    private List<Carta> armarMazoCampeones() {
    List<Carta> cartas = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
        cartas.add(crearPikachu());
        cartas.add(crearCharmander());
        cartas.add(crearJigglypuff());
        cartas.add(crearMewtwoEx());
        cartas.add(crearZapdosEx());
        cartas.add(new InvestigacionDelProfesor());
        cartas.add(new Pocion());
        cartas.add(new PokeBall());
        cartas.add(new Misty());
        cartas.add(new TurboVelocidad());
    }

    return cartas;
}

private PokemonBasico crearCharmander() {

    PokemonBasico charmander =
        new PokemonBasico(
            "Charmander",
            TipoEnergia.FUEGO,
            (short)60,
            (short)60,
            (byte)1
        );

    charmander.agregarAtaque(
        new Ataque(
            "Ascuas",
            1,
            TipoEnergia.FUEGO,
            20
        )
    );

    charmander.agregarAtaque(
        new Ataque(
            "Lanzallamas",
            2,
            TipoEnergia.FUEGO,
            40
        )
    );

    return charmander;
}

private PokemonBasico crearSquirtle() {

    PokemonBasico squirtle =
        new PokemonBasico(
            "Squirtle",
            TipoEnergia.AGUA,
            (short)60,
            (short)60,
            (byte)1
        );

    squirtle.agregarAtaque(
        new Ataque(
            "Pistola Agua",
            1,
            TipoEnergia.AGUA,
            20
        )
    );

    squirtle.agregarAtaque(
        new Ataque(
            "Burbuja",
            2,
            TipoEnergia.AGUA,
            40
        )
    );

    return squirtle;
}

private PokemonBasico crearPikachu() {

    PokemonBasico pikachu =
        new PokemonBasico(
            "Pikachu",
            TipoEnergia.ELECTRICO,
            (short)60,
            (short)60,
            (byte)1
        );

    pikachu.agregarAtaque(
        new Ataque(
            "Impactrueno",
            1,
            TipoEnergia.ELECTRICO,
            20
        )
    );

    pikachu.agregarAtaque(
        new Ataque(
            "Rayo",
            2,
            TipoEnergia.ELECTRICO,
            40
        )
    );

    return pikachu;
}

private PokemonEx crearMewtwoEx() {

    PokemonEx mewtwo =
        new PokemonEx(
            "Mewtwo ex",
            TipoEnergia.PSIQUICO,
            (short)150,
            (short)150,
            (byte)1
        );

    mewtwo.agregarAtaque(
        new Ataque(
            "Confusion",
            1,
            TipoEnergia.PSIQUICO,
            30
        )
    );

    mewtwo.agregarAtaque(
        new Ataque(
            "Psicorrayo",
            3,
            TipoEnergia.PSIQUICO,
            60
        )
    );

    return mewtwo;
}

private PokemonEx crearZapdosEx() {

    PokemonEx zapdos =
        new PokemonEx(
            "Zapdos ex",
            TipoEnergia.ELECTRICO,
            (short)130,
            (short)130,
            (byte)1
        );

    zapdos.agregarAtaque(
        new Ataque(
            "Voltio Cruel",
            1,
            TipoEnergia.ELECTRICO,
            30
        )
    );

    zapdos.agregarAtaque(
        new Ataque(
            "Tormenta Electrica",
            3,
            TipoEnergia.ELECTRICO,
            70
        )
    );

    return zapdos;
}

private PokemonBasico crearMagikarp() {

    PokemonBasico magikarp =
        new PokemonBasico(
            "Magikarp",
            TipoEnergia.AGUA,
            (short)30,
            (short)30,
            (byte)1
        );

    magikarp.agregarAtaque(
        new Ataque(
            "Salpicadura",
            1,
            TipoEnergia.AGUA,
            10
        )
    );

    magikarp.agregarAtaque(
        new Ataque(
            "Empujon",
            2,
            TipoEnergia.AGUA,
            20
        )
    );

    return magikarp;
}

private PokemonBasico crearJigglypuff() {

    PokemonBasico jigglypuff =
        new PokemonBasico(
            "Jigglypuff",
            TipoEnergia.INCOLORO,
            (short)70,
            (short)70,
            (byte)1
        );

    jigglypuff.agregarAtaque(
        new Ataque(
            "Canto",
            1,
            TipoEnergia.INCOLORO,
            20
        )
    );

    jigglypuff.agregarAtaque(
        new Ataque(
            "Golpe",
            2,
            TipoEnergia.INCOLORO,
            40
        )
    );

    return jigglypuff;
}

private PokemonBasico crearSnorlax() {

    PokemonBasico snorlax =
        new PokemonBasico(
            "Snorlax",
            TipoEnergia.INCOLORO,
            (short)150,
            (short)150,
            (byte)1
        );

    snorlax.agregarAtaque(
        new Ataque(
            "Placaje",
            2,
            TipoEnergia.INCOLORO,
            30
        )
    );

    snorlax.agregarAtaque(
        new Ataque(
            "Hiperrayo",
            3,
            TipoEnergia.INCOLORO,
            60
        )
    );

    return snorlax;
}

private PokemonEx crearMoltresEx() {

    PokemonEx moltres =
        new PokemonEx(
            "Moltres ex",
            TipoEnergia.FUEGO,
            (short)130,
            (short)130,
            (byte)1
        );

    moltres.agregarAtaque(
        new Ataque(
            "Llama Ardiente",
            2,
            TipoEnergia.FUEGO,
            40
        )
    );

    moltres.agregarAtaque(
        new Ataque(
            "Tormenta de Fuego",
            3,
            TipoEnergia.FUEGO,
            70
        )
    );

    return moltres;
}

private PokemonEx crearArticunoEx() {

    PokemonEx articuno =
        new PokemonEx(
            "Articuno ex",
            TipoEnergia.AGUA,
            (short)130,
            (short)130,
            (byte)1
        );

    articuno.agregarAtaque(
        new Ataque(
            "Viento Helado",
            2,
            TipoEnergia.AGUA,
            40
        )
    );

    articuno.agregarAtaque(
        new Ataque(
            "Ventisca",
            3,
            TipoEnergia.AGUA,
            70
        )
    );

    return articuno;
}

}
