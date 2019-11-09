package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import comunicaciones.MsjMapa;
import ui.EscucharTeclaInterface;
import ui.MarioJFrame;

public class ControladorCliente implements EscucharTeclaInterface {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	// DATOS NECESARIOS PARA LA CONEXION
	private static final String host = "127.0.0.1";
	private static final int puerto = 5000;
	private int jugador;
	private Object objLeido;
	private int idUsuario;

	MarioJFrame marioJFrame;

	public static void main(String[] args) {

		// GENERAMOS LA CONEXION
		try {
			System.out.println("cliente iniciando");
			ControladorCliente cc = new ControladorCliente();
			// cc.jugador = 0;
			cc.socket = new Socket(host, puerto);
			cc.out = new ObjectOutputStream(cc.socket.getOutputStream());
			cc.in = new ObjectInputStream(cc.socket.getInputStream());
			cc.HiloDeJuego();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public Object escuchar() {
		try {
			System.out.println(in.toString());
			Object peticion = in.readObject();
			objLeido = peticion;
			return peticion;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void enviarMensaje(Object obj) {
		try {
			out.reset(); // USAR SIEMPRE
			out.writeObject(obj);
			// out.reset(); // USAR SIEMPRE
			out.flush();

			objLeido = (Object) in.readObject();
			if (objLeido instanceof Integer) {
				jugador = (Integer) objLeido; // REPRESENTA AL JUGADOR EN JUEGO
			}
			System.out.println(objLeido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	public Object leerMensaje() {
		return objLeido;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void cerrarSocket() {
		try {
			enviarMensaje("KILL THREAD");
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}

	private void iniciarMapa(MsjMapa msjMapa) {
		marioJFrame = new MarioJFrame(msjMapa.getMapa().getTablero(), msjMapa.getMapa().getTablero().length, this);

	}

	public void HiloDeJuego() {
		Thread hiloDeJuego = new Thread(new Runnable() {
			public void run() {

				while (true) {

					Object peticion = escuchar();
					switch (peticion.getClass().getName()) {
					case "MsjMapa":
						iniciarMapa((MsjMapa) peticion);
						break;

					case "MsjRedibujar":
						redibujarMapa((MsjRedibujar) peticion);
						break;

					default:
						break;
					}

				} // FIN WHILE TRUE

			}
		});
		hiloDeJuego.start();
	}

	private void redibujarMapa(MsjRedibujar peticion) {
		marioJFrame.redibujar(peticion.getMapa().getTablero());
	}

	@Override
	public void teclaPresionada(int tecla) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTeclaPresionada() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void limpiarTeclaPresionada() {
		// TODO Auto-generated method stub

	}

}
