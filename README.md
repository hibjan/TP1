# CLI Plants vs Zombies

Una versión de un juego tipo Plants vs Zombies: se maneja un mundo con plantas, zombies, registro, monedas y acciones por turno.

## Estructura del proyecto

- `src/main/java/tp1/p2/`: código principal.
- `src/main/java/tp1/utils/`: utilidades.
- `src/main/resources/`: recursos (opcional).
- `src/test/java/`: tests JUnit (por agregar).
- `record.txt` (opcional, se crea al guardar récords) en raiz de TP_NEW.
- `pom.xml`: configuración Maven con compilación y plugins.

## Requisitos

- Java 17 (o JVM compatible 17+).
- Maven 3.6+.

## Uso

- Compilar:
  - `mvn clean compile`
- Empaquetar:
  - `mvn package`
- Ejecutar sin argumentos (usar por defecto):
  - `mvn exec:java`
- Ejecutar con argumentos:
  - `mvn exec:java -Dexec.mainClass=tp1.p2.PlantsVsZombies -Dexec.args="EASY 123"`

## Ejemplo de uso de la aplicación

`PlantsVsZombies <level> [<seed>]`

- `<level>`: EASY, HARD, INSANE
- `<seed>`: número para el generador de elementos (opcional)

### Ejemplo con valores reales

`mvn exec:java -Dexec.mainClass=tp1.p2.PlantsVsZombies -Dexec.args="INSANE 42"`

## Comandos dentro del juego

Al iniciar, el juego solicita comandos en consola. Con `help` se muestra la lista completa. Principales comandos:

- `help`: muestra ayuda de comandos.
- `show`: muestra el estado actual del tablero.
- `list`: enumera plantas o zombies disponibles.
- `addPlant <tipo> <fila> <col>`: agrega planta en la posición.
- `addZombie <tipo> <fila> <col>`: agrega zombie en la posición (modo prueba).
- `catch <fila> <col>`: captura un sol en la celda para monedas.
- `reset`: reinicia el juego.
- `exit`: sale del juego.
- `record`: muestra/guarda el mejor puntaje.

> Nota: el conjunto exacto de comandos depende de la versión. Usa `help` en el juego para ver comandos activos y su sintaxis.

## Sobre el proyecto

El proyecto implementa:

- `%` Comandos: `AddPlant`, `AddZombie`, `Catch`, `Reset`, `Help`, `ShowRecord`, etc.
- `Game`: lógica de turnos, acciones y estados.
- `GameObject`: plantas/zombies y efectos de acciones.
- `SunsManager` y `ZombiesManager` para control de spawn y monedas.
