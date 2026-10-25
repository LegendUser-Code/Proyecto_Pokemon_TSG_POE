public class Ataque {
    
    private String nombre;
    private int costoEnergia;
    private TipoEnergia tipoDeEnergia;
    private int danioBase;

    

    public Ataque(String nombre, int costoEnergia, TipoEnergia tipoDeEnergia, int danioBase) {
        this.nombre = nombre;
        this.costoEnergia = costoEnergia;
        this.tipoDeEnergia = tipoDeEnergia;
        this.danioBase = danioBase;
    }
    
    public String getNombre() {
        return nombre;
    }
    public int getCostoEnergia() {
        return costoEnergia;
    }
    public TipoEnergia getTipoDeEnergia() {
        return tipoDeEnergia;
    }
    public int getDanioBase() {
        return danioBase;
    }
    
}
