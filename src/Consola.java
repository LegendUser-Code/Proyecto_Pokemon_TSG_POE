import java.util.List;
import java.util.Scanner;

public class Consola {

    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombreJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        return this.scanner.nextLine().trim();
    }

    public int elegirMazo(String nombreJugador) {
        CatalogoCartas catalogo = new CatalogoCartas();
        List<String> mazos = catalogo.getNombreMazos();
        System.out.println(nombreJugador + ", elige tu mazo:");
        for (int i = 0; i < mazos.size(); i++) {
            System.out.println((i + 1) + ". " + mazos.get(i));
        }
        int opcion;
        do {
            opcion = leerEntero("Opcion: ");
        } while (opcion < 1 || opcion > mazos.size());
        return opcion;
    }

    public void mostrarEstado(Jugador jugadorActual, Jugador rival, int turno) {
        String igual = linea('=', 70);
        String guion = linea('-', 70);

        System.out.println(igual);
        System.out.println(" TURNO " + turno + " | RIVAL: " + rival.getNombre()
            + "  vs  TU CAMPO: " + jugadorActual.getNombre());
        System.out.println(igual);
        System.out.println();

        System.out.println(" [ CAMPO RIVAL ]");
        System.out.println(guion);
        imprimirSeccionCampo(rival);
        System.out.println(guion);
        System.out.println();

        System.out.println(" [ TU CAMPO ]");
        System.out.println(guion);
        imprimirSeccionCampo(jugadorActual);
        System.out.println(guion);
        System.out.println();

        System.out.println(" ZONA DE ENERGIA DISPONIBLE: " + energiaDisponible(jugadorActual));
        System.out.println();

        List<Carta> mano = jugadorActual.getMano();
        System.out.println(" CARTAS EN MANO (" + mano.size() + "):");
        System.out.println("  " + textoMano(mano));
        System.out.println();
    }

    private void imprimirSeccionCampo(Jugador j) {
        Pokemon activo = j.getCampo().getPokemonActivo();
        if (activo == null) {
            System.out.println("  ACTIVO:   [Sin Pokemon activo]");
        } else {
            System.out.println("  ACTIVO:   " + activo.getNombre()
                + " | PS: " + activo.getPsActual() + "/" + activo.getPsMaximo()
                + " | Energia: " + textoEnergias(activo.getEnergias()));
        }
        imprimirBanca(j.getCampo().getBanco());
        System.out.println("  PREMIOS:  " + j.getPuntosPremio() + " / 3");
    }

    // La banca siempre muestra 2 espacios; los vacios como [Espacio disponible].
    private void imprimirBanca(List<Pokemon> banco) {
        for (int i = 0; i < 2; i++) {
            String prefijo = (i == 0) ? "  BANCA:    " : "            ";
            if (i < banco.size()) {
                Pokemon p = banco.get(i);
                String base = p.getNombre() + " (" + p.getPsActual() + "/" + p.getPsMaximo() + " PS)";
                while (base.length() < 24) {
                    base += " ";
                }
                System.out.println(prefijo + (i + 1) + ". " + base + "["
                    + textoEnergias(p.getEnergias()) + "]");
            } else {
                System.out.println(prefijo + (i + 1) + ". [Espacio disponible]");
            }
        }
    }

    private String textoMano(List<Carta> mano) {
        String texto = "";
        for (int i = 0; i < mano.size(); i++) {
            if (i > 0) {
                texto += "    ";
            }
            texto += "[" + (i + 1) + "] " + mano.get(i).getNombre();
        }
        return texto;
    }

    private String energiaDisponible(Jugador j) {
        ZonaEnergia zona = j.getZonaEnergia();
        if (zona.hayEnergiaDisponible()) {
            return zona.getEnergiaActual().toString();
        }
        return "SIN ENERGIA";
    }

    // Muestra el tipo y la cantidad de cada energia adjunta.
    // Si el Pokemon no tiene energias, devuelve "Sin energia".
    private String textoEnergias(List<TipoEnergia> energias) {
        if (energias == null || energias.isEmpty()) {
            return "Sin energia";
        }
        int fuego = 0;
        int agua = 0;
        int electrico = 0;
        int psiquico = 0;
        int incoloro = 0;
        for (TipoEnergia e : energias) {
            switch (e) {
                case FUEGO: fuego++; break;
                case AGUA: agua++; break;
                case ELECTRICO: electrico++; break;
                case PSIQUICO: psiquico++; break;
                case INCOLORO: incoloro++; break;
            }
        }
        String texto = "";
        if (fuego > 0) texto += "Fuego x" + fuego + " ";
        if (agua > 0) texto += "Agua x" + agua + " ";
        if (electrico > 0) texto += "Electrico x" + electrico + " ";
        if (psiquico > 0) texto += "Psiquico x" + psiquico + " ";
        if (incoloro > 0) texto += "Incoloro x" + incoloro + " ";
        return texto.trim();
    }

    // Devuelve el nombre del tipo de energia en formato bonito.
    private String nombreTipo(TipoEnergia t) {
        switch (t) {
            case FUEGO: return "Fuego";
            case AGUA: return "Agua";
            case ELECTRICO: return "Electrico";
            case PSIQUICO: return "Psiquico";
            case INCOLORO: return "Incoloro";
            default: return t.toString();
        }
    }

    public int pedirAccion() {
        String igual = linea('=', 70);
        String guion = linea('-', 70);
        System.out.println(igual);
        System.out.println(" OPCIONES DE TURNO:");
        System.out.println(igual);
        System.out.println("  (1) Adjuntar energia a un Pokemon");
        System.out.println("  (2) Jugar carta Entrenador");
        System.out.println("  (3) Colocar Pokemon en la banca");
        System.out.println("  (4) Retirar Pokemon activo");
        System.out.println("  (5) Atacar");
        System.out.println("  (6) Pasar turno");
        System.out.println(guion);
        int opcion;
        do {
            opcion = leerEntero("Opcion: ");
        } while (opcion < 1 || opcion > 6);
        return opcion;
    }

    public int elegirEntrenador() {
        return leerEntero("Elija una carta de entrenador (numero): ");
    }

    public int elegirAtaque() {
        return leerEntero("Elija un ataque (numero): ");
    }

    public int elegirPokemon() {
        return leerEntero("Elija un Pokemon (numero): ");
    }

    // Menu bonito para elegir el Pokemon activo al inicio de la partida.
    public int elegirPokemonActivo(List<Carta> basicos) {
        String igual = linea('=', 70);
        String guion = linea('-', 70);
        System.out.println(igual);
        System.out.println(" ELIGE TU POKEMON ACTIVO");
        System.out.println(igual);
        for (int i = 0; i < basicos.size(); i++) {
            Carta c = basicos.get(i);
            if (c instanceof Pokemon) {
                Pokemon p = (Pokemon) c;
                System.out.println("  (" + (i + 1) + ") " + p.getNombre()
                    + " | PS: " + p.getPsActual() + "/" + p.getPsMaximo()
                    + " | Tipo: " + nombreTipo(p.getTipo()));
            }
        }
        System.out.println(guion);
        int opcion;
        do {
            opcion = leerEntero("Opcion: ");
        } while (opcion < 1 || opcion > basicos.size());
        return opcion;
    }

    // Menu bonito para colocar un Pokémon adicional en la banca.
    // Devuelve 0 si no quiere colocar más, o 1..n para elegir uno.
    public int elegirPokemonBanca(List<Carta> restantes) {
        String igual = linea('=', 70);
        String guion = linea('-', 70);
        System.out.println(igual);
        System.out.println(" COLOCAR POKEMON EN LA BANCA (opcional, max 2)");
        System.out.println(igual);
        System.out.println("  (0) No colocar mas");
        for (int i = 0; i < restantes.size(); i++) {
            Carta c = restantes.get(i);
            if (c instanceof Pokemon) {
                Pokemon p = (Pokemon) c;
                System.out.println("  (" + (i + 1) + ") " + p.getNombre()
                    + " | PS: " + p.getPsActual() + "/" + p.getPsMaximo()
                    + " | Tipo: " + nombreTipo(p.getTipo()));
            }
        }
        System.out.println(guion);
        int opcion;
        do {
            opcion = leerEntero("Opcion: ");
        } while (opcion < 0 || opcion > restantes.size());
        return opcion;
    }

    public void anunciarGanador(String nombre) {
        String igual = linea('=', 70);
        System.out.println(igual);
        System.out.println("  ¡" + nombre + " ha ganado el duelo Pokemon!");
        System.out.println("  ¡Eres el nuevo Campeon!");
        System.out.println(igual);
    }

    // Genera una linea de 'n' caracteres repetidos (para los bordes).
    private String linea(char c, int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += c;
        }
        return s;
    }

    // Lee un entero por teclado. Si el usuario ingresa letras, las descarta
    // y vuelve a pedir el dato para que el programa no se caiga.
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!this.scanner.hasNextInt()) {
            this.scanner.next();
            System.out.print("Ingrese un numero valido: ");
        }
        int valor = this.scanner.nextInt();
        this.scanner.nextLine();
        return valor;
    }
}
