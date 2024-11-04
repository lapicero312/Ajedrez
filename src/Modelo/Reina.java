package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Reina extends Pieza {

    public Reina(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    @Override
    public List<int[]> movimientosPosibles(Pieza[][] tablero) {
        List<int[]> movimientos = new ArrayList<>();

        // Movimientos verticales y horizontales (como la Torre)
        // Dirección arriba
        for (int i = fila - 1; i >= 0; i--) {
            if (agregarMovimientoSiValido(tablero, movimientos, i, columna)) break;
        }
        // Dirección abajo
        for (int i = fila + 1; i < 8; i++) {
            if (agregarMovimientoSiValido(tablero, movimientos, i, columna)) break;
        }
        // Dirección izquierda
        for (int j = columna - 1; j >= 0; j--) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila, j)) break;
        }
        // Dirección derecha
        for (int j = columna + 1; j < 8; j++) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila, j)) break;
        }

        // Movimientos diagonales (como el Alfil)
        // Dirección diagonal superior izquierda
        for (int i = 1; fila - i >= 0 && columna - i >= 0; i++) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila - i, columna - i)) break;
        }
        // Dirección diagonal superior derecha
        for (int i = 1; fila - i >= 0 && columna + i < 8; i++) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila - i, columna + i)) break;
        }
        // Dirección diagonal inferior izquierda
        for (int i = 1; fila + i < 8 && columna - i >= 0; i++) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila + i, columna - i)) break;
        }
        // Dirección diagonal inferior derecha
        for (int i = 1; fila + i < 8 && columna + i < 8; i++) {
            if (agregarMovimientoSiValido(tablero, movimientos, fila + i, columna + i)) break;
        }

        return movimientos;
    }
}



