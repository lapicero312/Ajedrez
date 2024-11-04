package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Torre extends Pieza {

    public Torre(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    @Override
    public List<int[]> movimientosPosibles(Pieza[][] tablero) {
        List<int[]> movimientos = new ArrayList<>();

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

        return movimientos;
    }
}