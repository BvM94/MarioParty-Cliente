package ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.border.EmptyBorder;

import entities.Personaje;
import entities.casilla.Casilla;
import entities.casilla.CasillaGanarEstrella;
import entities.casilla.CasillaParalizar;
import entities.casilla.CasillaSumarRestarMonedas;

public class MarioJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int cantidadCasillas;

	private Casilla[][] tablero;

	public MarioJPanel(Casilla[][] tablero, int cantidadCasillas) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.cantidadCasillas = cantidadCasillas;
		this.tablero = tablero;
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D lienzo = (Graphics2D) g;
		lienzo.setStroke(new BasicStroke(5f));

		dibujarCasillas(lienzo, Color.BLACK, Color.GRAY);

	}

	private void dibujarCasillas(Graphics2D lienzo, Color colorBorde, Color colorCamino) {
		int alturaCasilla = getHeight() / cantidadCasillas;

		// Aplica margen de media casilla arriba y abajo
		alturaCasilla = (getHeight() - alturaCasilla) / cantidadCasillas;

		int posicionY = alturaCasilla / 2;
		int posicionXInicial = (getWidth() / 2) - ((cantidadCasillas / 2) * alturaCasilla);

		for (int i = 0; i < cantidadCasillas; i++) {
			int posicionX = posicionXInicial;

			for (int j = 0; j < cantidadCasillas; j++) {
				if (tablero[i][j] == null) {
					lienzo.setPaint(colorBorde);
					lienzo.drawRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
				} else {
					lienzo.setPaint(colorBorde);
					lienzo.drawRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
					lienzo.setPaint(conseguirColorCasilla(tablero[i][j], colorCamino));
					lienzo.fillRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
					
					if(tablero[i][j].getPersonajePosicionado() != null) {
						dibujarPersonaje(lienzo, posicionX, posicionY, alturaCasilla, tablero[i][j].getPersonajePosicionado());
					}
				}
				
				posicionX += alturaCasilla;
			}

			posicionY += alturaCasilla;
		}
	}
	
	private Color conseguirColorCasilla(Casilla casilla, Color colorDefault) {
		if(casilla.estaTitilando()) {
			return Color.BLACK;
		}
		
		if(casilla instanceof CasillaParalizar) {
			return Color.GREEN;
		}else if(casilla instanceof CasillaGanarEstrella) {
			return Color.YELLOW;
		}else if(casilla instanceof CasillaSumarRestarMonedas) {
			return Color.RED;
		}
		return colorDefault;
	}

	public void redibujar(Casilla[][] tablero){
		this.tablero = tablero;
		repaint();
	}

	private void dibujarPersonaje(Graphics2D lienzo, int posicionX, int posicionY, int alturaCasilla, Personaje personaje) {
		final int borde = alturaCasilla / 6;
		int alturaPersonaje = alturaCasilla - (2 * borde);
		
		lienzo.setPaint(personaje.getColor());
		lienzo.fillRect(posicionX + borde, posicionY + borde, alturaPersonaje, alturaPersonaje);
		lienzo.setPaint(Color.CYAN);
		lienzo.drawRect(posicionX + borde, posicionY + borde, alturaPersonaje / 2, alturaPersonaje / 2);
		
		lienzo.setPaint(Color.CYAN);
		lienzo.drawRect((posicionX + (alturaPersonaje / 2)) + borde, posicionY + borde, alturaPersonaje / 2, alturaPersonaje / 2);
	}

}
