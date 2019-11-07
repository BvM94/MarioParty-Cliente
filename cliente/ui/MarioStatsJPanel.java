package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Personaje;

public class MarioStatsJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Personaje> jugadores;
	private int cantidad;
	
	public MarioStatsJPanel(List<Personaje> jugadores,int cantidad) {
		this.jugadores = jugadores;
		this.cantidad = cantidad;
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
	}

	public void setearLabels() {
		JLabel[] labels1 = new JLabel[cantidad];
		JLabel[] labels2 = new JLabel[cantidad];
		JLabel[] labels3 = new JLabel[cantidad];
		JLabel[] labels4 = new JLabel[cantidad];
		for (int i = 0; i < cantidad; i++) {
			labels1[i] = new JLabel();
			labels2[i] = new JLabel();
			labels3[i] = new JLabel();
			labels4[i] = new JLabel();
			labels1[i].setFont(new Font("Courier New", Font.ITALIC, 12));
			labels2[i].setFont(new Font("Courier New", Font.ITALIC, 12));
			labels3[i].setFont(new Font("Courier New", Font.ITALIC, 12));
			labels4[i].setFont(new Font("Courier New", Font.ITALIC, 12));
			labels1[i].setForeground(jugadores.get(i).getColor());
			labels1[i].setText("Jugador : " + jugadores.get(i).getNombre());
			labels2[i].setText("Estado : " + jugadores.get(i).getEstado());
			labels3[i].setText("Casilla : " + jugadores.get(i).getCasillaActual().getX() + " " + jugadores.get(i).getCasillaActual().getY());
			labels4[i].setText("Monedas : " + jugadores.get(i).getMonedas() + " Estrellas : " + jugadores.get(i).getEstrellas());
			Dimension size1 = labels1[i].getPreferredSize();
			Dimension size2 = labels2[i].getPreferredSize();
			Dimension size3 = labels3[i].getPreferredSize();
			Dimension size4 = labels4[i].getPreferredSize();
			labels1[i].setBounds(50,45*i+50,size1.width,size1.height);
			labels2[i].setBounds(50,45*i+60,size2.width,size2.height);
			labels3[i].setBounds(50,45*i+70,size3.width,size3.height);
			labels4[i].setBounds(50,45*i+80,size4.width,size4.height);
			add(labels1[i]);
			add(labels2[i]);
			add(labels3[i]);
			add(labels4[i]);
		}
	}
	
	public void redibujar(List<Personaje> jugadores) {
		this.jugadores = jugadores;
		removeAll();
		setearLabels();
	}
}
