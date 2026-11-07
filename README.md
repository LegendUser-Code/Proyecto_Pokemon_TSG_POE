# Proyecto_Pokemon_TSG_POE

Integrantes del equipo:

Samuel Mendoza Padilla 2569299 - 3743 
GitHub: LegendUser-Code

Juan José Bonilla moya - 2569254 - 3743 
GitHub: juanjosebonilla

Schneider Álvarez Gil - 2569328 - 3743
GitHub: schneider-AG

DESCRIPCIÓN DEL PROYECTO:
Pokémon Pocket TCG es una simulación por consola inspirada en Pokémon TCG Pocket, donde dos jugadores se enfrentan utilizando mazos de cartas compuestos por Pokémon, cartas de entrenador y energías.


CARACTERISTICAS IMPLEMENTADAS:

Sistemas de Cartas:

Pokémon Básicos
Pokémon EX
Sistemas PS
Sistema de ataques
Sistema de energías
Coste de retirada
Debilidades por tipo


Cartas Entrenador:

Misty 
Investigación del profesor


Objeto:

Poción
Poké Ball
Turbo Velocidad


Sistema de Combate:


Ataques con coste de energía
Validación de energías requeridas
Aplicación de daño
Sistema de debilidades
Derrota de Pokémon
Obtención de puntos de premio


Sistema de Juego:


Selección de mazo
Robar mano inicial
Campo de batalla
Banco de Pokémon
Turnos alternados
Adjuntar energía
Jugar cartas de entrenador
Colocar Pokémon en juego
Retirar Pokémon activos
Atacar
Condiciones de victoria


Mazos Disponibles:


Mazo Fuego/Agua:


Pokémon principales:
Charmander
Squirtle
Magikarp
Moltres ex
Articuno ex


Entrenadores:
Misty
Poción
Poké Ball
Investigación del Profesor


Mazo Eléctrico/Psíquico:


Pokémon principales:
Pikachu
Zapdos ex
Mewtwo ex
Jigglypuff
Snorlax


Entrenadores:
Misty
Poción
Poké Ball
Investigación del Profesor


Mazo Campeones de Gimnasio:


Pokémon principales:
Pikachu
Charmander
Squirtle
Zapdos ex
Mewtwo ex
Jigglypuff


Entrenadores:
Misty
Poción
Poké Ball
Investigación del Profesor


Estructura del Proyecto:


src
│
├── Carta.java
├── Pokemon.java
├── PokemonBasico.java
├── PokemonEx.java
│
├── Ataque.java
├── TipoEnergia.java
├── ZonaEnergia.java
│
├── Entrenador.java
├── Soporte.java
├── Objeto.java
│
├── Misty.java
├── InvestigacionDelProfesor.java
├── Blaine.java
├── Sabrina.java
├── TenienteSurge.java
├── Bill.java
│
├── Pocion.java
├── PokeBall.java
├── TurboVelocidad.java
│
├── Mazo.java
├── Jugador.java
├── CampoDeBatalla.java
│
├── CatalogoCartas.java
├── Consola.java
├── MotorJuego.java
│
└── App.java


Que se utilizo para la realización del proyecto:
Java
GitBash
GitHub
Visual Studio Code


EJECUCIÓN:

Compilar:
javac src/*.java

Ejecutar:
``*bash java App
