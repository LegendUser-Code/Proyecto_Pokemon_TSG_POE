public abstract class Carta {
    
    private String nombre;

    public Carta(String nombre) {
        this.nombre = nombre;
    }

    public Boolean esPokemonBasico() {
        return false;
    }
    public String getNombre() {
        return nombre;
    }
    

}