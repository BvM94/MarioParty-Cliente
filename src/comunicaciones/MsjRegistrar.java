package comunicaciones;

import java.io.Serializable;

public class MsjRegistrar implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String nickName;
	
	public MsjRegistrar(String nickName) {
		this.nickName = nickName;
	}
	
}
