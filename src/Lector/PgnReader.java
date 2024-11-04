package Lector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PgnReader {

        private List<String> movimientos;

        public PgnReader() {
            this.movimientos = new ArrayList<>();
        }

        // Método para cargar el archivo PGN y extraer movimientos
        public void cargarArchivoPGN(String rutaArchivo) {
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.startsWith("[")) { // Ignorar metadatos que están entre corchetes
                        String[] partes = linea.trim().split("\\s+");
                        for (String parte : partes) {
                            if (esMovimiento(parte)) { // Verificar si la parte es un movimiento
                                movimientos.add(parte);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        }

        // Método para verificar si una cadena es un movimiento
        private boolean esMovimiento(String cadena) {
            return cadena.matches("^[a-h1-8NBRQKO-]+$");
        }

        // Método para extraer los movimientos ya procesados en una lista
        public List<String> extraerMovimientos() {
            List<String> movimientosProcesados = new ArrayList<>();
            for (String movimiento : movimientos) {
                String movimientoParseado = parsearMovimiento(movimiento);
                if (!movimientoParseado.isEmpty()) {
                    movimientosProcesados.add(movimientoParseado);
                }
            }
            return movimientosProcesados;
        }

        // Método para convertir un movimiento PGN en un formato comprensible para el tablero
        private String parsearMovimiento(String movimiento) {
            // En una implementación más avanzada, aquí se puede analizar y traducir cada
            // movimiento de acuerdo a las reglas del ajedrez.
            // Por ahora, retornamos el movimiento directamente.
            return movimiento;
        }

        // Obtener lista de movimientos para uso en el programa principal
        public List<String> getMovimientos() {
            return movimientos;
        }

        public static void main(String[] args) {
            PgnReader pgnReader = new PgnReader();
            pgnReader.cargarArchivoPGN("ruta/al/archivo.pgn");

            List<String> movimientos = pgnReader.extraerMovimientos();
            System.out.println("Movimientos extraídos:");
            for (String movimiento : movimientos) {
                System.out.println(movimiento);
            }
        }
}
