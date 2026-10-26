public class PokemonBasico extends Pokemon{

    public PokemonBasico(String nombre, TipoEnergia tipo, short psMaximo, short psActual, 
        byte costoRetirada) {
        super(nombre, tipo, psMaximo, psActual, costoRetirada);
    }

    @Override
    public byte getPuntosPremio() {
        return 1;
    }
    
}
