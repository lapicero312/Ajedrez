package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Caballo extends Pieza {

    public Caballo(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    @Override
    public List<int[]> movimientosPosibles(Pieza[][] tablero) {
        List<int[]> movimientos = new ArrayList<>();

        int[][] movimientosL = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] movimiento : movimientosL) {
            int nuevaFila = fila + movimiento[0];
            int nuevaColumna = columna + movimiento[1];
            if (esPosicionValida(nuevaFila, nuevaColumna)) {
                if (tablero[nuevaFila][nuevaColumna] == null ||
                        !tablero[nuevaFila][nuevaColumna].getColor().equals(this.color)) {
                    movimientos.add(new int[]{nuevaFila, nuevaColumna});
                }
            }
        }

        return movimientos;
    }

    // Método auxiliar para verificar si una posición es válida en el tablero
    private boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
    }
}
