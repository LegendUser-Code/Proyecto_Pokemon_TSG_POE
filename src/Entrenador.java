public abstract class Entrenador extends Carta {

    private String descripcion;

    public Entrenador(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public abstract Boolean puedeActivar(Jugador jugador);

    public abstract Void Activar(Jugador jugador);
}