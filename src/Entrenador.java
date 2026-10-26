public abstract class Entrenador extends Carta{

    private String descripcion;

    public Entrenador(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
        
    }

    public String mostrarDetalle() {
        return "Entrenador: " + getNombre() + " - " + descripcion;
    }

    public abstract boolean puedeActivar();
    
    public abstract void Activar();
}