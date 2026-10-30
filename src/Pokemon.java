import java.util.ArrayList;
import java.util.List;

public abstract class Pokemon extends Carta{

    private TipoEnergia tipo;
    private short psMaximo, psActual;
    private byte costoRetirada;
    private List<Ataque> ataques;
    private List<TipoEnergia> energias;

    public Pokemon(String nombre, TipoEnergia tipo, short psMaximo, short psActual, byte costoRetirada) {
        super(nombre);
        this.tipo = tipo;
        this.psMaximo = psMaximo;
        this.psActual = psActual;
        this.costoRetirada = costoRetirada;
        this.ataques = new ArrayList<>();
        this.energias = new ArrayList<>();
    }

    public void adjuntarEnergia(TipoEnergia energia){
        if(energia != null){
            this.energias.add(energia);
            System.out.println("Se adjunto energia de tipo " + energia + " a " + getNombre());
        }
    }

    public void agregarAtaque(Ataque ataque){
        if(ataque == null){
            return;
        }

        if(this.ataques.size() >= 2){
            System.out.println("No se pueden agregar más de 2 ataques");
            return;
        }
        
        this.ataques.add(ataque);
    }

    public boolean puedeAtacar(Ataque ataque) {
        if (ataque == null || this.energias.isEmpty()) {
            return false;
        }

        TipoEnergia energiaRequerida = ataque.getTipoDeEnergia();

        if(energiaRequerida == TipoEnergia.INCOLORO){
            return energias.size() >= ataque.getCostoEnergia();
        }

        int cantidadEnergia = 0;

        for(TipoEnergia energia : this.energias){
            if(energia == energiaRequerida){
                cantidadEnergia ++;
            }
        }

        return cantidadEnergia >= ataque.getCostoEnergia();
        
    }

    public void atacar(Ataque ataque, Pokemon pokemonAtacado){
        if(ataque == null || pokemonAtacado == null){
            System.out.println("Ataque u objeto no válido");
            return;
        }

        if(!puedeAtacar(ataque)){
            System.out.println(getNombre() + " no tiene suficiente energia para usar " + ataque.getNombre());
            return;
        }

        int danio = ataque.getDanioBase();

        if(pokemonAtacado.getTipo().esDebilContra(getTipo())){
            danio += 20;
            System.out.println("El tipo de elemento de " + pokemonAtacado.getNombre() +
            " es debil contra el tipo de" + getNombre());
        }

        pokemonAtacado.recibirDaño(danio);

        System.out.println(pokemonAtacado.getNombre() + " fue atacado por " + getNombre() + 
        " usando el ataque " + ataque.getNombre());
    }

    public void recibirDaño(int cantidadDanio) {
        if(cantidadDanio <= 0) {
            return;
        }
        
        this.psActual -= cantidadDanio;

        if(this.psActual <= 0){
            this.psActual = 0;
        }

        System.out.println(getNombre() + " recibio " + cantidadDanio + 
        " puntos de daño. (ps restantes " + psActual + "/" + psMaximo + ")");
    }

    public void curar(int cantidadCuracion){
        if(cantidadCuracion <= 0){
            return;
        }

        this.psActual += cantidadCuracion;

        if(this.psActual >= this.psMaximo){
            this.psActual = this.psMaximo;
        }

        System.out.println(getNombre() + " ha recibido curacion (" + this.psActual +
        "/" + this.psMaximo + ")");
    }

    public boolean estaDerrotado(){
        return this.psActual <= 0;
    }

    public TipoEnergia getTipo() {
        return tipo;
    }
    public short getPsMaximo() {
        return psMaximo;
    }
    public short getPsActual() {
        return psActual;
    }
    public byte getCostoRetirada() {
        return costoRetirada;
    }
    public List<Ataque> getAtaques() {
        return ataques;
    }
    public List<TipoEnergia> getEnergias() {
        return energias;
    }
    
    public abstract byte getPuntosPremio();
    
}