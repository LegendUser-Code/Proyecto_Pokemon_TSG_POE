public class App {
    public static void main(String[] args) throws Exception {
        
        Ataque ataque1 = new Ataque("Trueno", 2, TipoEnergia.ELECTRICO, 20);
        Ataque ataque2 = new Ataque("Tormenta", 3, TipoEnergia.ELECTRICO, 40);


        Ataque ataque3 = new Ataque("Ascuas", 2, TipoEnergia.FUEGO, 30);
        Ataque ataque4 = new Ataque("Volcan", 3, TipoEnergia.FUEGO, 50);

        PokemonBasico pikachu = new PokemonBasico(
            "Pikachu",
            TipoEnergia.ELECTRICO,
            (short)80,
            (short)80,
            (byte)2
        );
        pikachu.agregarAtaque(ataque1);
        pikachu.agregarAtaque(ataque2);

        PokemonBasico charmander = new PokemonBasico("Charmander",
            TipoEnergia.FUEGO,
            (short)65,
            (short)65,
            (byte)2
            );
        charmander.agregarAtaque(ataque3);
        charmander.agregarAtaque(ataque4);

        System.out.println(pikachu.getNombre());

        pikachu.atacar(ataque2, charmander);

        pikachu.adjuntarEnergia(TipoEnergia.ELECTRICO);
        pikachu.adjuntarEnergia(TipoEnergia.ELECTRICO);
        pikachu.adjuntarEnergia(TipoEnergia.ELECTRICO);

        pikachu.atacar(ataque2, charmander);
    } 
}
