public enum TipoEnergia{
    FUEGO,
    AGUA,
    ELECTRICO,
    INCOLORO,
    PSIQUICO;

    public boolean esDebilContra(TipoEnergia atacante) {
        TipoEnergia miTipoEnergia = this;
        switch(miTipoEnergia){
            case FUEGO:
                 return atacante == AGUA;

            case AGUA:
                return atacante == ELECTRICO;

            case ELECTRICO:
                return atacante == PSIQUICO;

            case PSIQUICO:
                return atacante == FUEGO;

            case INCOLORO:
                return false;        // no tiene debilidad

            default:
                return false;
        }
    }
}