package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza {

    public Alfil(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    @Override
    public List<int[]> movimientosPosibles(Pieza[][] tablero) {
        List<int[]> movimientos = new ArrayList<>();

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




