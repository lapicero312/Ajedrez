package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {

    private boolean esPrimerMovimiento;

    public Peon(String color, int fila, int columna) {
        super(color, fila, columna);
        this.esPrimerMovimiento = true;
    }

    @Override
    public List<int[]> movimientosPosibles(Pieza[][] tablero) {
        List<int[]> movimientos = new ArrayList<>();
        int direccion = color.equals("Blanco") ? -1 : 1; // Hacia arriba para blancos, abajo para negros

        // Movimiento hacia adelante
        int nuevaFila = fila + direccion;
        if (esPosicionValida(nuevaFila, columna) && tablero[nuevaFila][columna] == null) {
            movimientos.add(new int[]{nuevaFila, columna});

            // Movimiento doble en el primer turno
            if (esPrimerMovimiento) {
                int filaDoble = fila + 2 * direccion;
                if (esPosicionValida(filaDoble, columna) && tablero[filaDoble][columna] == null) {
                    movimientos.add(new int[]{filaDoble, columna});
                }
            }
        }

        // Capturas en diagonal
        if (esPosicionValida(nuevaFila, columna - 1) && tablero[nuevaFila][columna - 1] != null &&
                !tablero[nuevaFila][columna - 1].getColor().equals(this.color)) {
            movimientos.add(new int[]{nuevaFila, columna - 1});
        }
        if (esPosicionValida(nuevaFila, columna + 1) && tablero[nuevaFila][columna + 1] != null &&
                !tablero[nuevaFila][columna + 1].getColor().equals(this.color)) {
            movimientos.add(new int[]{nuevaFila, columna + 1});
        }

        return movimientos;
    }

    // Método auxiliar para verificar si una posición es válida en el tablero
    private boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
    }

    // Método para marcar que el peón se ha movido
    public void mover() {
        esPrimerMovimiento = false;
    }
}
