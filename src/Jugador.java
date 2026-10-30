import java.util.ArrayList;
import java.util.List;

public class Jugador {

    public static final int PUNTOS_PARA_GANAR = 3;

    private String nombre;
    private Mazo mazo;
    private CampoDeBatalla campo;
    private ZonaEnergia zonaEnergia;
    private List<Carta> mano;
    private int puntosPremio;

    private boolean puedeAtacarEsteTurno;
    private boolean yaAtacoEsteTurno;
    private boolean soporteUsadoEsteTurno;

    public Jugador(String nombre, Mazo mazo, ZonaEnergia zonaEnergia) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre invalido");
        }
        if (mazo == null || zonaEnergia == null) {
            throw new IllegalArgumentException("Faltan el mazo o la zona de energia");
        }
        this.nombre = nombre;
        this.mazo = mazo;
        this.zonaEnergia = zonaEnergia;
        this.campo = new CampoDeBatalla();
        this.mano = new ArrayList<>();
        this.puntosPremio = 0;
        this.puedeAtacarEsteTurno = true;
    }

    // Inicia el turno. Pasar false para el primer turno del primer jugador,
    // que no puede atacar.
    public void iniciarTurno(boolean puedeAtacar) {
        this.puedeAtacarEsteTurno = puedeAtacar;
        this.yaAtacoEsteTurno = false;
        this.soporteUsadoEsteTurno = false;
    }

    public void iniciarTurno() {
        iniciarTurno(true);
    }

    // Robar cartas
    public Carta robarCarta() {
        Carta carta = this.mazo.robar();
        if (carta == null) {
            return null;
        }
        this.mano.add(carta);
        return carta;
    }

    public void robarManoInicial() {
        this.mano.clear();
        this.mano.addAll(this.mazo.robarManoInicial());
    }

    public boolean puedeRobar() {
        return this.mazo.getCantidadCartas() > 0;
    }

    // Energia
    public void generarEnergiaTurno() {
        this.zonaEnergia.generarEnergia();
    }

    public boolean adjuntarEnergia() {
        return adjuntarEnergia(this.campo.getPokemonActivo());
    }

    public boolean adjuntarEnergia(Pokemon objetivo) {
        if (!this.zonaEnergia.hayEnergiaDisponible()) {
            return false;
        }
        if (!esPokemonPropio(objetivo)) {
            return false;
        }
        TipoEnergia energia = this.zonaEnergia.tomarEnergia();
        if (energia == null) {
            return false;
        }
        objetivo.adjuntarEnergia(energia);
        return true;
    }

    // Colocar un pokemon basico de la mano al campo (activo o banco)
    public boolean colocarPokemonEnJuego(Carta carta) {
        if (carta == null || !this.mano.contains(carta)) {
            return false;
        }
        if (!(carta instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) carta;
        if (!pokemon.esPokemonBasico()) {
            return false;
        }
        if (this.campo.getPokemonActivo() == null) {
            this.campo.colocarActivo(pokemon);
        } else if (this.campo.getBanco().size() < 2) {
            this.campo.agregarABanco(pokemon);
        } else {
            return false;
        }
        this.mano.remove(carta);
        return true;
    }

    // Retira al activo pagando su costo de retirada y promueve a uno del banco
    public boolean retirarPokemonActivo(Pokemon nuevoActivo) {
        Pokemon activo = this.campo.getPokemonActivo();
        if (activo == null || nuevoActivo == null) {
            return false;
        }
        if (!this.campo.getBanco().contains(nuevoActivo)) {
            return false;
        }
        int costo = activo.getCostoRetirada();
        List<TipoEnergia> energias = activo.getEnergias();
        if (energias.size() < costo) {
            return false;
        }
        for (int i = 0; i < costo; i++) {
            energias.remove(energias.size() - 1);
        }
        this.campo.getBanco().remove(nuevoActivo);
        this.campo.colocarActivo(nuevoActivo);
        this.campo.agregarABanco(activo);
        return true;
    }

    // Atacar al oponente, una sola vez por turno
    public boolean atacar(Ataque ataque, Jugador oponente) {
        if (!this.puedeAtacarEsteTurno || this.yaAtacoEsteTurno) {
            return false;
        }
        Pokemon activo = this.campo.getPokemonActivo();
        Pokemon objetivo = oponente.getCampo().getPokemonActivo();
        if (activo == null || objetivo == null) {
            return false;
        }
        if (!activo.puedeAtacar(ataque)) {
            return false;
        }
        activo.atacar(ataque, objetivo);
        this.yaAtacoEsteTurno = true;

        if (objetivo.estaDerrotado()) {
            ganarPuntoPremio(objetivo.getPuntosPremio());
            oponente.getCampo().retirarActivo();
            oponente.promoverDesdeBanco();
        }
        return true;
    }

    // Sube el primer pokemon del banco a activo. Si no hay devuelve false.
    public boolean promoverDesdeBanco() {
        if (this.campo.getPokemonActivo() != null) {
            return true;
        }
        if (this.campo.getBanco().isEmpty()) {
            return false;
        }
        Pokemon promovido = this.campo.getBanco().remove(0);
        this.campo.colocarActivo(promovido);
        return true;
    }

    // Soportes: maximo 1 por turno
    public boolean puedeJugarSoporte() {
        return !this.soporteUsadoEsteTurno;
    }

    public void registrarSoporte() {
        this.soporteUsadoEsteTurno = true;
    }

    // Hay pokemon en juego si tiene activo o algo en el banco
    public boolean tienePokemonEnJuego() {
        return this.campo.getPokemonActivo() != null || !this.campo.getBanco().isEmpty();
    }

    public void ganarPuntoPremio(int puntos) {
        if (puntos > 0) {
            this.puntosPremio += puntos;
        }
    }

    public boolean gano() {
        return this.puntosPremio >= PUNTOS_PARA_GANAR;
    }

    private boolean esPokemonPropio(Pokemon pokemon) {
        if (pokemon == null) {
            return false;
        }
        if (pokemon == this.campo.getPokemonActivo()) {
            return true;
        }
        return this.campo.getBanco().contains(pokemon);
    }

    public String getNombre() {
        return this.nombre;
    }

    public Mazo getMazo() {
        return this.mazo;
    }

    public CampoDeBatalla getCampo() {
        return this.campo;
    }

    public ZonaEnergia getZonaEnergia() {
        return this.zonaEnergia;
    }

    public List<Carta> getMano() {
        return this.mano;
    }

    public int getPuntosPremio() {
        return this.puntosPremio;
    }

    public boolean yaAtacoEsteTurno() {
        return this.yaAtacoEsteTurno;
    }
}
