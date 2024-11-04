package Modelo;
import java.util.ArrayList;
import java.util.List;

    public class Rey extends Pieza {

        public Rey(String color, int fila, int columna) {
            super(color, fila, columna);
        }

        @Override
        public List<int[]> movimientosPosibles(Pieza[][] tablero) {
            List<int[]> movimientos = new ArrayList<>();
            int[][] posiblesMovimientos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

            for (int[] movimiento : posiblesMovimientos) {
                int nuevaFila = fila + movimiento[0];
                int nuevaColumna = columna + movimiento[1];
                if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
                    movimientos.add(new int[]{nuevaFila, nuevaColumna});
                }
            }
            return movimientos;
        }
    }


