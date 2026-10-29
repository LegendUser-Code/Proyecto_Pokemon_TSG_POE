public enum TipoEnergia {
    FUEGO,
    AGUA,
    ELECTRICO,
    INCOLORO,
    PSIQUICO;

    public boolean esDebilContra(TipoEnergia atacante) {
        TipoEnergia miTipoEnergia = this;
        switch (miTipoEnergia) {
            case FUEGO:
                return atacante == AGUA;
            case AGUA:
                return atacante == FUEGO || atacante == ELECTRICO;
            case ELECTRICO:
                return false;
            case PSIQUICO:
                return false;
            case INCOLORO:
                return atacante == PSIQUICO;
            default:
                return false;
        }
    }
}
