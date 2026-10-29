import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mazo {

    public static final int TAMANO = 20;      
    public static final int MAX_COPIAS = 2;  
    public static final int MIN_BASICOS = 6; 
    public static final int MANO_INICIAL = 5; 

    private final List<Carta> cartas;


    public Mazo(List<Carta> cartas) {
        if (cartas == null) {
            throw new IllegalArgumentException("La lista de cartas no puede ser null");
        }
        if (!esValido(cartas)) {
            throw new IllegalArgumentException("Mazo inválido: se requieren " + TAMANO
                + " cartas, máximo " + MAX_COPIAS + " copias por carta y mínimo "
                + MIN_BASICOS + " Pokémon básicos");
        }
        this.cartas = new ArrayList<>(cartas); 
    }

  
    public static boolean esValido(List<Carta> cartas) {
        if (cartas == null || cartas.size() != TAMANO) {
            return false;
        }
        var copias = new HashMap<String, Integer>();
        int basicos = 0;
        for (Carta c : cartas) {
            if (c == null) {
                return false;
            }
            if (copias.merge(c.getNombre(), 1, Integer::sum) > MAX_COPIAS) {
                return false;
            }
            if (c.esPokemonBasico()) {
                basicos++;
            }
        }
        return basicos >= MIN_BASICOS;
    }

    public void barajar() {
        Collections.shuffle(this.cartas);
    }

   
    public List<Carta> robarManoInicial() {
        var mano = new ArrayList<Carta>();
        do {
            this.cartas.addAll(mano); 
            mano.clear();
            barajar();
            for (int i = 0; i < MANO_INICIAL; i++) {
                mano.add(robar());
            }
        } while (!tieneBasico(mano));
        return mano;
    }

    private static boolean tieneBasico(List<Carta> mano) {
        for (Carta c : mano) {
            if (c.esPokemonBasico()) {
                return true;
            }
        }
        return false;
    }

    
    public Carta robar() {
        return this.cartas.isEmpty() ? null : this.cartas.removeLast(); 
    }

    public int getCantidadCartas() {
        return this.cartas.size();
    }
}
