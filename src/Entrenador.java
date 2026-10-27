public abstract class Entrenador extends Carta{

    private String descripcion;

    public Entrenador(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
        
    }

    public abstract Boolean puedeActivar();
    
    public abstract Void Activar();
}