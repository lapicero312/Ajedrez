package Vista;
import Controlador.Controlador;
import Modelo.Tablero;
import Modelo.Pieza;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AjedrezGUI extends JFrame {
    private JPanel tableroPanel;
    private JButton btnAvanzar, btnRetroceder, btnReiniciar, btnCargarArchivo;
    private Tablero tablero;
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    // Clase que representa el estado del tablero de ajedrez

    public AjedrezGUI() {
        // Configuración de la ventana principal
        setTitle("Visualizador de Partidas de Ajedrez");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal para el tablero
        tableroPanel = new JPanel();
        tableroPanel.setLayout(new GridLayout(8, 8));
        add(tableroPanel, BorderLayout.CENTER);

        // Crear botones de control
        btnAvanzar = new JButton("Avanzar");
        btnRetroceder = new JButton("Retroceder");
        btnReiniciar = new JButton("Reiniciar");
        btnCargarArchivo = new JButton("Cargar Archivo");

        // Panel de control
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());
        panelControl.add(btnCargarArchivo);
        panelControl.add(btnRetroceder);
        panelControl.add(btnAvanzar);
        panelControl.add(btnReiniciar);
        add(panelControl, BorderLayout.SOUTH);

        // Inicializar tablero
        tablero = new Tablero();
        dibujarTablero();

        // Listeners para los botones de control
        btnCargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });

        btnRetroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablero.deshacerMovimiento();
                dibujarTablero();
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciarPartida();  // Llama al método en el controlador
                dibujarTablero();
            }
        });

        btnAvanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.avanzarMovimiento();  // Llama al método en el controlador
                dibujarTablero();
            }
        });
        ;
    }

    // Método para cargar un archivo PGN
    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            controlador.cargarPartida(fileChooser.getSelectedFile());
            dibujarTablero();
        }
    }

    public File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private void dibujarTablero() {
        tableroPanel.removeAll();
        Pieza[][] piezas = tablero.getPiezas();  // Accede al estado actual del tablero

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);

                Pieza pieza = piezas[row][col];
                if (pieza != null) {
                    // Obtiene la imagen correspondiente a la pieza
                    String nombreArchivo = obtenerNombreArchivoImagen(pieza);
                    if (nombreArchivo != null) {
                        // Cargar imagen como recurso
                        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/" + nombreArchivo));
                        button.setIcon(new ImageIcon(icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                    }
                }

                // Colorear las casillas
                if ((row + col) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }

                tableroPanel.add(button);
            }
        }
        tableroPanel.revalidate();
        tableroPanel.repaint();
    }

    // Método auxiliar para obtener el nombre del archivo de imagen basado en la pieza
    private String obtenerNombreArchivoImagen(Pieza pieza) {
        String tipoPieza = pieza.getClass().getSimpleName().toLowerCase();  // Ejemplo: "torre", "caballo"
        String color = pieza.getColor().toLowerCase();                      // Ejemplo: "blanco", "negro"
        return tipoPieza + "_" + color + ".png";                            // Devuelve "torre_blanco.png"
    }




    // Actualiza las piezas en el tablero visual
    public void actualizarPiezas() {
        tableroPanel.removeAll();  // Clear the board to re-add components
        Pieza[][] piezas = tablero.getPiezas();  // Access current board state

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = piezas[i][j];
                JPanel casilla = new JPanel();
                casilla.setLayout(new BorderLayout());

                if (pieza != null) {
                    // Create label text based on piece type and color
                    String nombrePieza = pieza.getClass().getSimpleName() + " " + pieza.getColor();
                    JLabel label = new JLabel(nombrePieza);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    casilla.add(label, BorderLayout.CENTER);
                }

                // Set color of the square
                if ((i + j) % 2 == 0) {
                    casilla.setBackground(Color.WHITE);
                } else {
                    casilla.setBackground(Color.GRAY);
                }

                tableroPanel.add(casilla);  // Add the square to the board
            }
        }

        tableroPanel.revalidate();
        tableroPanel.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AjedrezGUI gui = new AjedrezGUI();
            gui.setVisible(true);
        });
    }

}

