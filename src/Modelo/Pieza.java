package Modelo;

import java.util.List;

public abstract class Pieza {
    protected String color;
    protected int fila;
    protected int columna;

    public Pieza(String color, int fila, int columna) {
        this.color = color;
        this.fila = fila;
        this.columna = columna;
    }

    public String getColor() {
        return color;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    // En la clase Pieza
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    // Método abstracto para movimientos posibles de cada pieza
    public abstract List<int[]> movimientosPosibles(Pieza[][] tablero);

    public boolean agregarMovimientoSiValido(Pieza[][] tablero, List<int[]> movimientos, int fila, int columna) {
        if (tablero[fila][columna] == null) {
            movimientos.add(new int[]{fila, columna});
            return false; // Continuar buscando en esta dirección
        } else if (!tablero[fila][columna].getColor().equals(this.color)) {
            movimientos.add(new int[]{fila, columna}); // Agregar si es una pieza del oponente
            return true; // Detenerse ya que encontró una pieza
        }
        return true; // Detenerse si es una pieza del mismo color
    }
}
