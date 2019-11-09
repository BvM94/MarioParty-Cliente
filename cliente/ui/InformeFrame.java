package ui;

import java.util.List;

import javax.swing.JFrame;

import entities.Personaje;
import entities.casillas.Casilla;
import entities.casillas.CasillaGanarEstrella;

import javax.swing.JLabel;
import java.awt.BorderLayout;

public class InformeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Personaje> jugadores;
	private int cantidad;
	private Personaje jugAct = null;
	private JLabel labelInfo = new JLabel();
	private int valor = -1;
	private String estado = "";

	public InformeFrame(List<Personaje> jugadores, int size) {
		this.jugadores = jugadores;
		this.cantidad = size;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Textos.TITULO_INFORME);
		setSize(250, 100);
		setLocation(1000, 200);
		actLabel();
		setVisible(true);
	}
	
	public void actLabel() {
		getContentPane().add(labelInfo, BorderLayout.NORTH);
		if (jugAct != null) {
			if(estado == "com") {
				labelInfo.setText("Turno del jugador : " + jugAct.getNombre());
			}
			else if(estado == "seleccion") {
				labelInfo.setText(jugAct.getNombre() + " esta seleccionando un item");
			}
			else if(estado == "avanza") {
				labelInfo.setText(jugAct.getNombre() + " avanza " + valor + " casillas");
			}
			else if(estado == "cas") {
				System.out.println("hola");
				if(jugAct.getCasillaActual() instanceof Casilla) {
					labelInfo.setText(jugAct.getNombre() + " esta en una casilla normal");
				}
				else if(jugAct.getCasillaActual() instanceof CasillaGanarEstrella) {
					labelInfo.setText(jugAct.getNombre() + " ha ganado una estrella");
				}
			}
		} else {
			labelInfo.setText("Comienza el juego");
		}
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Personaje getJugAct() {
		return jugAct;
	}

	public void setJugAct(Personaje jugAct) {
		this.jugAct = jugAct;
	}

	public void redibujar(List<Personaje> jugadores) {
		this.jugadores = jugadores;
		actLabel();
		repaint();
	}
}
