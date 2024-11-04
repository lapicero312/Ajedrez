package Controlador;
import Modelo.Tablero;
import Vista.AjedrezGUI;

import java.util.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Controlador {

    private Tablero tablero;
    private AjedrezGUI gui;
    private List<String> listaMovimientos;  // Lista de movimientos a reproducir
    private int indiceMovimiento;  // Índice para avanzar movimientos

    public Controlador(AjedrezGUI gui) {
        this.gui = gui;
        this.tablero = new Tablero();
        this.listaMovimientos = new ArrayList<>();
        this.indiceMovimiento = 0;

        // Vincula los botones de la interfaz con el controlador
        gui.setControlador(this);
        actualizarGUI();
    }

    // Método para avanzar un movimiento (ejecuta un movimiento desde la lista)
    public void avanzarMovimiento() {
        if (indiceMovimiento < listaMovimientos.size()) {
            String movimiento = listaMovimientos.get(indiceMovimiento);
            tablero.moverPieza(movimiento);  // Aplica el movimiento al tablero
            indiceMovimiento++;
            actualizarGUI();
        } else {
            System.out.println("No hay más movimientos por avanzar.");
        }
    }

    // Método para reiniciar la partida
    public void reiniciarPartida() {
        tablero.inicializarTablero();  // Método que resetea el tablero al estado inicial
        listaMovimientos.clear();
        indiceMovimiento = 0;
        actualizarGUI();
    }

    // Carga y ejecuta una secuencia de movimientos desde un archivo
    public void cargarPartida(File archivo) {
        listaMovimientos = leerMovimientosDesdeArchivo(archivo);
        indiceMovimiento = 0;  // Reinicia el índice para reproducir la partida desde el principio
        actualizarGUI();
    }

    private List<String> leerMovimientosDesdeArchivo(File archivo) {
        List<String> movimientos = new ArrayList<>();
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    movimientos.add(linea);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    private void actualizarGUI() {
        gui.actualizarPiezas();  // Actualiza la GUI con el estado actual del tablero
    }

    private void cargarArchivo() {
        File file = gui.seleccionarArchivo();
        if (file != null) {
            cargarPartida(file);  // Carga el archivo y prepara los movimientos
        }
    }
}
