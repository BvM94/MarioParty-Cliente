package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import entities.casillas.Casilla;

public class MarioJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MarioJPanel panel;
	private boolean escucharTeclas = false;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public MarioJFrame(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		// Full Screen
		// setExtendedState(MAXIMIZED_BOTH);
		setSize(500, 500);

		// cerrar con la X la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle(Textos.TITULO_PRINCIPAL);
		setLocationRelativeTo(null);

		this.socket = socket;
		this.in = in;
		this.out = out;

		HiloDeJuego();

	}

	public MarioJFrame(Casilla[][] tablero, int cantidadCasillas, EscucharTeclaInterface escucharTeclaInterface) {
		// Full Screen
		// setExtendedState(MAXIMIZED_BOTH);
		setSize(500, 500);

		// cerrar con la X la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle(Textos.TITULO_PRINCIPAL);
		setLocationRelativeTo(null);

		panel = new MarioJPanel(tablero, cantidadCasillas);
		setContentPane(panel);

		setVisible(true);

		escuchar(escucharTeclaInterface);
	}

	private void escuchar(EscucharTeclaInterface escucharTeclaInterface) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (escucharTeclas) {
					System.out.println("Toque tecla");
					escucharTeclas = false;
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						escucharTeclaInterface.teclaPresionada(0);
						break;
					case KeyEvent.VK_DOWN:
						escucharTeclaInterface.teclaPresionada(1);
						break;
					case KeyEvent.VK_RIGHT:
						escucharTeclaInterface.teclaPresionada(2);
						break;
					case KeyEvent.VK_LEFT:
						escucharTeclaInterface.teclaPresionada(3);
						break;

					default:
						System.out.println("La tecla " + e.getKeyChar() + "es invalida, vuelva a ingresar");
						escucharTeclas = true;
						break;
					}
				}
			}
		});

	}

	public void escucharTeclas() {
		escucharTeclas = true;
	}

	public void redibujar(Casilla[][] tablero) {
		panel.redibujar(tablero);
		repaint();
	}

	public void HiloDeJuego() {
		Thread hiloDeJuego = new Thread(new Runnable() {

			public void run() {
				Object peticion = null;
				while (true) {
					try {
						peticion = in.readObject();
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					switch (peticion.getClass().getSimpleName()) {
					case "Devolver":
						System.out.println("Recibo de vuelta string");

						break;
					}
				} // FIN WHILE TRUE
			}

		});
		hiloDeJuego.start();
	}

}
