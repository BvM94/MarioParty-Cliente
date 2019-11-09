package comunicaciones;

import java.io.Serializable;

public class MsjDesicion implements Serializable{

	private int desicion;
	
	public MsjDesicion (int desicion) {
		this.desicion = desicion;
	}
	
	public int getDesicion() {
		return desicion;
	}
}
