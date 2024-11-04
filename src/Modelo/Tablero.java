package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private Pieza[][] cuadros;
    private List<String> historialMovimientos;

    public Tablero() {
        cuadros = new Pieza[8][8];
        historialMovimientos = new ArrayList<>();
        inicializarTablero();
    }

    // Inicializar las piezas en sus posiciones iniciales
    public void inicializarTablero() {
        // Inicializar piezas Blancas
        cuadros[0][0] = new Torre("Blanco", 0, 0);
        cuadros[0][1] = new Caballo("Blanco", 0, 1);
        cuadros[0][2] = new Alfil("Blanco", 0, 2);
        cuadros[0][3] = new Reina("Blanco", 0, 3);
        cuadros[0][4] = new Rey("Blanco", 0, 4);
        cuadros[0][5] = new Alfil("Blanco", 0, 5);
        cuadros[0][6] = new Caballo("Blanco", 0, 6);
        cuadros[0][7] = new Torre("Blanco", 0, 7);
        for (int i = 0; i < 8; i++) {
            cuadros[1][i] = new Peon("Blanco", 1, i);
        }

        // Inicializar piezas Negras
        cuadros[7][0] = new Torre("Negro", 7, 0);
        cuadros[7][1] = new Caballo("Negro", 7, 1);
        cuadros[7][2] = new Alfil("Negro", 7, 2);
        cuadros[7][3] = new Reina("Negro", 7, 3);
        cuadros[7][4] = new Rey("Negro", 7, 4);
        cuadros[7][5] = new Alfil("Negro", 7, 5);
        cuadros[7][6] = new Caballo("Negro", 7, 6);
        cuadros[7][7] = new Torre("Negro", 7, 7);
        for (int i = 0; i < 8; i++) {
            cuadros[6][i] = new Peon("Negro", 6, i);
        }
    }

    // Método para mover una pieza en el tablero
    public void moverPieza(String movimiento) {
        // Example movement format: "e2e4"
        if (movimiento.length() != 4) {
            throw new IllegalArgumentException("Formato de movimiento inválido. Debe ser como 'e2e4'.");
        }

        // Parse start and end positions
        int filaInicial = 8 - Character.getNumericValue(movimiento.charAt(1));
        int columnaInicial = movimiento.charAt(0) - 'a';
        int filaFinal = 8 - Character.getNumericValue(movimiento.charAt(3));
        int columnaFinal = movimiento.charAt(2) - 'a';

        // Validate positions
        if (filaInicial < 0 || filaInicial >= 8 || columnaInicial < 0 || columnaInicial >= 8 ||
                filaFinal < 0 || filaFinal >= 8 || columnaFinal < 0 || columnaFinal >= 8) {
            throw new IllegalArgumentException("Movimiento fuera de los límites del tablero.");
        }

        // Get the piece at the start position
        Pieza pieza = cuadros[filaInicial][columnaInicial];
        if (pieza == null) {
            throw new IllegalArgumentException("No hay una pieza en la posición inicial.");
        }

        // Move the piece to the end position
        cuadros[filaFinal][columnaFinal] = pieza;
        cuadros[filaInicial][columnaInicial] = null;

        // Update piece's internal position
        pieza.setPosicion(filaFinal, columnaFinal);

        // Add the movement to history
        historialMovimientos.add(movimiento);
    }


    // Método para deshacer el último movimiento
    public void deshacerMovimiento() {
        if (!historialMovimientos.isEmpty()) {
            String ultimoMovimiento = historialMovimientos.remove(historialMovimientos.size() - 1);
            // Implementar lógica para revertir el movimiento en el tablero
        }
    }

    // Método para rehacer un movimiento (si ya se ha deshecho)
    public void rehacerMovimiento(String movimiento) {
        historialMovimientos.add(movimiento);
        // Implementar lógica para aplicar nuevamente el movimiento
    }

    public Pieza[][] getPiezas() {
        return cuadros;
    }


}
