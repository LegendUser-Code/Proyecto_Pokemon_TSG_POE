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
        System.out.println("TURNO " + turno + " - " + jugadorActual.getNombre());
        System.out.println();

        System.out.println("RIVAL (" + rival.getNombre() + ")");
        imprimirCampo(rival, "rival");
        System.out.println();

        System.out.println("TU CAMPO (" + jugadorActual.getNombre() + ")");
        imprimirCampo(jugadorActual, "propios");
        System.out.println();

        System.out.println("Zona de energía: " + energiaDisponible(jugadorActual));
        System.out.println();

        List<Carta> mano = jugadorActual.getMano();
        System.out.println("Tu mano (" + mano.size() + " cartas):");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i + 1) + ". " + mano.get(i).getNombre());
        }
    }

    private void imprimirCampo(Jugador j, String etiquetaPremio) {
        Pokemon activo = j.getCampo().getPokemonActivo();
        if (activo == null) {
            System.out.println("PS: -/-");
            System.out.println("[Activo] -     Energía adjunta: 0");
        } else {
            System.out.println("PS: " + activo.getPsActual() + "/" + activo.getPsMaximo());
            System.out.println("[Activo] " + activo.getNombre() + "     Energía adjunta: " + activo.getEnergias().size());
        }
        System.out.println("[Banco] " + textoBanco(j.getCampo().getBanco()));
        System.out.println("Puntos de premio " + etiquetaPremio + ": " + j.getPuntosPremio() + "/3");
    }

    private String textoBanco(List<Pokemon> banco) {
        if (banco == null || banco.isEmpty()) {
            return "(vacio)";
        }
        String texto = "";
        for (int i = 0; i < banco.size(); i++) {
            Pokemon p = banco.get(i);
            if (i > 0) {
                texto += " | ";
            }
            texto += p.getNombre() + " (" + p.getPsActual() + ")";
        }
        return texto;
    }

    private String energiaDisponible(Jugador j) {
        ZonaEnergia zona = j.getZonaEnergia();
        if (zona.hayEnergiaDisponible()) {
            return zona.getEnergiaActual().toString();
        }
        return "sin energia";
    }

    public int pedirAccion() {
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Adjuntar energía de zona");
        System.out.println("2. Jugar carta de entrenador");
        System.out.println("3. Colocar Pokémon en banco");
        System.out.println("4. Retirar Pokémon activo");
        System.out.println("5. Atacar");
        System.out.println("6. Pasar turno");
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

    public void anunciarGanador(String nombre) {
        System.out.println("¡" + nombre + " ha ganado el duelo Pokemon! ¡Eres el nuevo Campeon!");
    }

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
