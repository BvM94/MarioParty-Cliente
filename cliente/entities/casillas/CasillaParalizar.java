package entities.casillas;

import java.io.Serializable;

import entities.Personaje;

public class CasillaParalizar extends Casilla implements Serializable {
	private int turnosParalizado;
	
	public CasillaParalizar(int x, int y, boolean[] direcciones, int turnosParalizado) {
		super(x, y, direcciones);
		this.turnosParalizado=turnosParalizado;
	}

	public void aplicarEfecto(Personaje pj) {
		pj.paralizado(turnosParalizado);;
	}
}
