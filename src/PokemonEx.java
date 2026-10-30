public class PokemonEx extends Pokemon{

    public PokemonEx(String nombre, TipoEnergia tipo, short psMaximo, short psActual,
        byte costoRetirada) {
        super(nombre, tipo, psMaximo, psActual, costoRetirada);
    }

    @Override
    public byte getPuntosPremio() {
        return 2;
    }

    @Override
    public Boolean esPokemonBasico() {
        return true; // los pokemon-ex tambien son basicos
    }

}
