public class InvestigacionDelProfesor extends Soporte {

    public InvestigacionDelProfesor() {
        super(
            "Investigacion del Profesor",
            "Descarta tu mano actual y roba 2 cartas del mazo"
        );
    }

    @Override
    public Boolean puedeActivar() {
        return true;
    }

    @Override
    public Void Activar() {

        System.out.println(
            "Investigacion del Profesor activada"
        );

        return null;
    }

}