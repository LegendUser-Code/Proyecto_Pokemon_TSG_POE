public class PokemonEx extends Pokemon{

    public PokemonEx(String nombre, TipoEnergia tipo, short psMaximo, short psActual,
        byte costoRetirada) {
        super(nombre, tipo, psMaximo, psActual, costoRetirada);
    }

    @Override
    public byte getPuntosPremio() {
        return 2;
    }
    
}
