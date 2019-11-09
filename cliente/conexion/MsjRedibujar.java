package conexion;

import java.io.Serializable;

import entities.Mapa;

public class MsjRedibujar implements Serializable {
	private static final long serialVersionUID = 1L;
	private Mapa mapa;
	
	public MsjRedibujar(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public Mapa getMapa() {
		return mapa;
	}
}
