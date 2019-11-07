package ui;

import java.util.List;

import javax.swing.JFrame;

import entities.Personaje;

public class MarioStatsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private MarioStatsJPanel panel;

	public MarioStatsFrame(List<Personaje> jugadores, int i) {
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Cerrar con la X la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Textos.TITULO_STATS);
		setSize(300, 400);
		setLocation(100,150);
		panel = new MarioStatsJPanel(jugadores, i);
		setContentPane(panel);
		setVisible(true);
	}

	public void redibujar(List<Personaje> jugadores) {
		panel.redibujar(jugadores);
		repaint();
	}

}
