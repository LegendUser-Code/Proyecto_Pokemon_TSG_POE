public class InvestigacionDelProfesor extends Soporte {

    public InvestigacionDelProfesor() {
        super(
            "Investigación del Profesor",
            "Descarta tu mano y roba 2 cartas"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        return null;
    }
}