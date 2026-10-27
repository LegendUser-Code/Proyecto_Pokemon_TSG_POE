import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZonaEnergia {
    
    private List<TipoEnergia> tiposPosibles;
    private TipoEnergia energiaActual;
    private Random random;

    public ZonaEnergia(List<TipoEnergia> tiposPosibles) {
        this.tiposPosibles = new ArrayList<>(tiposPosibles);
        this.energiaActual = null;
        this.random = new Random();
    }

    public void generarEnergia() {
        if(tiposPosibles == null || tiposPosibles.isEmpty()){
            System.out.println("No hay tipos de energía");
            return;
        }
        int indice = random.nextInt(tiposPosibles.size());
        this.energiaActual = this.tiposPosibles.get(indice);
        System.out.println("Energía generad para el turno " + this.energiaActual);
    }

    public boolean hayEnergiaDisponible() {
        return this.energiaActual != null;
    }

    public TipoEnergia tomarEnergia() {
        
        if(!hayEnergiaDisponible()){
            System.out.println("No hay energía disponible para este turno");
            return null;
        }
        TipoEnergia energiaTomada = this.energiaActual;
        this.energiaActual = null;
        return energiaTomada;
    }


        public List<TipoEnergia> getTiposPosibles() {
        return tiposPosibles;
    }

    public TipoEnergia getEnergiaActual() {
        return energiaActual;
    }

    public Random getRandom() {
        return random;
    }

}