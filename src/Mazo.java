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

    private List<Carta> composicion;
    private List<Carta> cartas;

    public Mazo(List<Carta> cartas) {
        if (cartas == null || !cumpleReglas(cartas)) {
            System.out.println("Mazo invalido: se requieren " + TAMANO + " cartas, maximo "
                + MAX_COPIAS + " copias por carta y minimo " + MIN_BASICOS + " basicos");
        }
        List<Carta> base = cartas == null ? new ArrayList<>() : cartas;
        this.composicion = new ArrayList<>(base);
        this.cartas = new ArrayList<>(base);
    }

    public boolean esValido() {
        return cumpleReglas(this.composicion);
    }

    private static boolean cumpleReglas(List<Carta> lista) {
        if (lista.size() != TAMANO) {
            return false;
        }
        Map<String, Integer> copias = new HashMap<>();
        int basicos = 0;
        for (Carta c : lista) {
            if (c == null) {
                return false;
            }
            int cantidad = copias.getOrDefault(c.getNombre(), 0) + 1;
            if (cantidad > MAX_COPIAS) {
                return false;
            }
            copias.put(c.getNombre(), cantidad);
            if (c.esPokemonBasico()) {
                basicos++;
            }
        }
        return basicos >= MIN_BASICOS;
    }

    public void barajar() {
        Collections.shuffle(this.cartas);
    }

    // Roba la mano inicial de 5 cartas. Si no sale ningun basico hace mulligan.
    public List<Carta> robarManoInicial() {
        if (this.cartas.size() != TAMANO) {
            System.out.println("El mazo no esta completo");
            return new ArrayList<>();
        }
        List<Carta> mano = new ArrayList<>();
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

    private boolean tieneBasico(List<Carta> mano) {
        for (Carta c : mano) {
            if (c.esPokemonBasico()) {
                return true;
            }
        }
        return false;
    }

    public Carta robar() {
        if (this.cartas.isEmpty()) {
            return null;
        }
        return this.cartas.remove(this.cartas.size() - 1);
    }

    public int getCantidadCartas() {
        return this.cartas.size();
    }
}
